import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.5")
public class TahtienTulostusTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    public void testi(int maara, String[] oikeat) {
        TahtienTulostus.tulostaTahtia(maara);
        String lisa = ", kun tähtien määrä on " + maara;
        String tulostus = io.getSysOut();
        assertTrue("Metodi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        
		if (rivejaJoillaTahtia(rivit) > 1) {
			lisa += ". Tulostathan tähdet yhdelle riville.";
		}
        
        assertTrue("Metodi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Metodi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
        
        assertTrue("Tulostuuhan tähtirivin jälkeen rivinvaihto. \nKatso mitä tapahtuisi jos main:ssa kutsuttaisiin tulostaTahtia-metodia kahdesti.", tulostus.endsWith("\n"));
    }
	
	public int rivejaJoillaTahtia(String[] rivit) {
		int riveja=0;
		for (String rivi : rivit) {
			if (rivi.contains("*")) {
				riveja++;
			}
		}
		return riveja;
	}
    
    @Test
    public void testi1() {
        testi(6, new String[] {"******"});
    }

    @Test
    public void testi2() {
        testi(8, new String[] {"********"});
    }

    @Test
    public void testi3() {
        testi(4, new String[] {"****"});
    }

}