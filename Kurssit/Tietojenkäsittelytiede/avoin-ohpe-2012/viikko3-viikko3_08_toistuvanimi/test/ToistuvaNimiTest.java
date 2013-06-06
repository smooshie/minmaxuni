import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("3.8")
public class ToistuvaNimiTest {

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
    
    public void testi(String syote, String[] oikeat) {
        io.setSysIn(syote.replaceAll(", ", "\n") + "\n");
        ToistuvaNimi.main(new String[0]);
        String lisa = ", kun syötteet ovat: " + syote;
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Ohjelmasi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Ohjelmasi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
    
    @Test
    public void testi1() {
        testi("A, B, C, A", new String[] {"anna nimi anna nimi anna nimi anna nimi annoit saman nimen uudestaan"});
    }

    @Test
    public void testi2() {
        testi("A, B, C, B", new String[] {"anna nimi anna nimi anna nimi anna nimi annoit saman nimen uudestaan"});
    }

    @Test
    public void testi3() {
        testi("A, B, C, C", new String[] {"anna nimi anna nimi anna nimi anna nimi annoit saman nimen uudestaan"});
    }

    @Test
    public void testi4() {
        testi("A, A", new String[] {"anna nimi anna nimi annoit saman nimen uudestaan"});
    }
    
    
}