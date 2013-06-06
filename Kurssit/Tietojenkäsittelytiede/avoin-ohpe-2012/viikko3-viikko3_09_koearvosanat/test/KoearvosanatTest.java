import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("3.9")
public class KoearvosanatTest {

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
        Koearvosanat.main(new String[0]);
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
        testi("5, 0", new String[] {"anna arvosana anna arvosana tilasto",
            "5 1 kpl", "4 0 kpl", "3 0 kpl", "2 0 kpl", "1 0 kpl"});
    }

    @Test
    public void testi2() {
        testi("5, 2, 2, 5, 1, 0", new String[] {"anna arvosana anna arvosana anna arvosana anna arvosana anna arvosana anna arvosana tilasto",
            "5 2 kpl", "4 0 kpl", "3 0 kpl", "2 2 kpl", "1 1 kpl"});
    }

    @Test
    public void testi3() {
        testi("5, 4, 3, 2, 1, 0", new String[] {"anna arvosana anna arvosana anna arvosana anna arvosana anna arvosana anna arvosana tilasto",
            "5 1 kpl", "4 1 kpl", "3 1 kpl", "2 1 kpl", "1 1 kpl"});
    }

    
    
}
