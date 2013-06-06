/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.lang.reflect.Field;
import junit.framework.AssertionFailedError;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Usagi-chan
 */
public class OppivaTekoalyTest {
	private String luokka = "OppivaTekoaly";
	
	public OppivaTekoalyTest() {
	}

	@Before
	public void setUp() {
		try {
			Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		} catch (AssertionFailedError ae) {
			fail("Ei löytynyt luokkaa " + luokka + ". Eihän " + luokka + "si ota parametreja.");
		} catch (ArrayIndexOutOfBoundsException aie) {
			fail("Konstruktorissa luomastasi taulukosta yritettiin etsiä indeksiä, jota ei ollut taulukossa.\n" + aie.toString());
		}catch (Throwable t) {
			fail("Konstruktorin luonnissa ilmeni ongelma. " + t.toString() );
		}
	}
	
	
	/*
	@Test
	@Points("1.4.2")
	public void loytyykoMetoditTeeValintaJaOpiVoitosta() {
		loytyykoMetodi(int.class, "kokonaisluku", "teeValinta", int.class, "kokonaisluku");
		loytyykoMetodi("opiVoitosta");
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
	
	private void loytyykoMetodi(String metodinNimi) {
		Reflex.MethodRef0<Object, Void> metodi = Reflex.reflect(luokka).method(metodinNimi).returningVoid().takingNoParams();
		if (metodi.exists()) {
			return;
		}
		fail("Luokalla " + luokka + " tulee olla metodi " + metodinNimi + ".");
	}
	*/
	@Test
	@Points("1.4.2")
	public void eihanValintaJohdaOppimiseen() throws Throwable {
		Object tekoaly = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		String tapaus = "Eihän tekoälysi osaa mitään heti kun se on alustettu. Ei pelattu yhtään peliä, joten tekoälyn ei kuulunut oppia vielä mitään.";
		testaaValinnat(tekoaly, 3, tapaus, 2, 0.015, 10.0/30, 10.0/30, 10.0/30);
		testaaValinnat(tekoaly, 98, tapaus, 1, 0.015, 10.0/30, 10.0/30, 10.0/30);
	}
	
	@Test
	@Points("1.4.2")
	public void toimiikoOpiVoitosta() throws Throwable {
		Object tekoaly = voittojaPerakkain(5, 2, 1, 5000);
		String tapaus = "Luotu tekoäly voitti viisi kertaa peräkkäin ottamalla kahdesta tikusta yhden. Pidä huolta, että teeValinta hakee aina taulukon oikeasta indeksistä valinnat.";
		//TODO testaaEtteiValinnat(tekoaly, 2, tapaus, 0.015, 10.0/30, 10.0/30, 10.0/30) fail("Huomaa, että taulukkosi indeksi alkaa nollasta kun taas tikkujen määrä alkaa yhdestä.");
		testaaValinnat(tekoaly, 2, tapaus, 1, 0.015, 15.0/35, 10.0/35, 10.0/35);
		
		tekoaly = voittojaPerakkain(5, 3, 2, 5000);
		tapaus = "Luotu tekoäly voitti viisi kertaa peräkkäin ottamalla kolmesta tikusta kaksi.";
		testaaValinnat(tekoaly, 3, tapaus, 2, 0.015, 10.0/35, 15.0/35, 10.0/35);
		
		tekoaly = voittojaPerakkain(5, 6, 1, 5000);
		tapaus = "Luotu tekoäly voitti viisi kertaa peräkkäin ottamalla kuudesta tikusta yhden.";
		testaaValinnat(tekoaly, 6, tapaus, 1, 0.015, 15.0/35, 10.0/35, 10.0/35);
		
		tekoaly = voittojaPerakkain(2, 12, 3, 500);
		tapaus = "Luotu tekoäly voitti kaksi kertaa peräkkäin ottamalla 12 tikusta kolme.";
		testaaValinnat(tekoaly, 12, tapaus, 3, 0.02, 10.0/32, 10.0/32, 12.0/32);
	}
	/*
	private int voittojaPerakkainTesti(int perakkaisia, int tikkujaJaljella, int hyvaValinta, int suurinSallittuYritystenMaara) throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		Reflex.MethodRef0<Object, Void> opiVoitosta = Reflex.reflect(luokka).method("opiVoitosta").returningVoid().takingNoParams();
		
		int yritystenMaara = 0;
		
ulompi: while (true) {
			Object tekoaly = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
sisempi:	for (int i = 0; i < perakkaisia; i++) {
				int pallo = teeValinta.invokeOn(tekoaly, tikkujaJaljella);
				if (pallo != hyvaValinta) {
					yritystenMaara++;
					continue ulompi;
				}
				opiVoitosta.invokeOn(tekoaly);
			}
			return yritystenMaara;
		}
	}
	*/
	private Object voittojaPerakkain(int perakkaisia, int tikkujaJaljella, int hyvaValinta, int suurinSallittuYritystenMaara) throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		Reflex.MethodRef0<Object, Void> opiVoitosta = Reflex.reflect(luokka).method("opiVoitosta").returningVoid().takingNoParams();
		
		int yritystenMaara = 0;
		
ulompi: while (true) {
			Object tekoaly = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
sisempi:	for (int i = 0; i < perakkaisia; i++) {
				int pallo = 0;
				try {
					pallo = teeValinta.invokeOn(tekoaly, tikkujaJaljella);
				} catch (Throwable t) {
					fail("Huomaa, että listan indeksointi alkaa nollasta ja tikkujen määrät yhdestä.");
				}
				if (yritystenMaara > suurinSallittuYritystenMaara) {
					fail(perakkaisia + " peräkkäisen voiton tekeminen oli mahdotonta kun tikkuja oli jäljellä " + tikkujaJaljella + ". Pystyyhän tekoälysi voittamaan tässä tilanteessa kun ennen näitä tapauksia tekoäly ei ollut vielä oppinut mitään?");
				}
				if (pallo != hyvaValinta) {
					yritystenMaara++;
					continue ulompi;
				}
				opiVoitosta.invokeOn(tekoaly);
			}
			return tekoaly;
		}
	}
	
	private double testaaValinnat(Object tekoaly, int tikkuja, String tapaus, int hyvaValinta, double suurinSallittuPoikkeama, double... tnt) throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		
		int[] pallojenMaarat = new int[3];
		for (int i=0; i<30000; i++) {
			int pallo = 0;
			try {
				pallo = teeValinta.invokeOn(tekoaly, tikkuja);
			} catch (NullPointerException e) {
				fail(e.toString() + "\nPalautat null-viitteen teeValinta-metodista.");
			} catch (ArrayIndexOutOfBoundsException ae) {
				fail("Suunnittele lähestymistapa, jossa sen taulukon, johon tekoälyn tekemät valinnat talletetaan, koko ei riipu siitä miten monta kertaa tekoälyn odotetaan tekevän valinta.\nJos tekoäly tekee valinnan samasta tikkumäärästä useamman kerran voit olettaa, että edellinen valinta unohdetaan.");
			}
			try {
				pallojenMaarat[pallo-1] = pallojenMaarat[pallo-1] + 1;
			} catch (Exception e) {
				fail("Pallojen numeroiden tuli olla väliltä 1-3");
			}
		}
		
		double suurinPoikkeama = 0;
		
		for (int i = 0; i < 3; i++) {
			if (Math.abs(pallojenMaarat[i]/30000.0 - tnt[i]) > suurinSallittuPoikkeama) {
				String lisa = "";
				if (toisto50KertaaNayttaaToimivan(tikkuja, hyvaValinta, tnt)) {
					lisa += "\nJos valintojen talletukseen liittyvän taulukkosi koko on määritetty sen mukaan miten monta valintaa oletat pelin aikana syntyvän voi myös tästä olla apua:";
					lisa += "Suunnittele lähestymistapa, jossa sen taulukon, johon tekoälyn tekemät valinnat talletetaan, koko ei riipu siitä miten monta kertaa tekoälyn odotetaan tekevän valinta.\nJos tekoäly tekee valinnan samasta tikkumäärästä useamman kerran voit olettaa, että edellinen valinta unohdetaan.";
				}
				fail(tapaus + "\nJoitakin palloja näyttäisi kuitenkin tulevan väärä määrä siihen nähden miten tekoälyn tuli oppia. Pallot 1: " + pallojenMaarat[0] + "kpl, 2: " + pallojenMaarat[1] + "kpl, 3: " + pallojenMaarat[2] + "kpl" + lisa);
			}
			if (Math.abs(pallojenMaarat[i]/30000.0 - tnt[i]) > suurinPoikkeama) {
				suurinPoikkeama = Math.abs(pallojenMaarat[i]/30000.0 - tnt[i]);
			}
		}
		
		return suurinPoikkeama;
	}
	
	private boolean toisto50KertaaNayttaaToimivan(int tikkuja, int hyvaValinta, double... tnt) throws Throwable {
		int voittojenMaara = 0;
		if (tnt[0]*32 * 10%10 == 0) {
			voittojenMaara = 2;
		}
		else if (tnt[0]*35 *10%10 == 0) {
			voittojenMaara = 5;
		}
		else if (tnt[0]*30 *10%10 == 0) {
			voittojenMaara = 0;
			return false;
		}
		
		Object tekoaly = voittojaPerakkain(voittojenMaara, tikkuja, hyvaValinta, 5000);
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		
		int[] pallojenMaarat = new int[3];
		for (int i=0; i<50; i++) {
			int pallo = teeValinta.invokeOn(tekoaly, tikkuja);
			pallojenMaarat[pallo-1]++;
		}
		
		int haluttuYleisin = 0;
		int yleisin = 0;
		for (int i = 1; i < 3; i++) {
			if (tnt[i] > tnt[haluttuYleisin]) {
				haluttuYleisin = i;
			}
			if (pallojenMaarat[i] > pallojenMaarat[yleisin]) {
				yleisin = i;
			}
		}
		
		if (pallojenMaarat[haluttuYleisin] + 2 >= pallojenMaarat[yleisin]) {
			return true;
		}
		
		return false;
	}
	
	/*
	@Test
	@Points("1.4.2")
	public void loytyykoOpiHaviostaMetodi() {
		loytyykoMetodi("opiHaviosta");
	}
	*/
	@Test
	@Points("1.4.2")
	public void opiHaviostaToimii() throws Throwable {
		Object tekoaly = havioitaPerakkain(6, 3, 2, 20000);
		String tapaus = "Luotu tekoäly hävisi kuusi kertaa peräkkäin ottamalla kolmesta tikusta yhden tai kolme.";
		testaaValintojenMaarat(tekoaly, 3, tapaus, 2);
		
		tekoaly = havioitaPerakkain(6, 8, 3, 20000);
		tapaus = "Luotu tekoäly hävisi kuusi kertaa peräkkäin ottamalla kahdeksasta tikusta yhden tai kaksi.";
		testaaValintojenMaarat(tekoaly, 8, tapaus, 3);
		
		tekoaly = havioitaPerakkain(6, 18, 1, 20000);
		tapaus = "Ethän ole kertonut hatulle millä valinnoilla voitetaan.\nLuotu tekoäly hävisi kuusi kertaa peräkkäin ottamalla 18 tikusta kaksi tai kolme. (Oikeassa pelissä yhden nostaminen johtaisi mahdolliseen voittoon.)";
		testaaValintojenMaarat(tekoaly, 18, tapaus, 1);
		
		tekoaly = havioitaPerakkain(6, 66, 1, 20000);
		tapaus = "Luotu tekoäly hävisi kuusi kertaa peräkkäin ottamalla 66 tikusta kaksi tai kolme.";
		testaaValintojenMaarat(tekoaly, 66, tapaus, 1);	
	}
	/*
	private int havioitaPerakkainTesti(int perakkaisia, int tikkujaJaljella, int hyvaValinta, int suurinSallittuYritystenMaara) throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		Reflex.MethodRef0<Object, Void> opiHaviosta = Reflex.reflect(luokka).method("opiHaviosta").returningVoid().takingNoParams();
		
		int yritystenMaara = 0;
		
ulompi: while (true) {
			Object tekoaly = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
sisempi:	for (int i = 0; i < perakkaisia; i++) {
				int pallo = teeValinta.invokeOn(tekoaly, tikkujaJaljella);
				if (pallo != hyvaValinta) {
					yritystenMaara++;
					continue ulompi;
				}
				opiHaviosta.invokeOn(tekoaly);
			}
			return yritystenMaara;
		}
	}
	*/
	
	private Object havioitaPerakkain(int perakkaisia, int tikkujaJaljella, int hyvaValinta, int suurinSallittuYritystenMaara) throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		Reflex.MethodRef0<Object, Void> opiHaviosta = Reflex.reflect(luokka).method("opiHaviosta").returningVoid().takingNoParams();
		
		int yritystenMaara = 0;
		int ekaTyhma = hyvaValinta - 1;
		if (ekaTyhma == 0) {
			ekaTyhma = hyvaValinta + 1;
		}
		int hylkayksiaSatunnaisuudenTakia = 0;
		
ulompi: while (true) {
			int ekanTyhmanValintakerrat = 0;
			int tokanTyhmanValintakerrat = 0;
			Object tekoaly = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
sisempi:	for (int i = 0; i < perakkaisia; i++) {
				int pallo = 0;
				try {
					pallo = teeValinta.invokeOn(tekoaly, tikkujaJaljella);
				} catch (NullPointerException e) {
					fail(e.toString() + "\nHuomaa, että listan indeksointi alkaa nollasta ja tikkujen määrät yhdestä.");
				}
				if (yritystenMaara > suurinSallittuYritystenMaara) {
					if (suurinSallittuYritystenMaara > 50000) {
						fail("Tekoäly näyttää suosivan toista vaihtoehdoista, joilla tekoäly häviää.");
					}
					if (hylkayksiaSatunnaisuudenTakia >0 && hylkayksiaSatunnaisuudenTakia < 10) {
						suurinSallittuYritystenMaara += 10000;
						continue ulompi;
					}
					fail(perakkaisia + " peräkkäisen häviön tekeminen oli mahdotonta kun tikkuja oli jäljellä " + tikkujaJaljella + ". Pystyyhän tekoälysi voittamaan tässä tilanteessa kun ennen näitä tapauksia tekoäly ei ollut vielä oppinut mitään?");
				}
				if (pallo == hyvaValinta) {
					yritystenMaara++;
					continue ulompi;
				}
				
				if (pallo == ekaTyhma) {
					ekanTyhmanValintakerrat++;
				} else {
					tokanTyhmanValintakerrat++;
				}
				
				opiHaviosta.invokeOn(tekoaly);
			}
			if (ekanTyhmanValintakerrat < 2 || tokanTyhmanValintakerrat < 2) {
				hylkayksiaSatunnaisuudenTakia++;
				continue;
			}
			return tekoaly;
		}
	}
	
	private void testaaValintojenMaarat(Object tekoaly, int tikkuja, String tapaus, int voittavaPallo) throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		
		int[] pallojenMaarat = new int[3];
		for (int i=0; i<30000; i++) {
			int pallo = 0;
			try {
				pallo = teeValinta.invokeOn(tekoaly, tikkuja);
			} catch (NullPointerException e) {
				fail(e.toString() + "\nHuomaa, että listan indeksointi alkaa nollasta ja tikkujen määrät yhdestä.");
			}
			try {
				pallojenMaarat[pallo-1] = pallojenMaarat[pallo-1] + 1;
			} catch (Exception e) {
				fail("Pallojen numeroiden tuli olla väliltä 1-3");
			}
		}
		
		for (int i=1; i<=3; i++) {
			if (i == voittavaPallo) {
				continue;
			}
			if (pallojenMaarat[i-1] > pallojenMaarat[voittavaPallo-1]) {
				fail(tapaus + "\nTekoäly ei kuitenkaan näyttänyt oppivan näistä, koska nostettujen pallojen määrät olivat 1: " + pallojenMaarat[0] + ", 2: " + pallojenMaarat[1] + ", 3: " + pallojenMaarat[2]);
			}
		}
	}
	
	@Test
	@Points("1.4.2")
	public void muokataanhanUseaaHattuaPelinJalkeen() throws Throwable {
		muokkaaVoitonJalkeen();
		muokkaaHavionJalkeen();
	}
	
	private void muokkaaVoitonJalkeen() throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		Reflex.MethodRef0<Object, Void> opiVoitosta = Reflex.reflect(luokka).method("opiVoitosta").returningVoid().takingNoParams();
		
		Object tekoaly = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		
		int[] valinnat = new int[10];
		for (int i=0; i<10; i++) {
			int pallo = 0;
			try {
				pallo = teeValinta.invokeOn(tekoaly, i+1);
			} catch (NullPointerException e) {
				fail(e.toString() + "\nHuomaa, että listan indeksointi alkaa nollasta ja tikkujen määrät yhdestä.");
			}
			valinnat[i] = pallo;
		}
		opiVoitosta.invokeOn(tekoaly);
		
		tarkistaEtteiTasaisetJakaumatVoitonJalkeen(1, valinnat, tekoaly, teeValinta);
	}
	
	private void tarkistaEtteiTasaisetJakaumatVoitonJalkeen(int alku, int[] valinnat, Object tekoaly, Reflex.MethodRef1<Object, Integer, Integer> teeValinta) throws Throwable {
		for (int tikku=0; tikku<valinnat.length; tikku++) {
			double[] tnt = {10.0/31, 10.0/31, 10.0/31};
			tnt[valinnat[tikku]-1] += 1.0/31;
			
			int[] maarat = new int[3];
			for (int i=0; i<10000; i++) {
				int pallo = 0;
				try {
					pallo = teeValinta.invokeOn(tekoaly, tikku+alku);
				} catch (NullPointerException e) {
					fail(e.toString() + "\nHuomaa, että listan indeksointi alkaa nollasta ja tikkujen määrät yhdestä.");
				}
				maarat[pallo-1] = maarat[pallo-1] + 1;
			}
			for (int i=0; i<3; i++) {
				if (i == valinnat[tikku]-1) {
					continue;
				}
				if (maarat[i] > maarat[valinnat[tikku]-1]) {
					fail("Tekoälyn tuli oppia pelistä. Pelattiin 10 pitkä peli, joka voitettiin, mutta tekoäly ei näyttänyt oppivan valinnastaan kun tikkujen määrä oli " + (tikku+alku) + "\nTekoäly oppimisen jälkeen antoi tälle tikkumäärälle seuraavia palloja: 1: " + maarat[0] + " 2. " + maarat[1] + " 3. " + maarat[2]);
				}
				//if (Math.abs(maarat[i]/5000.0 - tnt[i]) > 0.25) {
				//	fail("Suoritettiin " + valinnat.length + " valinnan pitkä peli, joka voitettiin, mutta tekoäly ei näyttänyt oppivan valinnastaan kun tikkujen määrä oli " + (tikku+alku) + "\nTekoäly oppimisen jälkeen antoi tälle tikkumäärälle seuraavia palloja: 1: " + maarat[0] + " 2. " + maarat[1] + " 3. " + maarat[2]);
				//} liian pieni marginaali oppimisen ja ei-oppimisen välillä
			}
		}
	}
	
	private void muokkaaHavionJalkeen() throws Throwable {
		Reflex.MethodRef1<Object, Integer, Integer> teeValinta = Reflex.reflect(luokka).method("teeValinta").returning(int.class).taking(int.class);
		Reflex.MethodRef0<Object, Void> opiHaviosta = Reflex.reflect(luokka).method("opiHaviosta").returningVoid().takingNoParams();
		
		Object tekoaly = Reflex.reflect(luokka).constructor().takingNoParams().invoke();
		
		int[] valinnat = new int[10];
		for (int i=0; i<10; i++) {
			int pallo = 0;
			try {
				pallo = teeValinta.invokeOn(tekoaly, i+1);
			} catch (NullPointerException e) {
				fail(e.toString() + "\nHuomaa, että listan indeksointi alkaa nollasta ja tikkujen määrät yhdestä.");
			}
			valinnat[i] = pallo;
		}
		opiHaviosta.invokeOn(tekoaly);
		
		tarkistaEtteiTasaisetJakaumatHavionJalkeen(1, valinnat, tekoaly, teeValinta);
	}
	
	private void tarkistaEtteiTasaisetJakaumatHavionJalkeen(int alku, int[] valinnat, Object tekoaly, Reflex.MethodRef1<Object, Integer, Integer> teeValinta) throws Throwable {
		for (int tikku=0; tikku<valinnat.length; tikku++) {
			double[] tnt = {10.0/29, 10.0/29, 10.0/29};
			tnt[valinnat[tikku]-1] -= 1.0/29;
			
			int[] maarat = new int[3];
			for (int i=0; i<10000; i++) {
				int pallo = 0;
				try {
					pallo = teeValinta.invokeOn(tekoaly, tikku+alku);
				} catch (NullPointerException e) {
					fail(e.toString() + "\nHuomaa, että listan indeksointi alkaa nollasta ja tikkujen määrät yhdestä.");
				}
				maarat[pallo-1] = maarat[pallo-1] + 1;
			}
			for (int i=0; i<3; i++) {
				if (i == valinnat[tikku]-1) {
					continue;
				}
				if (maarat[i] < maarat[valinnat[tikku]-1]) {
					fail("Tikun tuli oppia pelistä. Pelattiin 10 pitkä peli, joka hävittiin, mutta tekoäly ei näyttänyt oppivan valinnastaan kun tikkujen määrä oli " + (tikku+alku) + "\nTekoäly oppimisen jälkeen antoi tälle tikkumäärälle seuraavia palloja: 1: " + maarat[0] + " 2. " + maarat[1] + " 3. " + maarat[2]);
				}
				//if (Math.abs(maarat[i]/5000.0 - tnt[i]) > 0.05) {
				//	fail("Suoritettiin " + valinnat.length + " valinnan pitkä peli, joka hävittiin, mutta tekoäly ei näyttänyt oppivan valinnastaan kun tikkujen määrä oli " + (tikku+alku) + "\nTekoäly oppimisen jälkeen antoi tälle tikkumäärälle seuraavia palloja: 1: " + maarat[0] + " 2. " + maarat[1] + " 3. " + maarat[2]);
				//}
			}
		}
	}
}