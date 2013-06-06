import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.3")
public class LahtolaskentaTest {

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
        io.setSysIn(luku+ "\n");
        Lahtolaskenta.main(new String[0]);
        String lisa = ", kun luku on " + luku;
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
        testi(5, new String[] {"anna luku 5 4 3 2 1 0"});
    }

    @Test
    public void testi2() {
        testi(10, new String[] {"anna luku 10 9 8 7 6 5 4 3 2 1 0"});
    }
    
    @Test
	public void testi20() {
		testi(20, new String[] {"anna luku 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0"});
	}
	
	@Test
	public void testi21() {
		testi(21, new String[] {"anna luku en jaksa laskea"});
	}

    @Test
    public void testi3() {
        testi(100, new String[] {"anna luku en jaksa laskea"});
    }

}