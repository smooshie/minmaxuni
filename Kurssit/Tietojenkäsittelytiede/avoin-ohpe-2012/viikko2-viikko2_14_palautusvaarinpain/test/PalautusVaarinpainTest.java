import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.14")
public class PalautusVaarinpainTest {

    public void testi(String merkkijono, String vaarinpain) {
        assertTrue("Merkkijonon " + merkkijono + " tulisi olla väärinpäin " + vaarinpain, PalautusVaarinpain.vaarinpain(merkkijono).equals(vaarinpain));
    }
    
    @Test
    public void testit() {
        testi("testi", "itset");
        testi("aybabtu", "utbabya");
        testi("uolevi", "ivelou");
        testi("saippuakauppias", "saippuakauppias");
    }

    
}