/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import fi.helsinki.cs.tmc.edutestutils.Reflex.ClassRef;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef1;
import java.lang.reflect.Constructor;
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
public class TekoalynOpettaminenTest {
	private String luokka = "OppivaTekoaly";
	private String mainLuokka = "Main";
	private String tarkistus = "54a1 19a2 5a0 36a3 40a3 25a0 10a1 38a1 17a0 48a3 61a0 8a3 1a0 66a1 27a2 4a3 33a0 60a3 45a0 58a1 43a2 2a1 13a0 24a3 63a2 7a2 41a0 16a3 31a2 12a3 22a1 68a3 30a1 50a1";
	
	public TekoalynOpettaminenTest() {
	}
	
	@Before
	public void setUp() throws Throwable {
		try {
			Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		} catch (Throwable t) {
			fail("Ei löytynyt luokkaa " + luokka + ". Eihän " + luokka + "si ota parametreja.");
		}
		
		try {
			Reflex.reflect(mainLuokka).constructor().takingNoParams().invoke();
		} catch (Throwable t) {
			fail("Ei löytynyt luokkaa " + mainLuokka);
		}
	}
	
	
	
	@Test
	@Points("1.4.3")
	public void opettamismetodiLoytyy() {
		Class oppivaTekoalyLuokka = Reflex.reflect(luokka).getReferencedClass();
		loytyykoMetodi("opettaminen", oppivaTekoalyLuokka, "OppivaTekoaly", oppivaTekoalyLuokka, "OppivaTekoaly");
	}
	
	private void loytyykoMetodi(String metodinNimi, Class parametri, String parametrinNimi, Class parametri2, String parametrin2Nimi) {
		ClassRef parametriluokka = Reflex.reflect("OppivaTekoaly");
		Reflex.MethodRef2 metodi = Reflex.reflect(mainLuokka).staticMethod(metodinNimi).returningVoid().taking(parametriluokka.getReferencedClass(), parametriluokka.getReferencedClass());
		if (metodi.exists()) {
			return;
		}
		
		Reflex.MethodRef0<Object, Void> parametritonMetodi = Reflex.reflect(mainLuokka).method(metodinNimi).returningVoid().takingNoParams();
		if (parametritonMetodi.exists()) {
			fail("Luokan " + mainLuokka + " metodin " + metodinNimi + " tulee ottaa parametriksi kaksi OppivaTekoalya.");
		}
		fail("Luokalla " + mainLuokka + " tulee olla metodi " + metodinNimi);
	}
	
	@Test
	@Points("1.4.3")
	public void opettaminenToimii() throws Throwable {
		Object tekoaly1 = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		Object tekoaly2 = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		
		Object main = Reflex.reflect(mainLuokka).constructor().takingNoParams().invoke();
		
		ClassRef parametriluokka = Reflex.reflect("OppivaTekoaly");
		
		Class mainLuokka = ReflectionUtils.findClass("Main");
		Constructor konstruktori = ReflectionUtils.requireConstructor(mainLuokka);
		Object o = ReflectionUtils.invokeConstructor(konstruktori);
		Method metodi = ReflectionUtils.requireMethod(true, mainLuokka, Void.TYPE, "opettaminen", parametriluokka.getReferencedClass(), parametriluokka.getReferencedClass());
		
		ReflectionUtils.invokeMethod(Void.TYPE, metodi, mainLuokka, tekoaly1, tekoaly2);
		
		String[] lista = tarkistus.split(" ");
		for (String tapaus : lista) {
			String[] tikutJaVastaus = tapaus.split("a");
			int tikut = Integer.parseInt(tikutJaVastaus[0]);
			int vastaus = Integer.parseInt(tikutJaVastaus[1]);
			
			if (vastaus == 0) {
				tarkistaEttaTasainenJakauma(tekoaly1, 1, tikut);
				tarkistaEttaTasainenJakauma(tekoaly2, 2, tikut);
			}
			else {
				tarkistaEttaYksiYleisin(tekoaly1, 1, tikut, vastaus);
				tarkistaEttaYksiYleisin(tekoaly2, 2, tikut, vastaus);
			}
		}
	}
	
	private void tarkistaEttaTasainenJakauma(Object tekoaly, int tekoalynNumero, int tikut) throws Throwable {
		MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		
		int[] maarat = new int[3];
		for (int i=0; i<300; i++) {
			int pallo = teeValinta.invokeOn(tekoaly, tikut);
			maarat[pallo-1] = maarat[pallo-1] + 1;
		}
		
		for (int i=1; i<=3; i++) {
			if (maarat[i-1] > 250 || maarat[i-1] < 25) {
				fail("Opetettu tekoäly " + tekoalynNumero + " valitsi " + tikut + " tikusta seuraavia tikkumääriä kun valinta pyydettiin tekemään 300 kertaa. 1: " + maarat[0] + " 2: " + maarat[1] + " 3: " + maarat[2] + "\nJakauman olisi tullut olla tasainen. Jos määrät ovat hyvin lähellä tasaista voit kokeilla ajaa testit uudestaan.");
			}
		}
	}
	
	private void tarkistaEttaYksiYleisin(Object tekoaly, int tekoalynNumero, int tikut, int vastaus) throws Throwable {
		MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		
		int[] maarat = new int[3];
		for (int i=0; i<300; i++) {
			int pallo = -1;
			pallo = teeValinta.invokeOn(tekoaly, tikut);
			if (pallo==-1) {
				fail("-yksi");
			}
			if (pallo==0) {
				fail("nolla");
			}
			try {
				maarat[pallo-1] = maarat[pallo-1] + 1;
			} catch (Exception e) {
				fail("teeValinta-metodin palauttamien pallojen numeroiden tulisi olla välillä 1-3 " + " tikkuja oli " + tikut);
			}
		}
		
		if (maarat[vastaus-1] < 280) {
			fail("Opetettu tekoäly " + tekoalynNumero + " valitsi " + tikut + " tikusta " + vastaus + " tikkua vain " + maarat[vastaus-1] + " kertaa kun vastausta pyydettiin 300 kertaa.");
		}
	}
}
