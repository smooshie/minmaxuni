import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.8")
public class YmpyranPintaalaTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    private void testi(int sade, String viesti) {
        io.setSysIn(sade + "\n");
        YmpyranPintaala.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);        
        String[] rivit = tulostus.split("\n");
        String vika = rivit[rivit.length - 1];
        assertTrue("Ohjelmasi toimii väärin, kun säde on " + sade, vika.indexOf("" + viesti) != -1);
    }
    
    @Test
    public void testit() {
        testi(7, "153.93");
        testi(16, "804.24");
        testi(20, "1256.63");
    }

}