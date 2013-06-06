import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.12")
public class KurssinArvosanaTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    private void testi(int pisteet, int arvosana) {
        io.setSysIn(pisteet + "\n");
        KurssinArvosana.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);        
        String[] rivit = tulostus.split("\n");
        String vika = rivit[rivit.length - 1];
        assertTrue("Ohjelmasi tulostaa kurssin arvosanan väärin, kun pistemäärä on " + pisteet, vika.indexOf("" + arvosana) != -1);
    }
    
    @Test
    public void testit() {
        for (int i = 0; i <= 60; i++) {
            if (i < 30) testi(i, 0);
            else if (i > 49) testi(i, 5);
            else testi(i, (i - 30) / 5 + 1);
        }
    }

}