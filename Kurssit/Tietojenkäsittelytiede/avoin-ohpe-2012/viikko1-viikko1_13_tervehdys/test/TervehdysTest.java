import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.13")
public class TervehdysTest {

    @Rule
    public MockStdio io = new MockStdio();   
	
	private void tervehdi(String nimi) {
		Tervehdys.tervehdi(nimi);
        String tulostus = io.getSysOut();
        assertTrue("Metodi 'tervehdi' ei tulosta mitään", tulostus.length() > 0);
		
		assertTrue("Käytäthän metodissa sille annettua parametriä", tulostus.contains(nimi));
		
        String[] rivit = tulostus.split("\n");
        int maara = rivit.length;
        assertTrue("Metodi 'tervehdi' tulostaa " + maara + " riviä 3:n sijasta:\n" + tulostus, maara == 3);
        for (int i = 0; i < 3; i++) {
            assertTrue("Metodi 'tervehdi' tulostaa väärin " + (i + 1) + ". rivin.\nRivi oli '" + rivit[i] + "' kun sen haluttiin olevan 'Hei, " + nimi + "!'", rivit[i].trim().equals("Hei, " + nimi + "!"));
        }
	} 
    
    @Test
    public void tervehdiAapelia() {
		tervehdi("Aapeli");
    }
	
	@Test
	public void tervehdiOulevia() {
		tervehdi("Uolevi");
	}
	
	@Test
	public void tervehdiMaijaa() {
		tervehdi("Maija");
	}

}