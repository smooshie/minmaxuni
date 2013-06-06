import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.18")
public class PieninKolmestaTest {

    private void testi(int a, int b, int c, int d) {
        boolean oikein = PieninKolmesta.pienin3(a, b, c) == d;
        assertTrue("Metodi 'pienin3' toimii väärin, kun a = " + a + ", b = " + b + " ja c = " + c, oikein);        
    }
    
    @Test
    public void kaikkiErisuuret() {
        testi(1, 2, 3, 1);
        testi(1, 3, 2, 1);
        testi(2, 1, 3, 1);
        testi(3, 1, 2, 1);
        testi(2, 3, 1, 1);
        testi(3, 2, 1, 1);
    }

    @Test
    public void kaksiYhtasuurta() {
        testi(1, 1, 2, 1);
        testi(1, 2, 1, 1);
        testi(2, 1, 1, 1);
        testi(1, 2, 2, 1);
        testi(2, 1, 2, 1);
        testi(2, 2, 1, 1);
    }

    @Test
    public void kolmeYhtasuurta() {
        testi(1, 1, 1, 1);
    }
    
}