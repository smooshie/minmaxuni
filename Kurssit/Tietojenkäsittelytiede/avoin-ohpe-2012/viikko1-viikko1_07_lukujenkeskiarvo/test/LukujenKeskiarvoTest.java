import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.7")
public class LukujenKeskiarvoTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    private void testi(int a, int b, String viesti) {
        io.setSysIn(a + "\n" + b + "\n");
        LukujenKeskiarvo.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);        
        String[] rivit = tulostus.split("\n");
        String vika = rivit[rivit.length - 1];
		if (viesti.contains(".")) {
			assertTrue("Huomioithan, että kokonaisluvuilla suoritetun laskun tulos on aina kokonaisluku kun taas keskiarvo voi olla myös liukuluku", vika.contains("."));
		}
        assertTrue("Ohjelmasi toimii väärin, kun 1. luku on " + a + " ja 2. luku on " + b, vika.indexOf("" + viesti) != -1);
    }
    
    @Test
    public void testit() {
        testi(8, 8, "8");
        testi(301, 9, "155");
        testi(2, 3, "2.5");
        testi(100, 101, "100.5");
    }

}