import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.math.BigInteger;

@Points("3.25")
public class Nopanheitot2Test {

    @Test
    public void testi1() {
        int luku = 9;
        BigInteger tulos = new BigInteger("248");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, Nopanheitot2.tehokasNopat(luku).equals(tulos));
    }

    @Test
    public void testi2() {
        int luku = 18;
        BigInteger tulos = new BigInteger("117920");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, Nopanheitot2.tehokasNopat(luku).equals(tulos));
    }
    
    @Test
    public void testi3() {
        int luku = 47;
        BigInteger tulos = new BigInteger("49847951551648");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, Nopanheitot2.tehokasNopat(luku).equals(tulos));
    }

    @Test
    public void testi4() {
        int luku = 192;
        BigInteger tulos = new BigInteger("672894679444672529853011495357117183642878171405096738680");
        assertTrue("Metodi toimii väärin kun parametri on " + luku, Nopanheitot2.tehokasNopat(luku).equals(tulos));
    }

    
}