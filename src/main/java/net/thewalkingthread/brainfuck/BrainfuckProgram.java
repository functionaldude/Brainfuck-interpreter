package net.thewalkingthread.brainfuck;

import net.thewalkingthread.brainfuck.exceptions.BrainfuckException;
import net.thewalkingthread.brainfuck.exceptions.InvalidInstructionException;
import net.thewalkingthread.brainfuck.exceptions.LoopConsistencyException;
import net.thewalkingthread.brainfuck.exceptions.PointerUnderflowException;
import net.thewalkingthread.brainfuck.payload.Instruction;

import java.util.*;

public class BrainfuckProgram {
    private Set<Instruction> cmds;
    private Instruction start;
    private byte[] heap;
    private int heap_ptr;
    private Stack<String> input_stack;

    public BrainfuckProgram(String program) throws BrainfuckException {
        cmds = new TreeSet<>();
        start = null;
        parse(program);
        heap = new byte[100];
        heap_ptr = 0;
    }

    public String run() throws PointerUnderflowException {
        Instruction iter = start;
        Instruction next;

        //output
        String retval = "";

        //input
        Scanner scanner = new Scanner(System.in).useDelimiter("\\s*");

        while (iter != null){
            //System.out.println(iter.num);
            next = iter.getNext();
            switch (iter.getType()){
                case PLUS:
                    heap[heap_ptr]++;
                    break;
                case MINUS:
                    if (heap[heap_ptr] > 0) heap[heap_ptr]--;
                    break;
                case PTR_INC:
                    heap_ptr++;
                    if (heap_ptr == heap.length){
                        byte[] new_heap = new byte[heap.length * 2];
                        System.arraycopy(heap, 0, new_heap, 0, heap.length);
                        heap = new_heap;
                    }
                    break;
                case PTR_DEC:
                    if (heap_ptr == 0)
                        throw new PointerUnderflowException();
                    else
                        heap_ptr--;
                    break;
                case LOOP_OPEN:
                    if (heap[heap_ptr] == 0)
                        next = iter.getJump().getNext();
                    break;
                case LOOP_CLOSE:
                    if (heap[heap_ptr] != 0)
                        next = iter.getJump().getNext();
                    break;
                case PUT:
                    retval += (char)heap[heap_ptr];
                    break;
                case GET:
                    if (input_stack.empty())
                        while (!scanner.hasNext("z")){
                            input_stack.push(""+scanner.next().charAt(0));
                        }
                    heap[heap_ptr] = (byte)input_stack.remove(0).charAt(0);
                    break;
            }
            iter = next;
        }
        return retval;
    }

    private void parse(String input) throws InvalidInstructionException, LoopConsistencyException {
        Instruction prev = null;
        Stack<Instruction> loop_stack = new Stack<>();
        for(int char_counter = 0; char_counter < input.length(); ++char_counter){
            Instruction current = new Instruction(input.charAt(char_counter), char_counter);
            if (start == null) start = current;

            if (prev != null){
                prev.setNext(current);
            }

            if (current.getType() == Instruction.Type.LOOP_OPEN){
                loop_stack.push(current);
            } else if (current.getType() == Instruction.Type.LOOP_CLOSE){
                if (loop_stack.empty()) throw new LoopConsistencyException();
                Instruction begin = loop_stack.pop();
                current.setJump(begin);
                begin.setJump(current);
            }

            cmds.add(current);
            prev = current;
        }
    }
}
