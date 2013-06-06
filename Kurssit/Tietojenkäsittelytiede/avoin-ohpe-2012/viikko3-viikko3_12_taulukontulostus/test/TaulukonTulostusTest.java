import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("3.12")
public class TaulukonTulostusTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        a = a.toLowerCase();
        a = a.replaceAll("[^A-Za-z0-9,\\[\\] \r\n]", "");
        a = a.replaceAll("[ \r\n]+", " ");
        b = b.toLowerCase();
        b = b.replaceAll("[^A-Za-z0-9,\\[\\] \r\n]", "");
        b = b.replaceAll("[ \r\n]+", " ");        
        return a.trim().equals(b.trim());
    }
    
    public void testi(int[] taulukko, String[] oikeat) {
        TaulukonTulostus.tulostaTaulukko(taulukko);
        String lisa = ", kun taulukon sisältö on: " + oikeat[0];
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
        int a[] = {1, 2, 3};
        testi(a, new String[] {"[1, 2, 3]"});
    }

    @Test
    public void testi2() {
        int a[] = {1, 5, 2, 4, 3};
        testi(a, new String[] {"[1, 5, 2, 4, 3]"});
    }

    @Test
    public void testi3() {
        int a[] = {2, 2, 2, 1};
        testi(a, new String[] {"[2, 2, 2, 1]"});
    }

    
    
}
