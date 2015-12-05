package net.thewalkingthread.brainfuck.payload;

import net.thewalkingthread.brainfuck.exceptions.InvalidInstructionException;

public class Instruction implements Comparable<Instruction>{

    public enum Type {
        PLUS, MINUS, PTR_INC, PTR_DEC, LOOP_OPEN, LOOP_CLOSE, PUT, GET
    }

    private Type type;
    private Instruction next;
    private Instruction jump;
    public Integer num;

    public Instruction(char c, int num) throws InvalidInstructionException {
        this.num = num;
        next = null;
        jump = null;
        switch (c){
            case '+': type = Type.PLUS; break;
            case '-': type = Type.MINUS; break;
            case '>': type = Type.PTR_INC; break;
            case '<': type = Type.PTR_DEC; break;
            case '[': type = Type.LOOP_OPEN; break;
            case ']': type = Type.LOOP_CLOSE; break;
            case '.': type = Type.PUT; break;
            case ',': type = Type.GET; break;
            default: throw new InvalidInstructionException();
        }
    }

    public Instruction getNext() {
        return next;
    }

    public void setNext(Instruction next) {
        this.next = next;
    }

    public Instruction getJump() {
        return jump;
    }

    public void setJump(Instruction jump) {
        this.jump = jump;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int compareTo(Instruction o) {
        return num.compareTo(o.num);
    }
}
