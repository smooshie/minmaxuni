import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("2.13")
public class TulostusVaarinpainTest {

    @Rule
    public MockStdio io = new MockStdio();    
    
    public boolean kelpaa(String a, String b) {
        return a.trim().equals(b.trim());
    }
    
    public void testi(String merkkijono, String[] oikeat) {
    	try {
	        TulostusVaarinpain.tulostaVaarinpain(merkkijono);
		} catch (StringIndexOutOfBoundsException e) {
			fail("Yrität pyytää merkkijonosta merkkiä indeksistä, joka on merkkijonon ulkopuolella.\nJava kertoo tästä ilmoittamalla virheestä:\nStringIndexOutOfBoundsException: " + e.getMessage());
		}
    	
    	String lisa = ", kun merkkijono on " + merkkijono;
        String tulostus = io.getSysOut();
        assertTrue("Metodi ei tulosta mitään" + lisa, tulostus.length() > 0);
        String[] rivit = tulostus.split("\n");
        assertTrue("Metodi tulostaa väärän määrän rivejä" + lisa, rivit.length == oikeat.length);
        for (int i = 0; i < oikeat.length; i++) {
            assertTrue("Metodi tulostus on väärä" + lisa, kelpaa(rivit[i], oikeat[i]));
        }
    }
    
    @Test
    public void testi1() {
        testi("testi", new String[] {"itset"});
    }

    @Test
    public void testi2() {
        testi("uolevi", new String[] {"ivelou"});
    }

    @Test
    public void testi3() {
        testi("aybabtu", new String[] {"utbabya"});
    }

}