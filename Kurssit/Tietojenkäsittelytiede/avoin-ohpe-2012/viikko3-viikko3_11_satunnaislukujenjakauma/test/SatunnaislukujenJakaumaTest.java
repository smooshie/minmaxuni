import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("3.11")
public class SatunnaislukujenJakaumaTest {

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
    
    @Test
    public void testi() {
        SatunnaislukujenJakauma.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Ohjelmasi tulostaa väärän määrän rivejä", rivit.length == 6);
        for (int i = 0; i < rivit.length; i++) {
            String[] osat = rivit[i].split(" ");
            int luku = Integer.parseInt(osat[1]);
            assertTrue("Ohjelmasi heittää oudon vähän silmälukua " + (i + 1), luku >= 164000);
            assertTrue("Ohjelmasi heittää oudon paljon silmälukua " + (i + 1), luku <= 168000);
        }
    }
    

    
    
}