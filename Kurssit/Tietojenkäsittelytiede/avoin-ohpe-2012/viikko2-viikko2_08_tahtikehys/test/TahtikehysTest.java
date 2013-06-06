import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.8")
public class TahtikehysTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    public void testi(String teksti, String[] oikeat) {
        Tahtikehys.kehysta(teksti);
        String lisa = ", kun teksti on " + teksti;
        String tulostus = io.getSysOut();
        assertTrue("Metodi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Metodi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        
		if (kehysLiianKapea(rivit[0], teksti)) {
			lisa += ". Ota huomioon, että tähtikehyksen tulee olla leveämpi kuin tekstin.";
		}
		else if (tahdetPuuttuvatTekstinLaidoilta(rivit[1], teksti)) {
			lisa += ". Tulosta tähdet myös kehyksen sivuille.";
		}
		else if (valilyonnitPuuttuvatTekstinLaidoilta(rivit[1], teksti)) {
			lisa += ". Tulosta tekstin kanssa samalle riville myös välilyönnit.";
		}
		
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Metodin tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
	
	private boolean kehysLiianKapea(String rivi, String teksti) {
		if (rivi.trim().length() == teksti.length()) {
			return true;
		}
		return false;
	}
	
	private boolean tahdetPuuttuvatTekstinLaidoilta(String rivi, String teksti) {
		if (!rivi.startsWith("*")) {
			return true;
		}
		if (!rivi.endsWith("*")) {
			return true;
		}
		
		return false;
	}
	
	private boolean valilyonnitPuuttuvatTekstinLaidoilta(String rivi, String teksti) {
		if (rivi.contains(" " + teksti + " ")) {
			return false;
		}
		return true;
	}
    
    @Test
    public void testi1() {
        testi("testi", new String[] {"*********", "* testi *", "*********"});
    }

    @Test
    public void testi2() {
        testi("uolevi", new String[] {"**********", "* uolevi *", "**********"});
    }

    @Test
    public void testi3() {
        testi("saippuakauppias", new String[] {"*******************", "* saippuakauppias *", "*******************"});
    }

}