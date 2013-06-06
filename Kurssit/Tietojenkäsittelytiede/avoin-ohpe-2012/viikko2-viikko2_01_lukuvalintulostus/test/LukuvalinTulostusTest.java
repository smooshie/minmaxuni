import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.1")
public class LukuvalinTulostusTest {

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
    
    public void testi(int alaraja, int ylaraja, String[] oikeat) {
        io.setSysIn(alaraja + "\n" + ylaraja + "\n");
        LukuvalinTulostus.main(new String[0]);
        String lisa = ", kun alaraja on " + alaraja + " ja yläraja on " + ylaraja;
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
        testi(3, 7, new String[] {"anna alaraja anna yläraja 3", "4", "5", "6", "7"});
    }

    @Test
    public void testi2() {
        testi(3, 8, new String[] {"anna alaraja anna yläraja 3", "4", "5", "6", "7", "8"});
    }

    @Test
    public void testi3() {
        testi(3, 3, new String[] {"anna alaraja anna yläraja 3"});
    }

}