import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("3.7")
public class NimetJarjestyksessaTest {

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
        NimetJarjestyksessa.main(new String[0]);
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
        testi("3, A, C, B", new String[] {"montako nimeä anna nimi anna nimi anna nimi nimet järjestyksessä",
            "A", "B", "C"});
    }

    @Test
    public void testi2() {
        testi("5, E, D, C, B, A", new String[] {"montako nimeä anna nimi anna nimi anna nimi anna nimi anna nimi nimet järjestyksessä",
            "A", "B", "C", "D", "E"});
    }

    @Test
    public void testi3() {
        testi("1, A", new String[] {"montako nimeä anna nimi nimet järjestyksessä",
            "A"});
    }

}
