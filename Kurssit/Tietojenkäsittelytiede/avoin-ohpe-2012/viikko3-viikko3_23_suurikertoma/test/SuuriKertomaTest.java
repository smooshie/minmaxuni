import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.math.BigInteger;

@Points("3.23")
public class SuuriKertomaTest {

    @Test
    public void testi1() {
        int luku = 5;
        BigInteger tulos = new BigInteger("120");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, SuuriKertoma.suuriKertoma(luku).equals(tulos));
    }

    @Test
    public void testi2() {
        int luku = 20;
        BigInteger tulos = new BigInteger("2432902008176640000");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, SuuriKertoma.suuriKertoma(luku).equals(tulos));
    }

    @Test
    public void testi3() {
        int luku = 79;
        BigInteger tulos = new BigInteger("894618213078297528685144171539831652069808216779571907213868063227837990693501860533361810841010176000000000000000000");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, SuuriKertoma.suuriKertoma(luku).equals(tulos));
    }

    
}
