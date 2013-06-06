import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.23")
public class FibonaccinLuvutTest {

    @Test
    public void testit() {
        assertTrue("F(0):n tulisi olla 0", FibonaccinLuvut.fibonacci(0) == 0);
        assertTrue("F(1):n tulisi olla 1", FibonaccinLuvut.fibonacci(1) == 1);
        int a = 0, b = 1;
        for (int i = 2; i <= 20; i++) {
            int c = a + b;
            a = b;
            b = c;
            assertTrue("F(" + i + "):n tulisi olla " + c, FibonaccinLuvut.fibonacci(i) == c);
        }
    }
    
}