import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("3.21")
public class LukujenKirjoitusTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        a = a.toLowerCase();
        a = a.replaceAll("[^A-Za-z0-9 \r\n]", "");
        a = a.replaceAll("[ \r\n]+", " ");
        b = b.toLowerCase();
        b = b.replaceAll("[^A-Za-z0-9 \r\n]", "");
        b = b.replaceAll("[ \r\n]+", " ");        
        return a.trim().equals(b.trim());
    }
    
    public void testi(int luku, String[] oikeat) {
        LukujenKirjoitus.numerot(luku);
        String lisa = ", kun luku on " + luku;
        String tulostus = io.getSysOut();
        assertTrue("Metodisi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Metodisi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Metodisi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
    
    @Test
    public void testi1() {
        int luku = 50;
        testi(luku, new String[] {"5 15 15 15 15 6 5 5 5 5"});
    }

    @Test
    public void testi2() {
        int luku = 123;
        testi(luku, new String[] {"22 57 27 23 22 22 22 22 22 22"});
    }
    
    @Test
    public void testi3() {
        int luku = 777;
        testi(luku, new String[] {"147 258 258 258 258 258 258 234 147 147"});
    }

    @Test
    public void testi4() {
        int luku = 12345;
        testi(luku, new String[] {"4664 8121 5121 4721 4671 4665 4664 4664 4664 4664"});
    }

    
}
