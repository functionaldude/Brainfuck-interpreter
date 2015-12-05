package net.thewalkingthread.brainfuck.exceptions;

public class PointerUnderflowException extends BrainfuckException{
    public PointerUnderflowException(Integer in){
        super(in);
    }
}
