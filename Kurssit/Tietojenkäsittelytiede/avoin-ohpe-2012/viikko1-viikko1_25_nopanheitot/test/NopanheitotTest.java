import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.25")
public class NopanheitotTest {

    private void testi(int n, int tulos) {
        assertTrue("Luku " + n + " tulisi voida muodostaa " + tulos + " tavalla", Nopanheitot.nopat(n) == tulos);
    }
    
    @Test
    public void testit() {
        testi(2, 2);
        testi(5, 16);
        testi(9, 248);
        testi(13, 3840);
        testi(17, 59448);
    }
    
}