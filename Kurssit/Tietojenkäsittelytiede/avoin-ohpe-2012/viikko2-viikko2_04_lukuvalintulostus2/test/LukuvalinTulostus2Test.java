import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.4")
public class LukuvalinTulostus2Test {

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
    
    public void testi(int alku, int loppu, String[] oikeat) {
        io.setSysIn(alku + "\n" + loppu + "\n");
        LukuvalinTulostus2.main(new String[0]);
        String lisa = ", kun luku 1 on " + alku + " ja luku 2 on " + loppu;
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
        testi(3, 7, new String[] {"anna luku 1 anna luku 2 3", "4", "5", "6", "7"});
    }

    @Test
    public void testi2() {
        testi(7, 3, new String[] {"anna luku 1 anna luku 2 7", "6", "5", "4", "3"});
    }

    @Test
    public void testi3() {
        testi(3, 3, new String[] {"anna luku 1 anna luku 2 3"});
    }

}