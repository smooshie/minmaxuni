import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.math.BigInteger;

@Points("3.24")
public class FibonaccinLuvut2Test {

    @Test
    public void testi1() {
        int luku = 12;
        BigInteger tulos = new BigInteger("144");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, FibonaccinLuvut2.tehokasFibonacci(luku).equals(tulos));
    }

    @Test
    public void testi2() {
        int luku = 44;
        BigInteger tulos = new BigInteger("701408733");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, FibonaccinLuvut2.tehokasFibonacci(luku).equals(tulos));
    }
    
    @Test
    public void testi3() {
        int luku = 95;
        BigInteger tulos = new BigInteger("31940434634990099905");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, FibonaccinLuvut2.tehokasFibonacci(luku).equals(tulos));
    }

    @Test
    public void testi4() {
        int luku = 161;
        BigInteger tulos = new BigInteger("1983924214061919432247806074196061");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, FibonaccinLuvut2.tehokasFibonacci(luku).equals(tulos));
    }

    
}