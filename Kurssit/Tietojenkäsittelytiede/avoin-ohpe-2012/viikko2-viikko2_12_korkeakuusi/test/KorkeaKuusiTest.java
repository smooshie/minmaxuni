import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.12")
public class KorkeaKuusiTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    public void testi(int korkeus, String[] oikeat) {
        KorkeaKuusi.kuusi(korkeus);
        String lisa = ", kun korkeus on " + korkeus;
        String tulostus = io.getSysOut();
        assertTrue("Metodi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Metodi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Metodi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
    
    @Test
    public void testi1() {
        testi(3, new String[] {"*", "***", "*****", "*"});
    }

    @Test
    public void testi2() {
        testi(4, new String[] {"*", "***", "*****", "*******", "*"});
    }

    @Test
    public void testi3() {
        testi(6, new String[] {"*", "***", "*****", "*******", "*********", "***********", "*"});
    }

}
