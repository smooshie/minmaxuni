import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.9")
public class MerkkikehysTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    public void testi(String teksti, char merkki, String[] oikeat) {
        Merkkikehys.kehysta(teksti, merkki);
        String lisa = ", kun teksti on " + teksti + " ja merkki on " + merkki;
        String tulostus = io.getSysOut();
        assertTrue("Metodi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        
        if (kehysLiianKapea(rivit[0], teksti)) {
			lisa += ". Ota huomioon, että merkkikehyksen tulee olla leveämpi kuin tekstin.";
		}
		else if (merkitPuuttuvatTekstinLaidoilta(rivit[1], teksti, merkki)) {
			lisa += ". Tulosta merkit myös kehyksen sivuille.";
		}
		else if (valilyonnitPuuttuvatTekstinLaidoilta(rivit[1], teksti)) {
			lisa += ". Tulosta tekstin kanssa samalle riville myös välilyönnit.";
		}
		
        assertTrue("Metodi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
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
	
	private boolean merkitPuuttuvatTekstinLaidoilta(String rivi, String teksti, char merkki) {
		if (!rivi.startsWith(merkki+"")) {
			return true;
		}
		if (!rivi.endsWith(merkki+"")) {
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
        testi("testi", '*', new String[] {"*********", "* testi *", "*********"});
    }

    @Test
    public void testi2() {
        testi("uolevi", '3', new String[] {"3333333333", "3 uolevi 3", "3333333333"});
    }

    @Test
    public void testi3() {
        testi("saippuakauppias", '+', new String[] {"+++++++++++++++++++", "+ saippuakauppias +", "+++++++++++++++++++"});
    }

}