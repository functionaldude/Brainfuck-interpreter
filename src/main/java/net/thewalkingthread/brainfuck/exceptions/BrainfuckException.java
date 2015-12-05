package net.thewalkingthread.brainfuck.exceptions;

abstract public class BrainfuckException extends Exception{
    private Integer instruction_num;
    public BrainfuckException(Integer in){
        instruction_num = in;
    }

    public Integer getInstruction() {
        return instruction_num;
    }
}
