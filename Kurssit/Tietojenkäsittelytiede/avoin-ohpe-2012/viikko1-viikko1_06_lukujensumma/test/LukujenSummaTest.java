import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.6")
public class LukujenSummaTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    private void testi(int a, int b, String viesti) {
        io.setSysIn(a + "\n" + b + "\n");
        LukujenSumma.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);        
        String[] rivit = tulostus.split("\n");
        String vika = rivit[rivit.length - 1];
		assertTrue("Huomaa, että System.out.println(\"teksti\" + 1 + 2) ei summaa lukuja vaan tulostaa ne peräkkäin", !vika.contains("" + a + b));
        assertTrue("Ohjelmasi toimii väärin, kun 1. luku on " + a + " ja 2. luku on " + b, vika.indexOf("" + viesti) != -1);
    }
    
    @Test
    public void testit() {
        testi(8, 8, "16");
        testi(301, 9, "310");
        testi(2, 3, "5");
        testi(100, 101, "201");
    }

}