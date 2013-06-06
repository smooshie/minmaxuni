/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Usagi-chan
 */
public class ToinenTekoalyTest {
	private String luokka = "ToinenTekoaly";
	private String tarkistus = "54a1 19a2 5a0 36a3 40a3 25a0 10a1 38a1 17a0 48a3 61a0 8a3 1a0 66a1 27a2 4a3 33a0 60a3 45a0 58a1 43a2 2a1 13a0 24a3 63a2 7a2 41a0 16a3 31a2 12a3 22a1 68a3 30a1 50a1";
	private String tarkistus1234 = "5a4 54a3 19a3 36a1 40a4 25a4 10a4 38a2";
	private String tarkistus124 = "2a1 5a1b4 39a2 47a1b4 60a2 23a1b4 1a0";
	private String tarkistus1246 = "2a1 6a2 10a1b6 34a1b6 16a4 31a6 48a4 13a1b4";
	private String tarkistuslista2411 = "3a2 5a4 9a2 25a4b11 42a2 13a11";
	
	public ToinenTekoalyTest() {
	}
	
	@Before
	public void setUp() {
		try {
			Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		} catch (Throwable t) {
			fail("Ei löytynyt luokkaa " + luokka + ". Eihän " + luokka + "si ota parametreja.");
		}
	}
	
	
	
	@Test
	@Points("1.4.4")
	public void loytyykoTeeValintaMetodi() {
		loytyykoMetodi(int.class, "kokonaisluku", "teeValinta", int.class, "kokonaisluku");
	}
	
	private void loytyykoMetodi(Class palautusarvo, String palautusarvonNimi, String metodinNimi, Class parametri, String parametrinNimi) {
		Reflex.MethodRef1<Object, Integer, Integer> metodi = Reflex.reflect(luokka).method(metodinNimi).returning(palautusarvo).taking(parametri);
		if (metodi.exists()) {
			return;
		}
		
		Reflex.MethodRef0<Object, Integer> parametritonMetodi = Reflex.reflect(luokka).method(metodinNimi).returning(palautusarvo).takingNoParams();
		if (parametritonMetodi.exists()) {
			fail("Luokan " + luokka + " metodin " + metodinNimi + " tulee ottaa parametriksi yksi " + parametrinNimi + ".");
		}
		
		Reflex.MethodRef1<Object, Void, Integer> returnitonMetodi = Reflex.reflect(luokka).method(metodinNimi).returningVoid().taking(parametri);
		if (returnitonMetodi.exists()) {
			fail("Luokan " + luokka + " metodin " + metodinNimi + " tulee palauttaa " + palautusarvonNimi + ".");
		}
		
		Reflex.MethodRef0<Object, Void> parametritonJaReturnitonMetodi = Reflex.reflect(luokka).method(metodinNimi).returningVoid().takingNoParams();
		if (parametritonJaReturnitonMetodi.exists()) {
			fail("Luokan " + luokka + " metodin " + metodinNimi + " tulee ottaa parametriksi yksi " + parametrinNimi + ".");
		}
		fail("Luokalla " + luokka + " tulee olla metodi " + metodinNimi);
	}
	
	@Test
	@Points("1.4.4")
	public void toimiikoTeeValinta() throws Throwable {
		Object tekoaly = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		
		String[] lista = tarkistus.split(" ");
		for (String tapaus : lista) {
			String[] tikutJaVastaus = tapaus.split("a");
			int tikut = Integer.parseInt(tikutJaVastaus[0]);
			int vastaus = Integer.parseInt(tikutJaVastaus[1]);
			
			if (vastaus == 0) {
				continue;
			}
			else {
				tarkistaEttaVainYksiVastaus(tekoaly, tikut, vastaus);
			}
		}
	}
	
	private void tarkistaEttaVainYksiVastaus(Object tekoaly, int tikut, int vastaus) throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		
		int[] maarat = new int[3];
		for (int i=0; i<300; i++) {
			int pallo = teeValinta.invokeOn(tekoaly, tikut);
			maarat[pallo-1] = maarat[pallo-1] + 1;
		}
		
		if (maarat[vastaus-1] != 300) {
			fail("Matemaattisen analyysin osaava tekoäly valitsi " + tikut + " tikusta " + vastaus + " tikkua vain " + maarat[vastaus-1] + " kertaa kun vastausta pyydettiin 300 kertaa.\nMatemaattisen analyysin perusteella tekoälyn tulisi antaa aina oikea vastaus.");
		}
	}
	
	@Test
	@Points("1.4.5")
	public void onkoYleisenTapauksenKonstruktoriLuotu() {
		try {
			int [] poistot = {1, 2};
			Reflex.reflect(luokka).constructor().taking(int[].class).invoke(poistot);
		} catch (Throwable t) {
			fail("Ei löytynyt luokkaa " + luokka + ".");
		}
	}
	
	@Test
	@Points("1.4.5")
	public void toimiikoTeeValinta1234() throws Throwable {
		testaa(tarkistus1234, "\nPelissä voitiin valita tikkuja 1, 2, 3 tai 4.", 1, 2, 3, 4);
		testaa(tarkistus1246, "\nPelissä voitiin valita tikkuja 1, 2, 4 tai 6", 1, 2, 4, 6);
	}
	
	private void testaa(String tarkistettavaLista, String tapauskuvaus, int... poistot) throws Throwable {
		Object tekoaly = Reflex.reflect(luokka).constructor().taking(int[].class).invoke(poistot);
		
		String[] lista = tarkistettavaLista.split(" ");
		for (String tapaus : lista) {
			String[] tikutJaVastaus = tapaus.split("a");
			int tikut = Integer.parseInt(tikutJaVastaus[0]);
			int[] vastaukset = avaaLista(tikutJaVastaus[1]);
			
			if (tikutJaVastaus[1].equals("0")) {
				continue;
			}
			tarkistaEttaVaanOikeitaVastauksia(tekoaly, tikut, poistot, vastaukset);
		}
	}
	
	private int[] avaaLista(String string) {
		String[] vastaukset = string.split("b");
		int[] numerovastaukset = new int[vastaukset.length];
		for (int i = 0; i < vastaukset.length; i++) {
			numerovastaukset[i] = Integer.parseInt(vastaukset[i]);
		}
		return numerovastaukset;
	}
	
	private void tarkistaEttaVaanOikeitaVastauksia(Object poistotTuntevaTekoaly, int tikut, int[] poistot, int[] vastaukset) throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		
		ArrayList<Integer> kaikkiPoistot = new ArrayList<Integer>();
		for (int i=0; i<poistot.length; i++) {
			kaikkiPoistot.add(poistot[i]);
		}
		ArrayList<Integer> voittavatPoistot = new ArrayList<Integer>();
		for (int i=0; i<vastaukset.length; i++) {
			voittavatPoistot.add(vastaukset[i]);
		}
		
		for (int i=0; i<300; i++) {
			int pallo = teeValinta.invokeOn(poistotTuntevaTekoaly, tikut);
			if (!kaikkiPoistot.contains(pallo)) {
				fail("Yleisen pelin säännöt tuntevan tekoälyn ei olisi pitänyt tarjota palloa " + pallo + " kun sallittavia poistoja olivat " + kaikkiPoistot.toString());
			}
			if (!voittavatPoistot.contains(pallo)) {
				fail("Yleisen pelin logiikan tuntevan tekoälyn olisi pitänyt tietää, että " + pallo + " pallo ei tuota varmaa voittoa kun tikkuja on " + tikut + ". Sallittavia poistoja olivat " + kaikkiPoistot.toString() + " ja voittavia poistoja olisivat olleet " + voittavatPoistot.toString());
			}
		}
	}
}
