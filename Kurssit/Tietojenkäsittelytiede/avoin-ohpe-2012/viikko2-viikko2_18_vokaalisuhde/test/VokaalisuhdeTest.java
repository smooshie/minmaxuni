import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.18")
public class VokaalisuhdeTest {

    public void testi(String merkkijono, int suhde) {
        assertTrue("Merkkijonon " + merkkijono + " vokaalisuhteen tulisi olla noin " + suhde, (int)Vokaalisuhde.vokaalisuhde(merkkijono) == suhde);
    }
    
    @Test
    public void testit() {
        testi("testi", 40);
        testi("aybabtu", 57);
        testi("saippuakauppias", 53);
    }

    
}