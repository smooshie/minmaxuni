import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.21")
public class PINKooditTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    @Test
    public void testi() {
        String[] oikeat = new String[9*9*9*9];
        int kohta = 0;
        for (int i = 1111; i <= 9999; i++) {
            String koodi = "" + i;
            if (koodi.indexOf('0') == -1) {
                oikeat[kohta++] = koodi;
            }
        }
        PINKoodit.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Ohjelmasi tulostaa väärän määrän rivejä", rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Ohjelmasi tulostus on väärä", kelpaa(rivit[i], oikeat[i]));
        }
    }
    

}