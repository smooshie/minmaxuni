/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
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
public class MurtoluvutTest {
	private Class luokka;
	private Constructor konstruktori1, konstruktori2;
	
	public MurtoluvutTest() {
	}

	@Before
	public void setUp() {
		try {
			luokka = ReflectionUtils.findClass("Murtoluku");
		} catch (AssertionError e) {
			fail("Olethan luonut luokan Murtoluku.");
		} catch (Throwable t) {
			fail("Outo virhe. Murtoluku-luokkaa ei löydetty.");
		}
		
		try {
			konstruktori2 = ReflectionUtils.requireConstructor(luokka, int.class, int.class);
		} catch (AssertionError e) {
			fail("Luokan yhden konstruktorin tulee ottaa parametrikseen kaksi kokonaislukua.");
		}
		try {
			konstruktori1 = ReflectionUtils.requireConstructor(luokka, int.class);
		} catch (AssertionError e) {
			fail("Luokan yhden konstruktorin tulee ottaa parametrikseen yksi kokonaisluku.");
		}
	}
	
	
	
	
	
	private Method haeMetodi(String metodinNimi) {
		Method metodi = null;
		try {
			metodi = ReflectionUtils.requireMethod(luokka, metodinNimi);
		} catch (Throwable t) {
			testaaEtteiParametrillinen(metodinNimi);
			fail("Luokalla tulee olla metodi \"" + metodinNimi + "\". Ethän anna sille mitään parametrejä.");
		}
		return metodi;
	}
	
	private Method haeMetodi(Class palautusarvo, String palautusarvonNimi, String metodinNimi) {
		Method metodi = haeMetodi(metodinNimi);
		try {
			metodi = ReflectionUtils.requireMethod(luokka, palautusarvo, metodinNimi);
		} catch (Throwable t) {
			fail("Luokan metodin " + metodinNimi + " tulee palauttaa " + palautusarvonNimi);
		}
		
		return metodi;
	}
	
	private void testaaEtteiParametrillinen(String metodinNimi) {
		try {
			ReflectionUtils.requireMethod(luokka, metodinNimi, int.class);
		} catch (Throwable t) {
			return;
		}
		fail("Luokan metodin " + metodinNimi + " ei tullut ottaa parametrejä.");
	}
	
	private void testaaEtteiParametriton(String metodinNimi, String parametrinNimi) {
		try {
			ReflectionUtils.requireMethod(luokka, metodinNimi);
		} catch (Throwable t) {
			return;
		}
		fail("Luokan metodin " + metodinNimi + " tulisi pyytää parametri: " + parametrinNimi);
	}
	
	private Method haeMetodi(String metodinNimi, Class parametri, String parametrinNimi) {
		Method metodi = null;
		try {
			metodi = ReflectionUtils.requireMethod(luokka, metodinNimi, parametri);
		} catch (Throwable t) {
			testaaEtteiParametriton(metodinNimi, parametrinNimi);
			fail("Luokalla tulee olla metodi " + metodinNimi + " ja sen tulee pyytää yksi " + parametrinNimi + "-tyyppinen parametri.");
		}
		
		return metodi;
	}
	
	private Method haeMetodi(Class palautusarvo, String palautusarvonNimi, String metodinNimi, Class parametri, String parametrinNimi) {
		Method metodi = haeMetodi(metodinNimi, parametri, parametrinNimi);
		
		try {
			metodi = ReflectionUtils.requireMethod(luokka, palautusarvo, metodinNimi, parametri);
		} catch (Throwable t) {
			fail("Luokan metodin " + metodinNimi + " tulee palauttaa " + palautusarvonNimi);
		}
		
		return metodi;
	}
	
	/**
	 * Testaa, että osoittajan hakeminen toimii kahden parametrin konstruktorilla.
	 */
	@Test
	@Points("1.2.1")
	public void haeOsoittajaToimiiKahdenParametrinKonstruktorilla() {
		haeOsoittajaToimii(3, 4);
		haeOsoittajaToimii(5, 2);
		haeOsoittajaToimii(-1, 6);
	}
	
	private void haeOsoittajaToimii(int osoittaja, int nimittaja) {
		int haettuOsoittaja = haeOsoittaja(osoittaja, nimittaja);
		assertFalse("Metodi haeOsoittaja antaa nimittäjän, kun osoittaja on " + osoittaja + " ja nimittaja " + nimittaja + ".\nTestattiin konstruktori, joka ottaa kaksi parametriä. Metodin antama osoittaja oli " + haettuOsoittaja + ".", haettuOsoittaja == nimittaja);
		assertTrue("Metodi haeOsoittaja antaa väärän osoittajan, kun osoittaja on " + osoittaja + ". Metodin antama arvo oli " + haettuOsoittaja + ".\nTestattiin konstruktori, joka ottaa kaksi parametriä.", haettuOsoittaja == osoittaja);
	}
	
	private int haeOsoittaja(int osoittaja, int nimittaja) {
		Method metodi = haeMetodi(int.class, "kokonaisluku", "haeOsoittaja");
		try {
			Object murtoluku = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja, nimittaja);
			return ReflectionUtils.invokeMethod(int.class, metodi, murtoluku);
		} catch (Throwable t) {
			fail("Virhe joka on jo catchattu. Koetettiin käyttää metodia haeOsoittaja.");
			return 0;
		}
	}
	
	/**
	 * Testaa, että osoittajan hakeminen toimii yhden parametrin konstruktorilla.
	 */
	@Test
	@Points("1.2.1")
	public void haeOsoittajaToimiiYhdenParametrinKonstruktorilla() {
		haeOsoittajaToimii(3);
		haeOsoittajaToimii(5);
		haeOsoittajaToimii(-3);
	}
	
	private void haeOsoittajaToimii(int osoittaja) {
		int haettuOsoittaja = haeOsoittaja(osoittaja);
		assertFalse("Metodi haeOsoittaja antaa nimittäjän, kun nimittaja on 1 ja osoittaja " + osoittaja + ".\nTestattiin konstruktori, joka ottaa yhden parametrin. Metodin antama osoittaja oli " + haettuOsoittaja + ".", haettuOsoittaja == 1);
		assertTrue("Metodi haeOsoittaja antaa väärän osoittajan, kun osoittaja on " + 1 + ". Metodin antama arvo oli " + haettuOsoittaja + ".\nTestattiin konstruktori, joka ottaa yhden parametrin.", haettuOsoittaja == osoittaja);
	}
	
	private int haeOsoittaja(int osoittaja) {
		Method metodi = haeMetodi(int.class, "kokonaisluku", "haeOsoittaja");
		try {
			Object murtoluku = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja);
			return ReflectionUtils.invokeMethod(int.class, metodi, murtoluku);
		} catch (Throwable t) {
			fail("Virhe joka on jo catchattu. Koetettiin käyttää metodia haeOsoittaja.");
			return 0;
		}
	}
	
	/**
	 * Testaa, että nimittajan hakeminen toimii kahden parametrin konstruktorilla.
	 */
	@Test
	@Points("1.2.1")
	public void haeNimittajaToimiiKahdenParametrinKonstruktorilla() {
		haeNimittajaToimii(22, 7);
		haeNimittajaToimii(5, 4);
		haeNimittajaToimii(-4, 5);
		haeNimittajaToimii(4, 0);
	}
	
	private void haeNimittajaToimii(int osoittaja, int nimittaja) {
		int haettuNimittaja = haeNimittaja(osoittaja, nimittaja);
		assertFalse("haeNimittaja-metodi palautti osoittajan. Testattiin kahden parametrin konstruktoria.", haettuNimittaja == osoittaja);
		assertTrue("Haettu nimittaja oli " + haettuNimittaja + " kun sen piti olla " + nimittaja + ". Testattiin kahden parametrin konstruktoria.", haettuNimittaja == nimittaja);
	}
	
	public int haeNimittaja(int osoittaja, int nimittaja) {
		Method metodi = haeMetodi(int.class, "kokonaisluku", "haeNimittaja");
		try {
			Object murtoluku = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja, nimittaja);
			return ReflectionUtils.invokeMethod(int.class, metodi, murtoluku);
		} catch (Throwable t) {
			if (nimittaja == 0) { //sievennys ei toimi kun nimittäjä on nolla
				return 0;
			}
			fail("Virhe joka on jo catchattu. Koetettiin käyttää metodia haeNimittaja.");
			return 0;
		}
	}
	
	/**
	 * Testaa, että nimittajan hakeminen toimii yhden parametrin konstruktorilla.
	 */
	@Test
	@Points("1.2.1")
	public void haeNimittajaToimiiYhdenParametrinKonstruktorilla() {
		haeNimittajaToimii(6);
		haeNimittajaToimii(10);
		haeNimittajaToimii(-5);
	}
	
	private void haeNimittajaToimii(int osoittaja) {
		int haettuNimittaja = haeNimittaja(osoittaja);
		assertFalse("haeNimittaja-metodi palautti osoittajan. Testattiin yhden parametrin konstruktoria.", haettuNimittaja == osoittaja);
		assertTrue("Haettu nimittaja oli " + haettuNimittaja + " kun sen piti olla 1. Testattiin yhden parametrin konstruktoria.", haettuNimittaja == 1);
	}
	
	public int haeNimittaja(int osoittaja) {
		Method metodi = haeMetodi(int.class, "kokonaisluku", "haeNimittaja");
		try {
			Object murtoluku = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja);
			return ReflectionUtils.invokeMethod(int.class, metodi, murtoluku);
		} catch (Throwable t) {
			fail("Virhe joka on jo catchattu. Koetettiin käyttää metodia haeNimittaja.");
			return 0;
		}
	}
	
	/**
	 * Testaa onko toString oikein.
	 */
	@Test
	@Points("1.2.2")
	public void onkoToStringOikein() {
		onkoToStringOikein(3, 4, "3/4");
		onkoToStringOikein(2, 9, "2/9");
		onkoToStringOikein(1378, 1379, "1378/1379");
		
		onkoToStringOikein(3, "3/1");
		onkoToStringOikein(1378, "1378/1");
	}
	
	private void onkoToStringOikein(int osoittaja, int nimittaja, String oikeaTulostus) {
		String toString = toString(osoittaja, nimittaja);
		String[] osaset = toString.split("/");
		
		boolean onKorvattu = false;
		try {
			onKorvattu = luokka.getMethod("toString").getDeclaringClass().equals(luokka);
		} catch (Throwable t) {
			fail("Outo virhe. toStringin tulisi aina olla peritty ja löydettävissä.");
		}
		if (!onKorvattu) {
			fail("Olethan luonut metodin toString, jolle ei anneta parametreja.");
		}
		
		assertTrue("Osoittaja ja nimittäjä tulee olla eroteltuna toStringissä yhdellä kauttaviivalla.\nTarkasteltiin kahden parametrin konstruktoria. Tulostus oli " + toString, osaset.length == 2);
		assertTrue("Osoittajan tulee olla kauttaviivan vasemmalla puolella. Nyt osoittajaksi oli merkitty " + osaset[0].trim() + " kun luotu murtoluku oli " + oikeaTulostus + "\nTarkasteltiin kahden parametrin konstruktoria.", osaset[0].trim().equals(osoittaja + ""));
		assertTrue("Nimittäjän tulee olla kauttaviivan oikealla puolella. Nyt nimittäjäksi oli merkitty " + osaset[1].trim() + " kun luotu murtoluku oli " +oikeaTulostus + "\nTarkasteltiin kahden parametrin konstruktoria.", osaset[1].trim().equals(nimittaja + ""));
	}
	
	private String toString(int osoittaja, int nimittaja) {
		Method metodi = haeMetodi(String.class, "String", "toString");
		
		try {
			Object murtoluku = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja, nimittaja);
			return ReflectionUtils.invokeMethod(String.class, metodi, murtoluku);
		} catch (Throwable t) {
			fail("Kaiken piti olla jo testattuna toStringissa.");
			return "";
		}
	}
	
	private void onkoToStringOikein(int osoittaja, String oikeaTulostus) {
		String toString = toString(osoittaja);
		String[] osaset = toString.split("/");
		assertTrue("Osoittaja ja nimittäjä tulee olla eroteltuna toStringissä yhdellä kauttaviivalla.\nTarkasteltiin yhden parametrin konstruktoria.", osaset.length == 2);
		assertTrue("Osoittajan tulee olla kauttaviivan vasemmalla puolella. Nyt osoittajaksi oli merkitty " + osaset[0].trim() + " kun luotu murtoluku oli " + oikeaTulostus +"\nTarkasteltiin yhden parametrin konstruktoria.", osaset[0].trim().equals(osoittaja + ""));
		assertTrue("Nimittäjän tulee olla kauttaviivan oikealla puolella. Nyt nimittäjäksi oli merkitty " + osaset[1].trim() + " kun luotu murtoluku oli " + oikeaTulostus + "\nTarkasteltiin yhden parametrin konstruktoria.", osaset[1].trim().equals("1"));
	}
	
	private String toString(int osoittaja) {
		Method metodi = haeMetodi(String.class, "String", "toString");
		
		try {
			Object murtoluku = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja);
			return ReflectionUtils.invokeMethod(String.class, metodi, murtoluku);
		} catch (Throwable t) {
			fail("Kaiken piti olla jo testattuna toStringissa.");
			return "";
		}
	}
	
	@Test
	@Points("1.2.2")
	public void desimaalinaStringOikein() {
		desimaalinaStringOikein(3, 4, "3/4", "0.75");
		desimaalinaStringOikein(7, 6, "7/6", "1.167");
		
		desimaalinaStringOikein(9, "9/1", "9.00");
	}
	
	private void desimaalinaStringOikein(int osoittaja, int nimittaja, String oikeaMurtoluku, String oikeaTulostus) {
		double tulos = desimaalina(osoittaja, nimittaja);
		
		assertFalse("Kokonaisluvuilla laskettaessa tulokset ovat kokonaislukuja. Tuloksen tuli olla " + oikeaTulostus + ", mutta oli " + tulos + " kun luotu murtoluku oli " + oikeaMurtoluku, tulos < 0.5 && tulos > -0.5);
		assertEquals("desimaalina-metodi antoi luvun " + tulos + ", mutta tuloksen tuli olla " + oikeaTulostus + " kun luotu murtoluku oli " + oikeaMurtoluku, Double.parseDouble(oikeaTulostus), tulos, 0.001);	
	}
	
	private double desimaalina(int osoittaja, int nimittaja) {
		Method metodi = haeMetodi(double.class, "double", "desimaalina");
		try {
			Object murtoluku = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja, nimittaja);
			return ReflectionUtils.invokeMethod(double.class, metodi, murtoluku);
		} catch (Throwable e) {
			fail("Tätä virhettä ei pitänyt tulla. Koetettiin käyttää metodia desimaalina.");
			return 0;
		}
	}
	
	private void desimaalinaStringOikein(int osoittaja, String oikeaMurtoluku, String oikeaTulostus) {
		double tulos = desimaalina(osoittaja);
		assertEquals("desimaalina-metodi antoi Stringin " + tulos + ", mutta tuloksen tuli olla" + oikeaTulostus + " kun luotu murtoluku oli " + oikeaMurtoluku, Double.parseDouble(oikeaTulostus), tulos, 0.001);	
	}
	
	private double desimaalina(int osoittaja) {
		Method metodi = haeMetodi(double.class, "double", "desimaalina");
		try {
			Object murtoluku = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja);
			return ReflectionUtils.invokeMethod(double.class, metodi, murtoluku);
		} catch (Throwable e) {
			fail("Tätä virhettä ei pitänyt tulla. Koitettiin käyttää metodia desimaalina.");
			return 0;
		}
	}
	
	@Test
	@Points("1.2.3")
	public void lisaaminenToimii() {
		summaaminenToimii(1, 3, 1, 5, 8, 15);
		summaaminenToimii(2, 3, 4, 7, 26, 21);
		
		summaaminenToimii(2, 3, 5, 1);
		summaaminenToimii(3, -1, 2, 1);
	}
	
	private void summaaminenToimii(int osoittaja1, int nimittaja1, int osoittaja2, int nimittaja2, int oikeaOsoittaja, int oikeaNimittaja) {
		Object murtoluku1 = null, murtoluku2 = null;
		try {
			murtoluku1 = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja1, nimittaja1);
			murtoluku2 = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja2, nimittaja2);
		} catch (Throwable t) {
			fail("Tätä virhettä ei pitänyt tulla jos aiemmat vaiheet toimivat. Luodaan kaksi murtolukua.");
		}
		Object murtolukusumma = lisaa(murtoluku1, murtoluku2);
		eihanAlkuperainenMurtolukuMuutu(murtoluku1, osoittaja1, nimittaja1);
		eihanAlkuperainenMurtolukuMuutu(murtoluku2, osoittaja2, nimittaja2);
		
		int summanOsoittaja = haeOsoittaja(murtolukusumma);
		int summanNimittaja = haeNimittaja(murtolukusumma);
		if (summanOsoittaja > oikeaOsoittaja && summanNimittaja > oikeaNimittaja && summanOsoittaja%oikeaOsoittaja == 0 && summanNimittaja%oikeaNimittaja == 0) {
			int moninkerta = summanNimittaja /oikeaNimittaja;
			if (summanOsoittaja%moninkerta == 0) {
				fail("Saat laskettua summan helpommin näin: (a/b) * (c/d) = (a*d + c*b / b*d)");
			}
		}
		if (summanOsoittaja == osoittaja1 + osoittaja2 && summanOsoittaja != oikeaOsoittaja) {
			fail("Murtolukuja summatessa osoittajat voidaan summata vasta kun summattavien murtolukujen nimittajat ovat samat.");
		}
		assertTrue("Lisaa metodin palauttaman murtoluvun osoittaja oli " + summanOsoittaja + " kun sen piti olla " + oikeaOsoittaja + ". Laskettiin " + osoittaja1+"/"+nimittaja1+" + "+osoittaja2+"/"+nimittaja2, summanOsoittaja == oikeaOsoittaja);
		assertTrue("Lisaa metodin palauttaman murtoluvun nimittäjä oli " + summanNimittaja + " kun sen piti olla " + oikeaNimittaja + ". Laskettiin " + osoittaja1+"/"+nimittaja1+" + "+osoittaja2+"/"+nimittaja2, summanNimittaja == oikeaNimittaja);
	}
	
	private void summaaminenToimii(int osoittaja1, int osoittaja2, int oikeaOsoittaja, int oikeaNimittaja) {
		Object murtoluku1 = null, murtoluku2 = null;
		try {
			murtoluku1 = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja1);
			murtoluku2 = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja2);
		} catch (Throwable t) {
			fail("Tätä virhettä ei pitänyt tulla, jos aiemmat vaiheet toimivat. Luodaan kaksi murtolukua yhden parametrin konstruktorilla.");
		}
		Object murtolukusumma = lisaa(murtoluku1, murtoluku2);
		eihanAlkuperainenMurtolukuMuutu(murtoluku1, osoittaja1, 1);
		eihanAlkuperainenMurtolukuMuutu(murtoluku2, osoittaja2, 1);
		
		int summanOsoittaja = haeOsoittaja(murtolukusumma);
		int summanNimittaja = haeNimittaja(murtolukusumma);
		if (summanOsoittaja > oikeaOsoittaja && summanNimittaja > oikeaNimittaja && summanOsoittaja%oikeaOsoittaja == 0 && summanNimittaja%oikeaNimittaja == 0) {
			int moninkerta = summanNimittaja /oikeaNimittaja;
			if (summanOsoittaja%moninkerta == 0) {
				fail("Saat laskettua summan helpommin näin: (a/b) * (c/d) = (a*d + c*b / b*d)");
			}
		}
		assertTrue("Lisaa metodin palauttaman murtoluvun osoittaja oli " + summanOsoittaja + " kun sen piti olla " + oikeaOsoittaja + ". Laskettiin " + osoittaja1+"/1"+" + "+osoittaja2+"/1", summanOsoittaja == oikeaOsoittaja);
		assertTrue("Lisaa metodin palauttaman murtoluvun nimittäjä oli " + summanNimittaja + " kun sen piti olla " + oikeaNimittaja + ". Laskettiin " + osoittaja1+"/1"+" + "+osoittaja2+"/1", summanNimittaja == oikeaNimittaja);
	}
	
	private Object lisaa(Object murtoluku1, Object murtoluku2) {
		Method metodi = haeMetodi(luokka, "Murtoluku", "lisaa", luokka, "Murtoluku");
		try {
			return ReflectionUtils.invokeMethod(luokka, metodi, murtoluku1, murtoluku2);
		} catch (Throwable t) {
			fail("Koetettiin käyttää lisaa-metodia. Omituinen virhe jos aiemmat testit menevät läpi.");
			return null;
		}
	}
	
	@Test
	@Points("1.2.3")
	public void vahentaminenToimii() {
		vahentaminenToimii(5, 4, 8, 9, 13, 36);
		vahentaminenToimii(1, 3, 2, 7, 1, 21);
	}
	
	private void vahentaminenToimii(int osoittaja1, int nimittaja1, int osoittaja2, int nimittaja2, int oikeaOsoittaja, int oikeaNimittaja) {
		Object murtoluku1 = null, murtoluku2 = null;
		try {
			murtoluku1 = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja1, nimittaja1);
			murtoluku2 = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja2, nimittaja2);
		} catch (Throwable t) {
			fail("Tätä virhettä ei pitänyt tulla, jos aiemmat vaiheet toimivat. Luodaan kaksi murtolukua.");
		}
		Object murtolukuerotus = vahenna(murtoluku1, murtoluku2);
		eihanAlkuperainenMurtolukuMuutu(murtoluku1, osoittaja1, nimittaja1);
		eihanAlkuperainenMurtolukuMuutu(murtoluku2, osoittaja2, nimittaja2);
		
		int erotuksenOsoittaja = haeOsoittaja(murtolukuerotus);
		int erotuksenNimittaja = haeNimittaja(murtolukuerotus);
		if (erotuksenOsoittaja > oikeaOsoittaja && erotuksenNimittaja > oikeaNimittaja && erotuksenOsoittaja%oikeaOsoittaja == 0 && erotuksenNimittaja%oikeaNimittaja == 0) {
			int moninkerta = erotuksenNimittaja / oikeaNimittaja;
			if (erotuksenOsoittaja%moninkerta == 0) {
				fail("Saat laskettua erotuksen helpommin näin: (a/b) * (c/d) = (a*d - c*b / b*d)");
			}
		}
		if (erotuksenOsoittaja == -oikeaOsoittaja) {
			fail("vahenna-metodi vähentää murtoluvut väärinpäin toisistaan.");
		}
		if (erotuksenOsoittaja == osoittaja1 - osoittaja2 && erotuksenOsoittaja != oikeaOsoittaja) {
			fail("Murtolukuja vähennettäessä osoittajat voidaan vähentää toisistaan vasta kun vähennettävien murtolukujen nimittajat ovat samat.");
		}
		assertTrue("vahenna-metodin palauttaman murtoluvun osoittaja oli " + erotuksenOsoittaja + " kun sen piti olla " + oikeaOsoittaja + ". Laskettiin " + osoittaja1+"/"+nimittaja1+" - "+osoittaja2+"/"+nimittaja2, erotuksenOsoittaja == oikeaOsoittaja);
		assertTrue("vahenna-metodin palauttaman murtoluvun nimittäjä oli " + erotuksenNimittaja + " kun sen piti olla " + oikeaNimittaja + ". Laskettiin " + osoittaja1+"/"+nimittaja1+" - "+osoittaja2+"/"+nimittaja2, erotuksenNimittaja == oikeaNimittaja);
	}
	
	private Object vahenna(Object murtoluku1, Object murtoluku2) {
		Method metodi = haeMetodi(luokka, "Murtoluku", "vahenna", luokka, "Murtoluku");
		try {
			return ReflectionUtils.invokeMethod(luokka, metodi, murtoluku1, murtoluku2);
		} catch (Throwable t) {
			fail("Koetettiin käyttää vahenna-metodia. Omituinen virhe jos aiemmat testit menevät läpi.");
			return null;
		}
	}
	
	@Test
	@Points("1.2.4")
	public void tuloToimii() {
		tuloToimii(1, 4, 1, 3, 1, 12);
		tuloToimii(2, 3, 1, 5, 2, 15);
		
		tuloToimii(2, 3, 6, 1);
		tuloToimii(-4, 2, -8, 1);
	}
	
	private void tuloToimii(int osoittaja1, int nimittaja1, int osoittaja2, int nimittaja2, int oikeaOsoittaja, int oikeaNimittaja) {
		Object murtolukutulo = kerro(osoittaja1, nimittaja1, osoittaja2, nimittaja2);
		
		int tulonOsoittaja = haeOsoittaja(murtolukutulo);
		int tulonNimittaja = haeNimittaja(murtolukutulo);
		if (tulonOsoittaja > oikeaOsoittaja && tulonNimittaja > oikeaNimittaja && tulonOsoittaja%oikeaOsoittaja == 0 && tulonNimittaja%oikeaNimittaja == 0) {
			int moninkerta = tulonNimittaja/oikeaNimittaja;
			if (tulonOsoittaja%moninkerta == 0) {
				fail("Saat laskettua tulon helpommin näin: (a/b) * (c/d) = (a*c / b*d)");
			}
		}
		
		assertTrue("kerro-metodin palauttaman murtoluvun osoittaja oli " + tulonOsoittaja + " kun sen piti olla " + oikeaOsoittaja + ". Laskettiin " + osoittaja1+"/"+nimittaja1+" * "+osoittaja2+"/"+nimittaja2, tulonOsoittaja == oikeaOsoittaja);
		
		if (tulonNimittaja == nimittaja1 || tulonNimittaja == nimittaja2) {
			fail("Murtolukujen tulossa myös nimittäjät kerrotaan toisillaan.");
		}
		assertTrue("kerro-metodin palauttaman murtoluvun nimittäjä oli " + tulonNimittaja + " kun sen piti olla " + oikeaNimittaja + ". Laskettiin " + osoittaja1+"/"+nimittaja1+" * "+osoittaja2+"/"+nimittaja2, tulonNimittaja == oikeaNimittaja);
	}
	
	private Object kerro(int osoittaja1, int nimittaja1, int osoittaja2, int nimittaja2) {
		Object murtoluku1 = null, murtoluku2 = null;
		try {
			murtoluku1 = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja1, nimittaja1);
			murtoluku2 = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja2, nimittaja2);
		} catch (Throwable t) {
			fail("Tätä virhettä ei pitänyt tulla, jos aiemmat vaiheet toimivat. Luodaan kaksi murtolukua.");
		}
		Object murtolukutulo = kerro(murtoluku1, murtoluku2);
		eihanAlkuperainenMurtolukuMuutu(murtoluku1, osoittaja1, nimittaja1);
		eihanAlkuperainenMurtolukuMuutu(murtoluku2, osoittaja2, nimittaja2);
		return murtolukutulo;
	}
	
	private void tuloToimii(int osoittaja1, int osoittaja2, int oikeaOsoittaja, int oikeaNimittaja) {
		Object murtolukutulo = kerro(osoittaja1, osoittaja2);
		
		int tulonOsoittaja = haeOsoittaja(murtolukutulo);
		int tulonNimittaja = haeNimittaja(murtolukutulo);
		if (tulonOsoittaja > oikeaOsoittaja && tulonNimittaja > oikeaNimittaja && tulonOsoittaja%oikeaOsoittaja == 0 && tulonNimittaja%oikeaNimittaja == 0) {
			int moninkerta = tulonNimittaja/oikeaNimittaja;
			if (tulonOsoittaja%moninkerta == 0) {
				fail("Saat laskettua tulon helpommin näin: (a/b) * (c/d) = (a*c / b*d)");
			}
		}
		
		assertTrue("kerro-metodin palauttaman murtoluvun osoittaja oli " + tulonOsoittaja + " kun sen piti olla " + oikeaOsoittaja + ". Laskettiin " + osoittaja1+"/1"+" * "+osoittaja2+"/1", tulonOsoittaja == oikeaOsoittaja);
		assertTrue("kerro-metodin palauttaman murtoluvun nimittäjä oli " + tulonNimittaja + " kun sen piti olla " + oikeaNimittaja + ". Laskettiin " + osoittaja1+"/1"+" * "+osoittaja2+"/1", tulonNimittaja == oikeaNimittaja);
	}
	
	private Object kerro(int osoittaja1, int osoittaja2) {
		Object murtoluku1 = null, murtoluku2 = null;
		try {
			murtoluku1 = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja1);
			murtoluku2 = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja2);
		} catch (Throwable t) {
			fail("Tätä virhettä ei pitänyt tulla, jos aiemmat vaiheet toimivat. Luodaan kaksi murtolukua.");
		}
		Object murtolukutulo = kerro(murtoluku1, murtoluku2);
		eihanAlkuperainenMurtolukuMuutu(murtoluku1, osoittaja1, 1);
		eihanAlkuperainenMurtolukuMuutu(murtoluku2, osoittaja2, 1);
		return murtolukutulo;
	}
	
	private Object kerro(Object murtoluku1, Object murtoluku2) {
		Method metodi = haeMetodi(luokka, "Murtoluku", "kerro", luokka, "Murtoluku");
		try {
			return ReflectionUtils.invokeMethod(luokka, metodi, murtoluku1, murtoluku2);
		} catch (Throwable t) {
			fail("Koetettiin käyttää kerro-metodia. Omituinen virhe jos aiemmat testit menevät läpi.");
			return null;
		}
	}
	
	@Test
	@Points("1.2.4")
	public void osamaaraToimii() {
		osamaaraToimii(2, 3, 1, 4, 8, 3);
		osamaaraToimii(4, 5, 1, 2, 8, 5);
		
		osamaaraToimii(4, 7, 4, 7);
		osamaaraToimii(5, 3, 5, 3);
	}
	
	private void osamaaraToimii(int osoittaja1, int nimittaja1, int osoittaja2, int nimittaja2, int oikeaOsoittaja, int oikeaNimittaja) {
		Object murtolukuosamaara = jaa(osoittaja1, nimittaja1, osoittaja2, nimittaja2);
		
		int osamaaranOsoittaja = haeOsoittaja(murtolukuosamaara);
		int osamaaranNimittaja = haeNimittaja(murtolukuosamaara);
		if (osamaaranOsoittaja > oikeaOsoittaja && osamaaranNimittaja > oikeaNimittaja && osamaaranOsoittaja%oikeaOsoittaja == 0 && osamaaranNimittaja%oikeaNimittaja == 0) {
			int moninkerta = osamaaranNimittaja/oikeaNimittaja;
			if (osamaaranOsoittaja%moninkerta == 0) {
				fail("Saat laskettua osamäärän helpommin näin: (a/b) / (c/d) = (a*d / b*c)");
			}
		}
		
		if (osamaaranOsoittaja == 0) {
			fail("Kokonaislukujen laskutoimitus palauttaa aina kokonaisluvun.");
		}
		if (osamaaranOsoittaja == osoittaja1/osoittaja2) {
			fail("Murtolukujen osamäärä on murtolukujen tulo kun toinen murtoluvuista käännetään.");
		}
		assertTrue("Jaa metodin palauttaman murtoluvun osoittaja oli " + osamaaranOsoittaja + " kun sen piti olla " + oikeaOsoittaja + ". Laskettiin " + osoittaja1+"/"+nimittaja1+" / "+osoittaja2+"/"+nimittaja2, osamaaranOsoittaja == oikeaOsoittaja);
		assertTrue("Jaa metodin palauttaman murtoluvun nimittäjä oli " + osamaaranNimittaja + " kun sen piti olla " + oikeaNimittaja + ". Laskettiin " + osoittaja1+"/"+nimittaja1+" / "+osoittaja2+"/"+nimittaja2, osamaaranNimittaja == oikeaNimittaja);
	}
	
	private Object jaa(int osoittaja1, int nimittaja1, int osoittaja2, int nimittaja2) {
		Object murtoluku1 = null, murtoluku2 = null;
		try {
			murtoluku1 = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja1, nimittaja1);
			murtoluku2 = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja2, nimittaja2);
		} catch (Throwable t) {
			fail("Tätä virhettä ei pitänyt tulla jos aiemmat vaiheet toimivat. Luodaan kaksi murtolukua.");
		}
		Object murtolukuosamaara = jaa(murtoluku1, murtoluku2);
		eihanAlkuperainenMurtolukuMuutu(murtoluku1, osoittaja1, nimittaja1);
		eihanAlkuperainenMurtolukuMuutu(murtoluku2, osoittaja2, nimittaja2);
		return murtolukuosamaara;
	}
	
	private void osamaaraToimii(int osoittaja1, int osoittaja2, int oikeaOsoittaja, int oikeaNimittaja) {
		Object murtolukuosamaara = jaa(osoittaja1, osoittaja2);
		
		int osamaaranOsoittaja = haeOsoittaja(murtolukuosamaara);
		int osamaaranNimittaja = haeNimittaja(murtolukuosamaara);
		if (osamaaranOsoittaja > oikeaOsoittaja && osamaaranNimittaja > oikeaNimittaja && osamaaranOsoittaja%oikeaOsoittaja == 0 && osamaaranNimittaja%oikeaNimittaja == 0) {
			int moninkerta = osamaaranNimittaja/oikeaNimittaja;
			if (osamaaranOsoittaja%moninkerta == 0) {
				fail("Saat laskettua osamäärän helpommin näin: (a/b) / (c/d) = (a*d / b*c)");
			}
		}
		
		assertTrue("Jaa metodin palauttaman murtoluvun osoittaja oli " + osamaaranOsoittaja + " kun sen piti olla " + oikeaOsoittaja + ". Laskettiin " + osoittaja1+"/1"+" / "+osoittaja2+"/1", osamaaranOsoittaja == oikeaOsoittaja);
		assertTrue("Jaa metodin palauttaman murtoluvun nimittäjä oli " + osamaaranNimittaja + " kun sen piti olla " + oikeaNimittaja + ". Laskettiin " + osoittaja1+"/1"+" / "+osoittaja2+"/1", osamaaranNimittaja == oikeaNimittaja);
	}
	
	private Object jaa(int osoittaja1, int osoittaja2) {
		Object murtoluku1 = null, murtoluku2 = null;
		try {
			murtoluku1 = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja1);
			murtoluku2 = ReflectionUtils.invokeConstructor(konstruktori1, osoittaja2);
		} catch (Throwable t) {
			fail("Tätä virhettä ei pitänyt tulla jos aiemmat vaiheet toimivat. Luodaan kaksi murtolukua.");
		}
		Object murtolukuosamaara = jaa(murtoluku1, murtoluku2);
		eihanAlkuperainenMurtolukuMuutu(murtoluku1, osoittaja1, 1);
		eihanAlkuperainenMurtolukuMuutu(murtoluku2, osoittaja2, 1);
		return murtolukuosamaara;
	}
	
	private Object jaa(Object murtoluku1, Object murtoluku2) {
		Method metodi = haeMetodi(luokka, "Murtoluku", "jaa", luokka, "Murtoluku");
		try {
			return ReflectionUtils.invokeMethod(luokka, metodi, murtoluku1, murtoluku2);
		} catch (Throwable t) {
			fail("Koetettiin käyttää jaa-metodia. Omituinen virhe jos aiemmat testit menevät läpi.");
			return null;
		}
	}
	
	@Test
	@Points("1.2.5")
	public void sievennysToimii() {
		sievennysToimii(2, 4, 1, 2);
		sievennysToimii(3, 9, 1, 3);
		sievennysToimii(4, 6, 2, 3);
		sievennysToimii(27, 3, 9, 1);
		
		sievennysToimii(1, -4, -1, 4);
		sievennysToimii(4, -5, -4, 5);
		sievennysToimii(12, -44, -3, 11);
		sievennysToimii(18, -12, -3, 2);
		
		sievennysToimii(-5, -20, 1, 4);
		sievennysToimii(-6, -2, 3, 1);
	}
	
	private void sievennysToimii(int osoittaja1, int nimittaja1, int oikeaOsoittaja, int oikeaNimittaja) {
		Object murtoluku = null;
		try {
			murtoluku = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja1, nimittaja1);
		} catch (Throwable t) {
			fail("Outo virhe, jos aiemmat testit on mennyt läpi. Koitettiin käyttää kahden parametrin konstruktoria.");
		}
		
		int haettuOsoittaja = haeOsoittaja(murtoluku);
		int haettuNimittaja = haeNimittaja(murtoluku);
		assertTrue("Konstruktori ei sieventänyt oikein vaan osoittaja oli väärä. Luotiin murtoluku " + osoittaja1 + "/" + nimittaja1 + " ja sievennetyn osoittajan olisi tullut olla " + oikeaOsoittaja + " mutta se oli " + haettuOsoittaja, haettuOsoittaja == oikeaOsoittaja);
		assertTrue("Konstruktori ei sieventänyt oikein vaan nimittäjä oli väärä. Luotiin murtoluku " + osoittaja1 + "/" + nimittaja1 + " ja sievennetyn nimittäjän olisi tullut olla " + oikeaNimittaja + " mutta se oli " + haettuNimittaja, haettuNimittaja == oikeaNimittaja);
	}
	
	
	
	private int haeOsoittaja(Object murtoluku) {
		Method metodi = ReflectionUtils.requireMethod(luokka, int.class, "haeOsoittaja");
		try {
			return ReflectionUtils.invokeMethod(int.class, metodi, murtoluku);
		} catch (Throwable t) {
			fail("Ethän palauta null-viitteitä. Muutoin tätä ei pitäisi tulla, jos haeOsoittaja toimii.");
			return 0;
		}
	}
	
	private int haeNimittaja(Object murtoluku) {
		Method metodi = ReflectionUtils.requireMethod(luokka, int.class, "haeNimittaja");
		try {
			return ReflectionUtils.invokeMethod(int.class, metodi, murtoluku);
		} catch (Throwable t) {
			fail("Ethän palauta null-viitteitä. Muutoin tätä ei pitäisi tulla, jos haeNimittaja toimii.");
			return 0;
		}
	}
	
	private void eihanAlkuperainenMurtolukuMuutu(Object murtoluku, int osoittaja, int nimittaja) {
		int uusiOsoittaja = haeOsoittaja(murtoluku);
		int uusiNimittaja = haeNimittaja(murtoluku);
		
		if (uusiOsoittaja != osoittaja || uusiNimittaja != nimittaja) {
			fail("Laskutoimituksessa ei saa muuttaa kumpaakaan alkuperäisistä murtoluvuista.");
		}
	}
	/*
	@Test
	@Points("1.2.5")
	public void sievennysNimittajallaNolla() {
		sievennysToimiiKunNimittajaNolla(2, 0);
	}
	
	private void sievennysToimiiKunNimittajaNolla(int osoittaja1, int nimittaja1) {
		Object murtoluku = null;
		try {
			murtoluku = ReflectionUtils.invokeConstructor(konstruktori2, osoittaja1, nimittaja1);
		} catch (Throwable t) {
			fail("Koodissasi on mahdollisesti if (a%b == 0), lisää tämän eteen if (b == 0) {return a;} (ja muuta edellinen if -> else if).\nJos edellinen vinkki ei sovi ratkaisuusi niin muuta koodiasi niin, että jos annettu nimittaja on nolla niin se muutetaan ykköseksi.");
		}
	}
	*/
}
