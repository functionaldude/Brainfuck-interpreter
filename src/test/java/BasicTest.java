import net.thewalkingthread.brainfuck.BrainfuckProgram;
import org.junit.Assert;
import org.junit.Test;

public class BasicTest {
    @Test
    public void testHelloWorld1() throws Exception {
        String input = "++++++++++" +
                "[>+++++++>++++++++++>+++>+<<<<-]" +
                ">++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
        BrainfuckProgram program = new BrainfuckProgram(input);
        String out = program.run();

        Assert.assertTrue(out.equals("Hello World!\n"));
    }

    @Test
    public void testHelloWorld2() throws Exception {
        String input = ">++++++++[<+++++++++>-]<.>>+>+>++>[-]+<[>[->+<<++++>]<<]>.+++++++..+++.>" +
                ">+++++++.<<<[[-]<[-]>]<+++++++++++++++.>>.+++.------.--------.>>+.>++++.";
        BrainfuckProgram program = new BrainfuckProgram(input);
        String out = program.run();

        Assert.assertTrue(out.equals("Hello World!\n"));
    }

    @Test
    public void testHelloWorld3() throws Exception {
        String input = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>." +
                ">---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
        BrainfuckProgram program = new BrainfuckProgram(input);
        String out = program.run();

        Assert.assertTrue(out.equals("Hello World!\n"));
    }
}
