import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.15")
public class PalindromiTest {

    public void testi(String merkkijono, boolean palindromi) {
        if (palindromi) {
            assertTrue("Merkkijonon " + merkkijono + " tulisi olla palindromi", Palindromi.palindromi(merkkijono));
        } else {
            assertTrue("Merkkijonon " + merkkijono + " ei tulisi olla palindromi", !Palindromi.palindromi(merkkijono));            
        }
    }
    
    @Test
    public void testit() {
        testi("enne", true);
        testi("enni", false);
        testi("tullut", true);
        testi("autioitua", true);
        testi("aavikoitua", false);
        testi("reliefpfeiler", true);
        testi("innostunutsonni", true);
        testi("saippuakauppias", true);
        testi("vihanneskauppias", false);
    }

    
}