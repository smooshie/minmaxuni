/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodAndReturnType;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef0;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef1;
import java.lang.reflect.Method;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Usagi-chan
 */
public class HattuTest {

	private String luokka = "Hattu";

	@Before
	public void setUp() {
		try {
			Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		} catch (Throwable t) {
			fail("Ei löytynyt luokkaa " + luokka + ". Eihän " + luokka + "si ota parametreja.");
		}
	}

	@Test
	@Points("1.4.1")
	public void nostaPalloToimii() throws Throwable {
		Object hattu = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		MethodRef0<Object, Integer> nostaPallo = Reflex.reflect(luokka).method("nostaPallo").returning(int.class).takingNoParams();
		
		int[] pallojenMaarat = new int[3];
		for (int i=0; i<30000; i++) {
			int pallo = nostaPallo.invokeOn(hattu);
			try {
				pallojenMaarat[pallo-1] = pallojenMaarat[pallo-1] + 1;
			} catch (Exception e) {
				fail("Pallojen numeroiden tuli olla väliltä 1-3. Ongelma tuli nostaPallo-metodin yhteydessä.");
			}
		}
		
		if (Math.abs(10000-pallojenMaarat[0]) > 500 || Math.abs(10000-pallojenMaarat[1]) > 500 || Math.abs(10000-pallojenMaarat[2]) > 500) {
			fail("Joitakin palloja näyttää tulevan enemmän kuin muita. Pallot 1: " + pallojenMaarat[0] + "kpl, 2: " + pallojenMaarat[1] + "kpl, 3: " + pallojenMaarat[2] + "kpl");
		}
	}
	
	@Test
	@Points("1.4.1")
	public void lisaaPalloToimii() throws Throwable {
		lisaaPalloja(1, 10, 0.02, 0.5, 0.25, 0.25);
		lisaaPalloja(1, 100, 0.01, 110.0/130, 10.0/130, 10.0/130);
		lisaaPalloja(1, 990, 0.003, 1000.0/1020, 10.0/1020, 10.0/1020);
		
		lisaaPalloja(2, 10, 0.02, 0.25, 0.5, 0.25);
		lisaaPalloja(2, 100, 0.01, 10.0/130, 110.0/130, 10.0/130);
		lisaaPalloja(2, 990, 0.003, 10.0/1020, 1000.0/1020, 10.0/1020);
		
		lisaaPalloja(3, 10, 0.02, 0.25, 0.25, 0.5);
		lisaaPalloja(3, 100, 0.01, 10.0/130, 10.0/130, 110.0/130);
		lisaaPalloja(3, 990, 0.003, 10.0/1020, 10.0/1020, 1000.0/1020);
		
		lisaaUseaaPalloa(1, 10, 2, 10, 0.015, 20.0/50, 20.0/50, 10.0/50);
		lisaaUseaaPalloa(2, 600, 3, 990, 0.01, 10.0/1620, 610.0/1620, 1000.0/1620);
	}
	
	private void lisaaPalloja(int pallo, int maara, double suurinSallittuPoikkeama, double... todennakoisyydet) throws Throwable {
		Object hattu = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		MethodRef1<Object, Void, Integer> lisaaPallo = Reflex.reflect(luokka).method("lisaaPallo").returningVoid().taking(int.class);
		
		for (int i = 0; i < maara; i++) {
			lisaaPallo.invokeOn(hattu, pallo);
		}
		
		int[] pallojenMaarat = pallojenMaarat(hattu, 30000);
		String tapaus = "Palloa " + pallo + " koitettiin lisätä " + maara + " kappaletta.";
		String tilasto = "Nostettujen pallojen määrät: 1: " + pallojenMaarat[0] + " 2: " + pallojenMaarat[1] + " 3: " + pallojenMaarat[2];
		for (int i = 0; i < 3; i++) {
			if (Math.abs(pallojenMaarat[i]/30000.0 - todennakoisyydet[i]) > suurinSallittuPoikkeama) {
				fail("Palloa " + (i+1) + " näyttää tulevan poikkeavasti.\n" + tapaus + "\n" + tilasto);
			}
		}
	}
	
	private void lisaaUseaaPalloa(int pallo1, int maara1, int pallo2, int maara2, double suurinSallittuPoikkeama, double... todennakoisyydet) throws Throwable {
		Object hattu = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		MethodRef1<Object, Void, Integer> lisaaPallo = Reflex.reflect(luokka).method("lisaaPallo").returningVoid().taking(int.class);
		
		for (int i = 0; i < maara1; i++) {
			lisaaPallo.invokeOn(hattu, pallo1);
		}
		for (int i = 0; i < maara2; i++) {
			lisaaPallo.invokeOn(hattu, pallo2);
		}
		
		int[] pallojenMaarat = pallojenMaarat(hattu, 30000);
		String tapaus = "Palloa " + pallo1 + " koitettiin lisätä " + maara1 + " kappaletta ja palloa " + pallo2 + " koitettiin lisätä " + maara2 + " kappaletta.";
		if (maara1 +  maara2 > 1000) {
			tapaus += "\nHuomaa, että palloja voi hatussa olla yhteensä yli 1000 kunhan yhtä palloa ei ole enempää kuin 1000.";
		}
		String tilasto = "Nostettujen pallojen määrät: 1: " + pallojenMaarat[0] + " 2: " + pallojenMaarat[1] + " 3: " + pallojenMaarat[2];
		for (int i = 0; i < 3; i++) {
			if (Math.abs(pallojenMaarat[i]/30000.0 - todennakoisyydet[i]) > suurinSallittuPoikkeama) {
				fail("Palloa " + (i+1) + " näyttää tulevan poikkeavasti.\n" + tapaus + "\n" + tilasto);
			}
		}
	}
	
	private int[] pallojenMaarat(Object hattu, int nostoja) throws Throwable {
		MethodRef0<Object, Integer> nostaPallo = Reflex.reflect(luokka).method("nostaPallo").returning(int.class).takingNoParams();
		
		int[] pallojenMaarat = new int[3];
		
		for (int i = 0; i < nostoja; i++) {
			int pallo = nostaPallo.invokeOn(hattu);
			try {
				pallojenMaarat[pallo-1] = pallojenMaarat[pallo-1] + 1;
			} catch (Exception e) {
				fail("Hatun pallojen tulee olla välillä 1-3");
			}
		}
		
		return pallojenMaarat;
	}
	
	@Test
	@Points("1.4.1")
	public void poistaPalloToimii() throws Throwable {
		poistaPalloja(1, 5, 0.02, 5.0/25, 10.0/25, 10.0/25);
		poistaPalloja(2, 5, 0.02, 10.0/25, 5.0/25, 10.0/25);
		poistaPalloja(3, 5, 0.02, 10.0/25, 10.0/25, 5.0/25);
		
		poistaPalloja(1, 2, 0.015, 8.0/28, 10.0/28, 10.0/28);
		poistaPalloja(2, 7, 0.015, 10.0/23, 3.0/23, 10.0/23);
		poistaPalloja(3, 4, 0.015, 10.0/26, 10.0/26, 6.0/26);
		
		for (int i=1; i<=3; i++) {
			kaikkienPallojenPoistoEiOnnistu(i);
		}
	}
	
	private double poistaPalloja(int pallo, int maara, double suurinSallittuPoikkeama, double... todennakoisyydet) throws Throwable {
		Object hattu = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		MethodRef1<Object, Void, Integer> poistaPallo = Reflex.reflect(luokka).method("poistaPallo").returningVoid().taking(int.class);
		
		for (int i = 0; i < maara; i++) {
			poistaPallo.invokeOn(hattu, pallo);
		}
		
		double suurinPoikkeama = 0;
		
		int[] pallojenMaarat = pallojenMaarat(hattu, 30000);
		String tapaus = "Palloa " + pallo + " koitettiin poistaa " + maara + " kappaletta.";
		String tilasto = "Nostettujen pallojen määrät: 1: " + pallojenMaarat[0] + " 2: " + pallojenMaarat[1] + " 3: " + pallojenMaarat[2];
		for (int i = 0; i < 3; i++) {
			if (Math.abs(pallojenMaarat[i]/30000.0 - todennakoisyydet[i]) > suurinSallittuPoikkeama) {
				fail("Palloa " + (i+1) + " näyttää tulevan poikkeavasti.\n" + tapaus + "\n" + tilasto);
			}
			if (Math.abs(pallojenMaarat[i]/30000.0 - todennakoisyydet[i]) > suurinPoikkeama) {
				suurinPoikkeama = Math.abs(pallojenMaarat[i]/30000.0 - todennakoisyydet[i]);
			}
		}
		
		return suurinPoikkeama;
	}
	
	private void kaikkienPallojenPoistoEiOnnistu(int pallo) throws Throwable {
		Object hattu = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		MethodRef1<Object, Void, Integer> poistaPallo = Reflex.reflect(luokka).method("poistaPallo").returningVoid().taking(int.class);
		
		for (int i = 0; i < 10; i++) {
			poistaPallo.invokeOn(hattu, pallo);
		}
		
		MethodRef0<Object, Integer> nostaPallo = Reflex.reflect(luokka).method("nostaPallo").returning(int.class).takingNoParams();
		for (int i = 0; i < 1000; i++) {
			int nostettuPallo = nostaPallo.invokeOn(hattu);
			if (nostettuPallo < 1 || nostettuPallo > 3) {
				fail("Pallojen numeroiden tulisi olla välillä 1-3.");
			}
			if (nostettuPallo == pallo) {
				return;
			}
		}
		
		fail("Palloa " + pallo + " ei löytynyt hatusta kun kaikki koitettiin poistaa kaikki kyseiset pallot. Yhden pallon pitäisi aina jäädä jäljelle.");
	}
}
