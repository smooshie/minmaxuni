import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.19")
public class KielenTunnistaminenTest {

    public void testi(String merkkijono, String kieli) {
        assertTrue("Merkkijonon " + merkkijono + " kielen tulisi olla " + kieli, KielenTunnistaminen.kieli(merkkijono) == kieli);
    }
    
    @Test
    public void testit() {
        testi("esimerkki", "suomi");
        testi("voima", "suomi");
        testi("example", "englanti");
        testi("strength", "englanti");
    }

    
}