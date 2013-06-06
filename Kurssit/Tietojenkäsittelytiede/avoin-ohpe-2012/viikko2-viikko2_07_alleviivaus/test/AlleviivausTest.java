import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.7")
public class AlleviivausTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    public void testi(String teksti, String[] oikeat) {
        Alleviivaus.alleviivaa(teksti);
        String lisa = ", kun teksti on " + teksti;
        String tulostus = io.getSysOut();
        assertTrue("Metodi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
		
		if (tekstiJaAlleviivausSamallaRivilla(rivit, teksti)) {
			lisa += ". Ethän tulosta alleviivausta samalle riville tekstin kanssa.";
		}
		else if (alleviivaustaUseallaRivilla(rivit)) {
			lisa += ". Tulostathan alleviivauksen kaikki merkit samalle riville.";
		}
		
        assertTrue("Metodi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Metodi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
	
	private boolean tekstiJaAlleviivausSamallaRivilla(String[] rivit, String teksti) {
		for (String rivi : rivit) {
			if (rivi.contains(teksti)) {
				if (rivi.contains("=")) {
					return true;
				}
				return false;
			}
		}
		//liikaa virheitä, tulostetaan kuvaavampia virheilmoituksia
		return false;
	}
	
	private boolean alleviivaustaUseallaRivilla(String[] rivit) {
		int riveja = 0;
		for (String rivi : rivit) {
			if (rivi.contains("=")) {
				riveja++;
			}
		}
		
		if (riveja > 1) {
			return true;
		}
		return false;
	}
    
    @Test
    public void testi1() {
        testi("testi", new String[] {"testi", "====="});
    }

    @Test
    public void testi2() {
        testi("uolevi", new String[] {"uolevi", "======"});
    }

    @Test
    public void testi3() {
        testi("saippuakauppias", new String[] {"saippuakauppias", "==============="});
    }

}