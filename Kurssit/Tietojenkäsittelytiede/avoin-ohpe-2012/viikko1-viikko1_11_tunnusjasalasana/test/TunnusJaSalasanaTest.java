import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.11")
public class TunnusJaSalasanaTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    private void testi(String tunnus, String salasana, String viesti) {
        io.setSysIn(tunnus + "\n" + salasana + "\n");
        TunnusJaSalasana.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelmasi ei tulosta mitään", tulostus.length() > 0);        
        String[] rivit = tulostus.split("\n");
        String vika = rivit[rivit.length - 1];
		
		String lisailmoitus = "";
		if ((tunnus.equals("aapeli") || tunnus.equals("maija")) && (salasana.equals("kana") || salasana.equals("kissa"))) {
			lisailmoitus = ". Muistathan, että aapelin salasanaksi ei käy maijan salasana ja päinvastoin";
		}
		
        assertTrue("Ohjelmasi toimii väärin, kun tunnus on " + tunnus + " ja salasana on " + salasana + lisailmoitus, vika.toLowerCase().indexOf("" + viesti) != -1);
    }
    
    @Test
    public void testit() {
        testi("aapeli", "kissa", "tervetuloa");
        testi("maija", "kana", "tervetuloa");
        testi("aapeli", "kana", "virhe");
        testi("maija", "kissa", "virhe");
        testi("uolevi", "kissa", "virhe");
        testi("aapeli", "rotta", "virhe");
        testi("maija", "siili", "virhe");
		
		testi("aapeli", "kissakana", "virhe");
    }

}