import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.17")
public class PieninKahdestaTest {

    private void testi(int a, int b, int c) {
        boolean oikein = PieninKahdesta.pienin2(a, b) == c;
        assertTrue("Metodi 'pienin2' toimii väärin, kun a = " + a + " ja b = " + b, oikein);        
    }
    
    @Test
    public void vasenPienin() {
        testi(3, 5, 3);
    }

    @Test
    public void oikeaPienin() {
        testi(5, 3, 3);
    }

    @Test
    public void yhtaPienet() {
        testi(3, 3, 3);
    }

    
}