import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.9")
public class ErikoistervehdysTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    private void testi(String nimi, String viesti) {
        io.setSysIn(nimi + "\n");
        Erikoistervehdys.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);        
        String[] rivit = tulostus.split("\n");
        String vika = rivit[rivit.length - 1].toLowerCase();
        assertTrue("Ohjelmasi toimii väärin, kun nimi on " + nimi, vika.indexOf("" + viesti) != -1);
    }
    
    @Test
    public void testit() {
        testi("Aapeli", "moro");
        testi("Maija", "hei");
        testi("Uolevi", "hei");
        testi("Jenna", "hei");
        testi("Kalle", "hei");
        testi("Pauli", "hei");
    }

}
