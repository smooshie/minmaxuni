import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import java.util.*;

@Points("2.20")
public class KorttipakkaTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    @Test
    public void testi() {
        String[] oikeat = new String[52];
        String[] maat = {"pata", "ruutu", "risti", "hertta"};
        int kohta = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                oikeat[kohta++] = maat[i] + "-" + j;
            }
        }
        Arrays.sort(oikeat);
        Korttipakka.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        Arrays.sort(rivit);
        assertTrue("Ohjelmasi tulostaa väärän määrän rivejä", rivit.length == oikeat.length);
        
        String lisa = "";
        if (rivit[0].contains(" -") || rivit[0].contains("- ")) {
        	lisa = ". Huomaa, että maan nimen ja kortin numeron välillä ei ole välilyöntejä.";
        }
        
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Ohjelmasi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
    

}