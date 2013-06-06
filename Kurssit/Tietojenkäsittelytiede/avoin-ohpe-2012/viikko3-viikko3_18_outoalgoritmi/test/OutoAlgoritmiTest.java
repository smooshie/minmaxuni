import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("3.18")
public class OutoAlgoritmiTest {

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
        OutoAlgoritmi.tulostaOuto(luku);
        String lisa = ", kun aloitusluku on " + luku;
        String tulostus = io.getSysOut();
        assertTrue("Metodisi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Metodisi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Metodisi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
    
    @Test
    public void testi1() {
        int luku = 40;
        testi(luku, new String[] {"40 20 10 5 16 8 4 2 1"});
    }
   
    @Test
    public void testi2() {
        int luku = 53;
        testi(luku, new String[] {"53 160 80 40 20 10 5 16 8 4 2 1"});
    }

    @Test
    public void testi3() {
        int luku = 60;
        testi(luku, new String[] {"60 30 15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1"});
    }

    @Test
    public void testi4() {
        int luku = 81;
        testi(luku, new String[] {"81 244 122 61 184 92 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1"});
    }
    
    
}