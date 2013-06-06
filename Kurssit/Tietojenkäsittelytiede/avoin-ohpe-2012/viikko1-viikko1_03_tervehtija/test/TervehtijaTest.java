import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.3")
public class TervehtijaTest {

    @Rule
    public MockStdio io = new MockStdio();    
   
 
    @Test
    public void testi() {
        Tervehtija.main(new String[0]);
        String tulostus = io.getSysOut();
        String[] rivit = tulostus.split("\n");
        assertTrue("Ohjelma tulostaa väärän määrän rivejä", rivit.length == 3);
        assertTrue("Ohjelman tulostus on puutteellinen", tulostus.indexOf("\\o/") != -1);
        assertTrue("Ohjelman tulostus on puutteellinen", tulostus.indexOf("|") != -1);
        assertTrue("Ohjelman tulostus on puutteellinen", tulostus.indexOf("/ \\") != -1);
		
		assertTrue("Ohjelman tulosteesta puuttuu Heippa!", tulostus.toLowerCase().contains("heippa!"));
		
		int hipsujenMaara = 0;
		for (int i = 0; i < tulostus.length(); i++) {
			if (tulostus.charAt(i) == '"') hipsujenMaara++;
		}
		assertTrue("Ympäröithän tervehdyksen heittomerkkeihin", hipsujenMaara == 2);
    }

}