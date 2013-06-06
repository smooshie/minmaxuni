import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.24")
public class AckermanninFunktioTest {

    private void testi(int m, int n, int tulos) {
        assertTrue("A(" + m + "," + n + "):n tulisi olla " + tulos, AckermanninFunktio.ackermann(m, n) == tulos);
    }
    
    @Test
    public void testit() {
        testi(0, 0, 1);
        testi(3, 1, 13);
        testi(1, 4, 6);
        testi(3, 3, 61);
        testi(4, 0, 13);
        testi(2, 4, 11);
        testi(3, 4, 125);
    }
    
}