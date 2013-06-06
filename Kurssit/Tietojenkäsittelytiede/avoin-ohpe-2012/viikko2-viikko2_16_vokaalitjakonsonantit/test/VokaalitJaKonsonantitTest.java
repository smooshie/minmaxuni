import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.16")
public class VokaalitJaKonsonantitTest {

    public void testi(char merkki, boolean vokaali, boolean konsonantti) {
        if (vokaali) {
            assertTrue("Merkin " + merkki + " tulisi olla vokaali", VokaalitJaKonsonantit.vokaali(merkki));
        } else {
            assertTrue("Merkin " + merkki + " ei tulisi olla vokaali", !VokaalitJaKonsonantit.vokaali(merkki));
        }
        if (konsonantti) {
            assertTrue("Merkin " + merkki + " tulisi olla konsonantti", VokaalitJaKonsonantit.konsonantti(merkki));
        } else {
            assertTrue("Merkin " + merkki + " ei tulisi olla konsonantti", !VokaalitJaKonsonantit.konsonantti(merkki));
        }
    }
    
    @Test
    public void testit() {
        testi('a', true, false);
        testi('u', true, false);
        testi('y', true, false);
        testi('e', true, false);
        testi('p', false, true);
        testi('b', false, true);
        testi('q', false, true);
        testi('s', false, true);
        testi('3', false, false);
        testi('#', false, false);
        testi(';', false, false);
    }

    
}