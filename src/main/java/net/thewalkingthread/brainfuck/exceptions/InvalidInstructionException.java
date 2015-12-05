package net.thewalkingthread.brainfuck.exceptions;

public class InvalidInstructionException extends BrainfuckException{
    public InvalidInstructionException(Integer in){
        super(in);
    }
}
