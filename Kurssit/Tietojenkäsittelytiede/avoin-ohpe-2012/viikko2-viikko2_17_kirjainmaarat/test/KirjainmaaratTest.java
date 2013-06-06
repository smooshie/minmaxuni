import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.17")
public class KirjainmaaratTest {

    public void testi(String merkkijono, int vokaalit, int konsonantit) {
        assertTrue("Merkkijonossa " + merkkijono + " tulisi olla " + vokaalit + " vokaalia", Kirjainmaarat.vokaalienMaara(merkkijono) == vokaalit);
        assertTrue("Merkkijonossa " + merkkijono + " tulisi olla " + konsonantit + " konsonanttia", Kirjainmaarat.konsonanttienMaara(merkkijono) == konsonantit);
    }
    
    @Test
    public void testit() {
        testi("testi", 2, 3);
        testi("uolevi", 4, 2);
        testi("saippuakauppias", 8, 7);
    }

    
}