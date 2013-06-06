import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.6")
public class MerkkienTulostusTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    public void testi(int maara, char merkki, String[] oikeat) {
        MerkkienTulostus.tulostaMerkkeja(maara, merkki);
        String lisa = ", kun merkkien määrä on " + maara + " ja merkki on " + merkki;
        String tulostus = io.getSysOut();
        assertTrue("Metodi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        
        if (rivejaJoillaMerkkia(rivit, merkki) > 1) {
			lisa += ". Tulostathan merkit yhdelle riville.";
		}
        
        assertTrue("Metodi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Metodi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
        
        assertTrue("Tulostuuhan merkkirivin jälkeen rivinvaihto. \nKatso mitä tapahtuisi jos main:ssa kutsuttaisiin tulostaMerkkeja-metodia kahdesti.", tulostus.endsWith("\n"));
    }
	
	public int rivejaJoillaMerkkia(String[] rivit, char merkki) {
		int riveja=0;
		for (String rivi : rivit) {
			if (rivi.contains(merkki+"")) {
				riveja++;
			}
		}
		return riveja;
	}
    
    @Test
    public void testi1() {
        testi(6, '*', new String[] {"******"});
    }

    @Test
    public void testi2() {
        testi(8, '4', new String[] {"44444444"});
    }

    @Test
    public void testi3() {
        testi(13, '+', new String[] {"+++++++++++++"});
    }

}