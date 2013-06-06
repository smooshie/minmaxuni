import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("3.2")
public class IanKysyminenTest {

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
    
    public void testi(String syote, String[] oikeat) {
        io.setSysIn(syote.replaceAll(", ", "\n") + "\n");
        IanKysyminen.main(new String[0]);
        String lisa = ", kun syötteet ovat: " + syote;
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Ohjelmasi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Ohjelmasi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
    
    @Test
    public void testi1() {
        testi("200, 150, 100", new String[] {"anna ikä älä huijaa", "anna ikä älä huijaa", "anna ikä kiitos"});
    }

    @Test
    public void testi2() {
        testi("-5, 200, -5, 200, 50", new String[] {"anna ikä älä huijaa", "anna ikä älä huijaa", "anna ikä älä huijaa", "anna ikä älä huijaa", "anna ikä kiitos"});
    }

    @Test
    public void testi3() {
        testi("12", new String[] {"anna ikä kiitos"});
    }

}
