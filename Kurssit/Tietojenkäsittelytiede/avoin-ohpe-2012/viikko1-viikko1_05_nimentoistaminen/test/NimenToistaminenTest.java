import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.5")
public class NimenToistaminenTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    @Test
    public void uolevi() {
        io.setSysIn("Uolevi\n");
        NimenToistaminen.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelma ei tulosta mitään", tulostus.length() > 0);
		
		String lisailmoitus = "";
		if (tulostus.toLowerCase().startsWith("anna nimi:\n") || tulostus.toLowerCase().startsWith("anna nimi: \n") ) {
			tulostus = tulostus.substring( tulostus.indexOf("\n") + 1 );
		}
		else if (tulostus.toLowerCase().lastIndexOf("\n", tulostus.toLowerCase().indexOf("hei")) != -1) {
			lisailmoitus = "\nTulostaahan ohjelmasi kysymyksen ja vastauksen samalle riville?";
		}
        String[] rivit = tulostus.split("\n");
        int maara = rivit.length;
        assertTrue("Ohjelma tulostaa " + maara + " riviä 3:n sijasta" + lisailmoitus, maara == 3);
        for (int i = 0; i < 3; i++) {
            assertTrue("Ohjelma tulostaa väärin " + (i + 1) + ". rivin", rivit[i].trim().indexOf("Uolevi") != -1);
        }
    }

}