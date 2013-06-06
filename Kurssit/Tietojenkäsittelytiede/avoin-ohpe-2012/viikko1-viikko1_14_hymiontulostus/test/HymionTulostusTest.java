import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.14")
public class HymionTulostusTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    private void testi(int parametri, String tulos) {
        HymionTulostus.tulostaHymio(parametri);
        String tulostus = io.getSysOut();
        assertTrue("Metodi 'tulostaHymio' ei tulosta mitään, kun parametri on 1", tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        int maara = rivit.length;
        assertTrue("Metodi 'tulostaHymio' tulostaa " + maara + " riviä 1:n sijasta, kun parametri on 1", maara == 1);
        assertTrue("Metodi 'tulostaHymio' tulostaa virheellisen hymiön, kun parametri on " + parametri, rivit[0].trim().equals(tulos));        
    }
    
    @Test
    public void parametri1() {
        testi(1, "(^_^)");
    }

    @Test
    public void parametri2() {
        testi(2, "(O_o)");
    }

    @Test
    public void parametri3() {
        testi(3, "(;_;)");
    }

}