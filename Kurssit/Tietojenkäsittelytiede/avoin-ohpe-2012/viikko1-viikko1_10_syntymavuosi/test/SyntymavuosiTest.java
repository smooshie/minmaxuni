import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.10")
public class SyntymavuosiTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    private void testi(int vuosi, String viesti) {
        io.setSysIn(vuosi + "\n");
        Syntymavuosi.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);        
        String[] rivit = tulostus.split("\n");
        String vika = rivit[rivit.length - 1];
        assertTrue("Ohjelmasi toimii väärin, kun vuosi on " + vuosi, vika.indexOf("" + viesti) != -1);
    }
    
    @Test
    public void testit() {
        for (int i = 1850; i <= 2050; i += 10) {
            if (i < 1900 || i > 2010) {
                testi(i, "narrata");
            } else {
                testi(i, "" + (2012 - i));
            }
        }
    }

}