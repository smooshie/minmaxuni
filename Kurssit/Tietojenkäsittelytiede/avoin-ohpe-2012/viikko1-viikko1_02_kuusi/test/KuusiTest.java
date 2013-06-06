import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.2")
public class KuusiTest {

    @Rule
    public MockStdio io = new MockStdio();    
   
 
    @Test
    public void testi() {
        Kuusi.main(new String[0]);
        String tulostus = io.getSysOut();
        String[] rivit = tulostus.split("\n");
        assertTrue("Ohjelma tulostaa väärän määrän rivejä", rivit.length == 6);
        int tahdet = 0;
        for (int i = 0; i < tulostus.length(); i++) {
            if (tulostus.charAt(i) == '*') tahdet++;
        }
        assertTrue("Ohjelma tulostaa väärän määrän tähtiä", tahdet == 26);
		
		int valilyonteja = 0;
		for (int rivi = 0; rivi < rivit.length; rivi++) {
			for (int i = 0; i< rivit[rivi].length(); i++) {
				if (rivit[rivi].charAt(i) == ' ') valilyonteja++;
				else if (rivit[rivi].charAt(i) == '*') break;
			}
		}
		while (valilyonteja > 14) {
			valilyonteja -= rivit.length;
		}
		assertTrue("Ohjelma tulostaa väärän määrän välilyöntejä. Käytä niitä kuusen suoristamiseen", valilyonteja == 14);
		
		int[] tahtiaRivilla = new int[rivit.length];
		int[] valilyontejaRivilla = new int[rivit.length];
		for (int rivi = 0; rivi < rivit.length; rivi ++) {
			for (int i = 0; i < rivit[rivi].length(); i++) {
				if (rivit[rivi].charAt(i) == '*') tahtiaRivilla[rivi]++;
				else if (rivit[rivi].charAt(i) == ' ' && tahtiaRivilla[rivi] == 0) valilyontejaRivilla[rivi]++;
			}
		}
		
		int[] tahtiaPitaisiOllaRivilla = {1, 3, 5, 7, 9, 1};
		int tahtipoikkeamaRivilla = -1;
		
		for (int rivi = 0; rivi < rivit.length; rivi++) {
			if (tahtiaRivilla[rivi] != tahtiaPitaisiOllaRivilla[rivi]) {
				tahtipoikkeamaRivilla = rivi+1;
				break;
			}
		}
		assertTrue("Rivillä " + tahtipoikkeamaRivilla + " on väärä määrä tähtiä", tahtipoikkeamaRivilla == -1);
		
		int[] valilyontejaPitaisiOllaRivilla = {4, 3, 2, 1, 0, 4};
		int valipoikkeamaRivilla = -1;
		
		for (int rivi = 0; rivi < rivit.length; rivi++) {
			if (valilyontejaRivilla[rivi] < valilyontejaPitaisiOllaRivilla[rivi]) {
				valipoikkeamaRivilla = rivi+1;
				break;
			}
		}
		assertTrue("Rivillä " + valipoikkeamaRivilla + " on väärä määrä välilyöntejä", valipoikkeamaRivilla == -1);
    }

}