import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.25")
public class KirjainnelioTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    public void testi(int kerrokset, String[] oikeat) {
        Kirjainnelio.kirjainnelio(kerrokset);
        String lisa = ", kun kerroksia on " + kerrokset;
        String tulostus = io.getSysOut();
        assertTrue("Metodi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Metodi tulostaa väärän määrän rivejä " + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Metodi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
    
    public void kaanteinenTesti(int kerrokset, String[] vaarat) {
		Kirjainnelio.kirjainnelio(kerrokset);
        String lisa = ", kun kerroksia on " + kerrokset;
		
        String tulostus = io.getSysOut();
        assertTrue("Metodi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Metodi tulostaa väärän määrän rivejä " + lisa, rivit.length == vaarat.length);
        
		lisa += "\n A:n tulee olla sisin merkki.";
		
		for (int i = 0; i < vaarat.length; i++) {
            assertFalse("Metodi tulostus on väärä" + lisa, kelpaa(rivit[i], vaarat[i]));
        }
	}
	
	@Test
	public void kaanteinen() {
		kaanteinenTesti(2, new String[] {"AAA", "ABA", "AAA"});
	}
    
    @Test
    public void testi1() {
        testi(2, new String[] {"BBB", "BAB", "BBB"});
    }

    @Test
    public void testi2() {
        testi(3, new String[] {"CCCCC", "CBBBC", "CBABC", "CBBBC", "CCCCC"});
    }

    @Test
    public void testi3() {
        testi(4, new String[] {"DDDDDDD", "DCCCCCD", "DCBBBCD", "DCBABCD",
                               "DCBBBCD", "DCCCCCD", "DDDDDDD"});
    }

}