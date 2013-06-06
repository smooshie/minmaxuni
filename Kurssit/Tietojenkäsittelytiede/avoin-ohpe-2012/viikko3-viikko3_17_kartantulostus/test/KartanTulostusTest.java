import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("3.17")
public class KartanTulostusTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        a = a.toLowerCase();
        a = a.replaceAll("[^A-Za-z0-9#.@ \r\n]", "");
        a = a.replaceAll("[ \r\n]+", " ");
        b = b.toLowerCase();
        b = b.replaceAll("[^A-Za-z0-9#.@ \r\n]", "");
        b = b.replaceAll("[ \r\n]+", " ");        
        return a.trim().equals(b.trim());
    }
    
    public void testi(int[][] taulukko, String tulos, String[] oikeat) {
        KartanTulostus.tulostaKartta(taulukko);
        String lisa = ", kun taulukon sisältö on: " + tulos;
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
        int[][] kartta = {{1, 1}, {1, 1}};
        String tulos = "[[1, 1], [1, 1]]";
        testi(kartta, tulos, new String[] {"##", "##"});
    }

    @Test
    public void testi2() {
        int[][] kartta = {{1, 1, 1, 1}, {1, 0, 2, 1}, {1, 1, 1, 1}};
        String tulos = "[[1, 1], [1, 1]]";
        testi(kartta, tulos, new String[] {"####", "#.@#", "####"});
    }

    @Test
    public void testi3() {
        int[][] kartta = {{1, 1, 1}, {1, 0, 1}, {1, 0, 1}, {1, 2, 1}, {1, 1, 1}};
        String tulos = "[[1, 1], [1, 1]]";
        testi(kartta, tulos, new String[] {"###", "#.#", "#.#", "#@#", "###"});
    }
    
    
}
