/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import fi.helsinki.cs.tmc.edutestutils.Reflex.ClassRef;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef0;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.security.Permission;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.TreeSet;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleText;
import javax.swing.*;
import org.fest.swing.core.matcher.JButtonMatcher;
import org.fest.swing.core.matcher.JLabelMatcher;
import org.fest.swing.driver.JMenuItemMatcher;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.EdtViolationException;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.*;
import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.fest.swing.launcher.ApplicationLauncher;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Usagi-chan
 */
public class KayttoliittymaTest extends FestSwingJUnitTestCase {

	private FrameFixture frame;

	@BeforeClass
	public static void setUpClass() throws Exception {
		onkoLuokkaa("Main");
		onkoLuokkaa("Varitesti");
		onkoMetodia("Varitesti", "run");
		implementoikoLuokkaRunnablen("Varitesti", "Runnable");
	}

	@Override
	public void onSetUp() {
		ApplicationLauncher.application(Main.class).start();
		try {
			robot().settings().delayBetweenEvents(100);
		} catch (Exception e) {
			robot().settings().delayBetweenEvents(500);
		}

		try {
			frame = WindowFinder.findFrame(JFrame.class).using(robot());
		} catch (Exception e) {
			fail("Ei löytynyt JFramea. Olethan luonut sen, asettanut sen näkyväksi ja käynnistänyt sen main:ssa.");
		}
	}

	private static Object onkoLuokkaa(String luokanNimi) {
		try {
			return Reflex.reflect(luokanNimi).constructor().takingNoParams().invoke();
		} catch (EdtViolationException e) {
			fail("Ikkunan Swing-komponentteja ei saa luoda luokkaa rakentaessa (konstruktorissa tai privaatti muuttujien määrittelyissä). (EdtViolationException)");
		} catch (Throwable t) {
			fail("Olethan luonut luokan " + luokanNimi + " ja luonut sille myös parametrittoman konstruktorin jos olet luonut sille parametrillisen konstruktorin.");
		}
		return null;
	}

	private static MethodRef0<Object, Void> onkoMetodia(String luokanNimi, String metodinNimi) {
		MethodRef0<Object, Void> metodi = Reflex.reflect(luokanNimi).method(metodinNimi).returningVoid().takingNoParams();
		if (!metodi.exists()) {
			fail("Olethan luonut metodin " + metodinNimi + " luokkaan " + luokanNimi);
		}
		return metodi;
	}

	private static void periikoLuokka(String luokanNimi, String perittavanLuokanNimi) {
		Class luokka = null;
		try {
			luokka = ReflectionUtils.findClass(luokanNimi);
		} catch (Throwable t) {
			fail("Virhe rajapinnan tarkistuksessa.");
		}

		if (!Runnable.class.isAssignableFrom(luokka)) {
			fail("Luokan " + luokanNimi + " tulee toteuttaa rajapinta " + perittavanLuokanNimi);
		}
	}

	private static void implementoikoLuokkaRunnablen(String luokanNimi, String rajapinnanNimi) {
		Class luokka = null;
		try {
			luokka = ReflectionUtils.findClass(luokanNimi);
		} catch (Throwable t) {
			fail("Virhe rajapinnan tarkistuksessa, koska luokkaa " + luokanNimi + " ei löydetty.");
		}

		if (!Runnable.class.isAssignableFrom(luokka)) {
			fail("Luokan " + luokanNimi + " tulee toteuttaa rajapinta " + rajapinnanNimi);
		}
	}

	@Test
	@Points("3.4.1")
	public void loytyykoKomponentit() {
		JLabelFixture teksti = annaLabel("sininen", "punainen");
		JButtonFixture sininen = annaNappula("sininen");
		JButtonFixture punainen = annaNappula("punainen");
		JLabelFixture pisteet = annaLabel("Pisteet: 0");
		JLabelFixture aika = annaLabel("Aika: 60");

		onkoKomponentitOikeissaPaikoissaan(teksti, sininen, punainen, pisteet, aika);
	}

	public JLabelFixture annaLabel(String teksti1, String teksti2) {
		try {
			return frame.label(JLabelMatcher.withText(teksti1));
		} catch (ComponentLookupException e) {
			try {
				return frame.label(JLabelMatcher.withText(teksti2));
			} catch (ComponentLookupException e2) {
				fail("Olethan luonut JLabelin, jossa lukee " + teksti1 + " tai " + teksti2);
				return null;
			}
		}
	}

	public JLabelFixture annaLabel(String teksti) {
		try {
			return frame.label(JLabelMatcher.withText(teksti));
		} catch (ComponentLookupException e) {
			fail("Olethan luonut JLabelin, jossa lukee " + teksti);
			return null;
		}
	}

	public JButtonFixture annaNappula(String teksti) {
		try {
			return frame.button(JButtonMatcher.withText(teksti));
		} catch (ComponentLookupException e) {
			fail("Olethan luonut nappulan, jossa lukee " + teksti);
			return null;
		}
	}

	private void onkoKomponentitOikeissaPaikoissaan(JLabelFixture teksti, JButtonFixture sininen, JButtonFixture punainen, JLabelFixture pisteet, JLabelFixture aika) {
		onkoLabelJaNappulatOikeassaJarjestyksessa(teksti, sininen, punainen);
		onkoNappulatSamallaRivilla(sininen, punainen);
		onkoLaskuritAlimpana(sininen, punainen, pisteet, aika);
		onkoLaskuritSamallaKorkeudella(pisteet, aika);

		onkoJButtonitErotettavissa(sininen, punainen);
	}

	private void onkoLabelJaNappulatOikeassaJarjestyksessa(JLabelFixture teksti, JButtonFixture sininen, JButtonFixture punainen) {
		if (teksti.component().getLocationOnScreen().y >= sininen.component().getLocationOnScreen().y) {
			fail("Kysymys-labelin tulisi olla vastausvaihtoehto-nappuloiden yläpuolella.\nTämän saat valitsemalla layoutin missä komponentit voi asetella y-akselin suuntaisesti (Box, Grid).");
		}
		if (teksti.component().getLocationOnScreen().y >= punainen.component().getLocationOnScreen().y) {
			fail("Kysymys-labelin tulisi olla vastausvaihtoehto-nappuloiden yläpuolella.\nTämän saat valitsemalla layoutin missä komponentit voi asetella y-akselin suuntaisesti (Box, Grid).");
		}
	}

	private void onkoNappulatSamallaRivilla(JButtonFixture sininen, JButtonFixture punainen) {
		if (Math.abs(sininen.component().getLocationOnScreen().y - punainen.component().getLocationOnScreen().y) > 10) {
			fail("Vaihtoehto-nappuloiden tuli olla samalla rivillä.\nSaavutat tämän kun laitat Containeriin sisemmän Containerin ja siihen nappulat x-suuntaiseen layouttiin (Flow, Grid, Box).");
		}
	}

	private void onkoLaskuritAlimpana(JButtonFixture sininen, JButtonFixture punainen, JLabelFixture pisteet, JLabelFixture aika) {
		int nappuloidenY = Math.max((int) sininen.component().getLocationOnScreen().y, (int) punainen.component().getLocationOnScreen().y);

		if (pisteet.component().getLocationOnScreen().y <= nappuloidenY) {
			fail(pisteet.component().getLocationOnScreen().y + " " + nappuloidenY + "Pisteet-labelin tuli olla vaihtoehto-nappuloiden alapuolella.\nTämän saat valitsemalla layoutin missä komponentit voi asetella y-akselin suuntaisesti (Box, Grid).");
		}
		if (aika.component().getLocationOnScreen().y <= nappuloidenY) {
			fail("Aika-labelin tuli olla vaihtoehto-nappuloiden alapuolella.\nTämän saat valitsemalla layoutin missä komponentit voi asetella y-akselin suuntaisesti (Box, Grid).");
		}
	}

	private void onkoLaskuritSamallaKorkeudella(JLabelFixture pisteet, JLabelFixture aika) {
		if (Math.abs(pisteet.component().getLocationOnScreen().y - aika.component().getLocationOnScreen().y) > 10) {
			fail("Pisteet-ja Aika-labeleiden tuli olla samalla rivillä.\nSaavutat tämän kun laitat Containeriin sisemmän Containerin ja siihen nappulat x-suuntaiseen layouttiin (Flow, Grid, Box).");
		}
	}

	private void onkoJButtonitErotettavissa(JButtonFixture sininen, JButtonFixture punainen) {
		int valimatka = sininen.target.getLocationOnScreen().x - punainen.target.getLocationOnScreen().x;
		if (Math.abs(valimatka) < sininen.target.getWidth()) {
			fail("Ikkunan koko tulee asettaa setPreferredSize-komennolla tarpeeksi suureksi, jotta ikkunan komponentit eivät mene päällekkäin missään käyttöjärjestelmässä.");
		}
	}

	@Test
	@Points("3.4.2")
	public void testaaVaritykset() {
		JButton sininen = annaNappula("sininen").target;
		JButton punainen = annaNappula("punainen").target;

		testaaTaustanVari();
		testaaNappuloidenTaustat(sininen, punainen);
		testaaLabelienTaustat();
		testaaNappuloidenReunojenPiirtamattomyydet(sininen, punainen);
	}

	private Container haeContainer() {
		return ((JFrame) frame.target).getContentPane();
	}

	private void testaaTaustanVari() {
		Container container = haeContainer();

		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("Mac OS X".toLowerCase())) {
			if (frame.background().target() == Color.BLACK && container.getBackground() != Color.BLACK) {
				fail("Macissä taustan värin voi asettaa JFramessa, mutta muissa käyttöjärjestelmissä se pitää määrittää Containerin kautta.");
			}
		}

		if (container.getBackground() != Color.BLACK) {
			fail("Asetathan Containerin taustavärin mustaksi. (BLACK)");
		}
	}

	private void testaaNappuloidenTaustat(JButton sininen, JButton punainen) {
		if (sininen.getBackground() != Color.BLACK || punainen.getBackground() != Color.BLACK) {
			fail("Asetathan kaikkien JButtonien taustavärin mustaksi. (BLACK)");
		}

		if (sininen.getForeground() != Color.WHITE) {
			if (sininen.getForeground() == Color.BLUE || sininen.getForeground() == Color.RED) {
			} else {
				fail("Asetathan kaikkien JButtonien tekstit valkoisiksi. (WHITE)");
			}
		}

		if (punainen.getForeground() != Color.WHITE) {
			if (punainen.getForeground() == Color.BLUE || punainen.getForeground() == Color.RED) {
			} else {
				fail("Asetathan kaikkien JButtonien tekstit valkoisiksi. (WHITE)");
			}
		}
	}

	private void testaaNappuloidenReunojenPiirtamattomyydet(JButton sininen, JButton punainen) {
		if (sininen.isBorderPainted() || punainen.isBorderPainted()) {
			fail("Määritäthän kaikkien nappuloiden reunuksen, että niitä ei piirretä. (BorderPainted)");
		}

		if (sininen.isFocusable()) {
			if (sininen.isFocusPainted()) {
				fail("Määritäthän kaikkien nappuloiden fokuksen, että niitä ei piirretä. (setFocusable)");
			}
		}
		if (punainen.isFocusable()) {
			if (punainen.isFocusPainted()) {
				fail("Määritäthän kaikkien nappuloiden fokuksen, että niitä ei piirretä. (setFocusable)");
			}
		}
	}

	private void testaaLabelienTaustat() {
		JLabel label = annaLabel("sininen", "punainen").target;
		JLabel pisteet = annaLabel("Pisteet: 0").target;
		JLabel aika = annaLabel("Aika: 60").target;

		ArrayList<Color> sallitutVarit = new ArrayList<Color>();
		sallitutVarit.add(Color.WHITE);
		sallitutVarit.add(Color.RED);
		sallitutVarit.add(Color.BLUE);

		if (!sallitutVarit.contains(label.getForeground())) {
			fail("Asetathan ylimmän JLabelin tekstin valkoiseksi.");
		}
		if (pisteet.getForeground() != Color.WHITE) {
			fail("Asetathan pisteet näyttävän JLabelin tekstin valkoiseksi.");
		}
		if (aika.getForeground() != Color.WHITE) {
			fail("Asetathan ajan näyttävän JLabelin tekstin valkoiseksi.");
		}
	}

	@Test
	@Points("3.4.3 3.4.4")
	public void tilanteenArpominenToimii() throws Throwable {
		JButtonFixture sininen = annaNappula("sininen");
		JButtonFixture punainen = annaNappula("punainen");

		testaaArvottavuus(sininen, punainen);
	}

	private void testaaArvottavuus(JButtonFixture... fixturet) throws Throwable {
		TreeSet<String> labelSetti = new TreeSet<String>();
		TreeSet<String> vasenSetti = new TreeSet<String>();
		TreeSet<String> oikeaSetti = new TreeSet<String>();
		TreeSet<String> kysymysVastausSetti = new TreeSet<String>();

		JLabel label = annaLabel("sininen", "punainen").target;

		int maara = 0;
		int kaytettavaFixture = 0;
		while (labelSetti.size() < 4 || vasenSetti.size() < 4 || oikeaSetti.size() < 4 || kysymysVastausSetti.size() < 4) {
			maara++;
			if (maara >= 30) {
				luoVirheIlmoitusVaihteluidenSatunnaisuudesta(kaytettavaFixture, labelSetti, vasenSetti, oikeaSetti, kysymysVastausSetti);
			}

			klikkaaJaKirjaaTulokset(fixturet[kaytettavaFixture], label, fixturet[0].target, fixturet[1].target, labelSetti, vasenSetti, oikeaSetti, kysymysVastausSetti);

			if (kaytettavaFixture == 0 && settejaVahanPaallePuolet(labelSetti, vasenSetti, oikeaSetti, kysymysVastausSetti)) {
				kaytettavaFixture = 1;
			}

			if (maara == 2) {
				if (labelSetti.size() + vasenSetti.size() + oikeaSetti.size() + kysymysVastausSetti.size() == 4) {
					tutkiNapinKuuntelijaa(fixturet[kaytettavaFixture], fixturet[kaytettavaFixture].target.getText());
				}
			}
			if (maara != 0 && maara % 20 == 0) {
				tutkiNapinKuuntelijaa(fixturet[kaytettavaFixture], fixturet[kaytettavaFixture].target.getText());
			}

			if (maara == 8) {
				if (labelSetti.size() == 1) {
					fail("Ylimmän rivin JLabelin teksti tai väri ei näytä muuttuvan.");
				}
				if (vasenSetti.size() == 1 || oikeaSetti.size() == 1) {
					fail("Toisen nappulan teksti tai väri ei näytä muuttuvan.");
				}
				if (kysymysVastausSetti.size() == 1) {
					fail("Yhteen JLabeliin näyttää aina liittyvän jotkin tietyt vastausvaihtoehdot.");
				}
			}
		}
	}

	private void luoVirheIlmoitusVaihteluidenSatunnaisuudesta(int kaytettavaFixture, TreeSet... setit) {
		String lisa = luoVirheilmoitus(setit[0], setit[1], setit[2], setit[3]);
		if (kaytettavaFixture == 0) {
			lisa += "\nVika voi liittyä kumpaankin paineltavaan nappulaan.";
		} else {
			lisa += "\nVika liittyy mahdollisesti vain toiseen paineltavaan nappulaan.";
		}
		fail("Pelin teksti ja värivalinnat eivät näytä toimivan satunnaisesti.\n" + lisa);
	}

	private void klikkaaJaKirjaaTulokset(JButtonFixture nappula, JLabel label, JButton nappula1, JButton nappula2, TreeSet<String>... setit) throws Throwable {
		nappula.focus();
		nappula.click();
		nuku(200);

		setit[0].add(luoPermutaatio(label));
		setit[1].add(luoPermutaatio(nappula1));
		setit[2].add(luoPermutaatio(nappula2));
		setit[3].add(luoPermutaatio(label, nappula1));
	}

	private boolean settejaVahanPaallePuolet(TreeSet... setit) {
		int maara = setit[0].size() + setit[1].size() + setit[2].size() + setit[3].size();

		for (TreeSet setti : setit) {
			if (setti.size() == 1) {
				return false;
			}
		}

		if (maara > 10) {
			return true;
		}
		return false;
	}

	private String luoPermutaatio(JLabel label, JButton nappula) {
		String permutaatio = "";
		if (label.getText().equals("sininen")) {
			permutaatio += "0";
		} else {
			permutaatio += "1";
		}
		if (nappula.getText().equals("sininen")) {
			permutaatio += "0";
		} else {
			permutaatio += "1";
		}
		return permutaatio;
	}

	private String luoPermutaatio(JLabel pala) {
		String permutaatio = "";
		if (pala.getText().equals("sininen")) {
			permutaatio += "0";
		} else {
			permutaatio += "1";
		}
		if (pala.getForeground() == Color.BLUE) {
			permutaatio += "0";
		} else {
			permutaatio += "1";
		}
		return permutaatio;
	}

	private String luoPermutaatio(JButton pala) {
		String permutaatio = "";
		if (pala.getText().equals("sininen")) {
			permutaatio += "0";
		} else {
			permutaatio += "1";
		}
		if (pala.getForeground() == Color.BLUE) {
			permutaatio += "0";
		} else {
			permutaatio += "1";
		}
		return permutaatio;
	}

	private String luoVirheilmoitus(TreeSet<String>... setit) {
		String virheilmoitus = "";

		for (int i = 0; i < 4; i++) {
			if (setit[i].size() < 4) {
				virheilmoitus = puraPermutaatio(setit[i], i);
				return virheilmoitus;
			}
		}

		return "Testi näyttää toimivan omituisesti eikä satunnaisuudessa näytä olevan mitään vikaa. Etsi nopein tapa valittaa asiasta.";
	}

	private String puraPermutaatio(TreeSet<String> setti, int i) {
		String permutaatio = "";
		int merkitys1 = 0, merkitys2 = 1; // 0=teksti, 1=väri
		if (i == 0) {
			permutaatio += "Labelilla";
		} else if (i == 3) {
			merkitys2 = 0;
			permutaatio += "Labelilla ja nappulalla";
		} else {
			permutaatio += "Nappulalla";
		}

		permutaatio += " näyttää olevan vain variaatiot: ";

		for (String tapaus : setti) {
			permutaatio += annaMerkitys(tapaus.charAt(0), merkitys1);
			permutaatio += "-";
			permutaatio += annaMerkitys(tapaus.charAt(1), merkitys2) + " ";
		}

		return permutaatio;
	}

	private String annaMerkitys(char merkki, int merkitys) {
		if (merkitys == 0) {
			if (merkki == '0') {
				return "sininen";
			} else {
				return "punainen";
			}
		} else {
			if (merkki == '0') {
				return "BLUE";
			} else {
				return "RED";
			}
		}
	}

	private void tutkiNapinKuuntelijaa(JButtonFixture nappula, String nappulanTeksti) {
		ActionListener[] tapahtumaKuuntelijaLista = nappula.target.getActionListeners();
		if (tapahtumaKuuntelijaLista.length == 0) {
			boolean opastettiinko = opastaNappulaKuuntelijanTeossa();
			if (opastettiinko) {
				fail("Napilta " + nappulanTeksti + " ei löytynyt tapahtumakuuntelijaa.\nLiitä luotu napinkuuntelija nappiin.");
			}
			fail("Napilta " + nappulanTeksti + " ei löytynyt tapahtumakuuntelijaa.\nJos olet epävarma tämän luomisesta tee tapahtumakuuntelijaa varten luokka \"NapinKuuntelija(Varitesti varitesti)\", jolloin testit voivat tarjota parempia virheilmoituksia.");
		}
	}

	private boolean opastaNappulaKuuntelijanTeossa() {
		try {
			ClassRef varitestiLuokka = Reflex.reflect("Varitesti");
			Object varitesti = onkoLuokkaa("Varitesti");
			Reflex.reflect("NapinKuuntelija").constructor().taking(varitestiLuokka.getReferencedClass()).invoke(varitesti);
		} catch (EdtViolationException e) {
			fail("Ikkunan Swing-komponentteja ei saa luoda luokkaa rakentaessa (konstruktorissa tai privaatti muuttujien määrittelyissä). (EdtViolationException)");
		} catch (Throwable t) {
			return false;
		}

		onkoMetodia("NapinKuuntelija", "actionPerformed", ActionEvent.class);

		implementoikoLuokkaTapahtumanKuuntelijan("NapinKuuntelija", "ActionListener");
		return true;
	}

	@Test
	@Points("3.4.5")
	public void pisteenLaskuToimii() throws Throwable {
		nuku(400);
		JLabel label = annaLabel("sininen", "punainen").target;
		JButtonFixture ekaNappula = annaNappula("sininen");
		JButtonFixture tokaNappula = annaNappula("punainen");
		JLabel pisteetLabel = annaLabel("Pisteet: 0").target;
		int pisteet = Integer.parseInt(pisteetLabel.getText().split(" ")[1]);

		int maara = 0;
		boolean valittavaNappi = true;
		while (maara < 5) {
			nuku(400);
			klikkaaHaluttuaNappulaa(label, ekaNappula, tokaNappula, valittavaNappi);

			nuku(400);

			pisteet = tarkistaPisteet(pisteetLabel, pisteet, valittavaNappi);

			if (maara == 1) {
				valittavaNappi = !valittavaNappi;
			}
			maara++;
		}
	}

	private void nuku(int aika) {
		while (true) {
			try {
				Thread.sleep(aika);
			} catch (InterruptedException e) {
				continue;
			}
			return;
		}
	}

	private void klikkaaHaluttuaNappulaa(JLabel label, JButtonFixture ekaNappula, JButtonFixture tokaNappula, boolean valittavaNappi) {
		if (!(label.getForeground() == Color.BLUE || label.getForeground() == Color.RED)) {
			fail("JLabelin väri ei ollut RED tai BLUE, joten testirobotti ei osannut valita oikeaa nappulaa. JLabelin väri oli " + label.getForeground().toString());
		}

		try {
			if (ekaNappula.target.getText().equals("sininen") == (label.getForeground() == Color.BLUE)) {
				if (valittavaNappi) {
					ekaNappula.focus();
					ekaNappula.click();
				} else {
					tokaNappula.focus();
					tokaNappula.click();
				}
			} else {
				if (valittavaNappi) {
					tokaNappula.focus();
					tokaNappula.click();
				} else {
					ekaNappula.focus();
					ekaNappula.click();
				}
			}
		} catch (Throwable t) {
			fail("Nappulaa ei löydetty. Voi olla, että olet tuhonnut nappulat ja luonut uudet, mutta tehtävässä on tarkoitus vain vaihtaa nappulan tekstiä.");
		}
	}

	private JButtonFixture valitseNappi(Color valittavaVari, boolean valittavaNappi) {
		if (valittavaVari == Color.RED) {
			if (valittavaNappi) {
				return annaNappula("punainen");
			} else {
				return annaNappula("sininen");
			}
		} else if (valittavaVari == Color.BLUE) {
			if (valittavaNappi) {
				return annaNappula("sininen");
			} else {
				return annaNappula("punainen");
			}
		}
		fail("Tekstin väri oli väärä. JLabelin väri oli " + valittavaVari.toString() + " (RGB)");
		return null;
	}

	private int tarkistaPisteet(JLabel label, int pisteet, boolean valittuNappiOikea) {
		if (valittuNappiOikea) {
			pisteet++;
		} else {
			pisteet--;
		}

		String pisteetLabel = label.getText();
		int pisteetNumero = 0;
		try {
			pisteetNumero = Integer.parseInt(pisteetLabel.split(" ")[1]);
		} catch (Exception e) {
			fail("Pisteet-labelin pisteet eivät olleet muunnettavissa numeroksi. Labelin teksti oli " + pisteetLabel);
		}

		String pistevihje = "\nPelissä tuli katsoa JLabelin väri (ei teksti) ja valita sen perusteella JButton, jossa väri on kirjoitettuna. JLabelin teksti ja JButtonien värit ovat vain sekoittamassa pelaajaa.";

		if (pisteetNumero != pisteet) {
			if (pisteet == 1 && pisteetNumero == 0) {
				fail("Pisteiden lasku ei toimi. Testirobotti valitsi oikean nappulan, mutta pisteitä tuli nolla." + pistevihje);
			} else if (valittuNappiOikea) {
				fail("Pisteiden lasku ei toimi. Testirobotti valitsi oikean nappulan ja oletettiin, että pisteitä olisi " + pisteet + ", mutta pisteitä oli kirjattuna " + pisteetNumero + pistevihje);
			} else if (pisteet < 0) {
				fail("Pisteiden lasku ei toimi kun pisteet menevät negatiivisiksi.");
			} else {
				fail("Pisteiden lasku ei toiminut kun testirobotti valitsi väärän nappulan ja oletettiin, että pisteitä olisi " + pisteet + ", mutta pisteitä oli kirjattuna " + pisteetNumero + pistevihje);
			}
		}

		return pisteet;
	}

	@Test
	@Points("3.4.6")
	public void loytyykoAjastin() throws Throwable {
		toimiikoAjastin();
	}

	private void toimiikoAjastin() throws Throwable {
		JLabel aikaLabel = annaLabel("Aika: 60").target;
		int aika = 60;

		JButtonFixture nappula = annaNappula("sininen");
		nappula.focus();
		nappula.click();

		nuku(300);

		for (int i = 0; i < 3; i++) {
			nuku(1020);

			aika--;
			String aikaString = aikaLabel.getText();
			if (Integer.parseInt(aikaString.split(" ")[1]) > aika) {
				fail("Pelin edetessä oletettiin, että aika olisi korkeintaan " + aika + ", mutta JLabelissa luki " + aikaString);
			}
		}
	}

	@Test
	@Points("3.4.7")
	public void toimiikoUusiPeliJaLopetus() throws Throwable {
		onkoMetodia("Varitesti", "uusiPeli");
		toimiikoUusiPeli();

		onkoMetodia("Varitesti", "suljePeli");
		toimiikoLopeta();
	}

	private void toimiikoUusiPeli() throws Throwable {
		JLabel pisteetLabel = annaLabel("Pisteet: 0").target;
		JLabel aikaLabel = annaLabel("Aika: 60").target;
		int aika = 60;

		JButtonFixture nappula = annaNappula("sininen");
		nappula.focus();
		nappula.click();

		nuku(200);

		frame.pressAndReleaseKeys(KeyEvent.VK_F2);
		nuku(200);
		if (!pisteetLabel.getText().equals("Pisteet: 0")) {
			tutkiNappaimistonKuuntelijaa();
			fail("Kun painetaan F2:sta, pelin tulisi alkaa alusta ja pistemäärän tulisi olla jälleen 0. Nyt JLabelissa luki " + pisteetLabel.getText());
		}

		nuku(1010);
		if (!aikaLabel.getText().equals("Aika: 60")) {
			fail("Kun painetaan F2:sta, pelin tulisi alkaa alusta, mutta ajan ei tulisi vähentyä ennen kuin pelaaja klikkaa jotain nappulaa. Nyt sekunti uuden pelin valinnasta JLabelissa luki " + aikaLabel.getText());
		}
	}

	private void toimiikoLopeta() throws Throwable {
		final EiKaatuja eiKaatuja = new EiKaatuja();
		SecurityManager oletus = System.getSecurityManager();
		System.setSecurityManager(eiKaatuja);
		try {
			frame.pressAndReleaseKeys(KeyEvent.VK_ESCAPE);
		} catch (SecurityException e) {
			return;
		} catch (Throwable t) {
			fail("Tapahtui joku virhe." + t.getMessage());
		} finally {
			System.setSecurityManager(oletus);
		}

		try {
			nuku(100);
			frame.requireNotVisible();
		} catch (Throwable t) {
			tutkiNappaimistonKuuntelijaa();
			fail("Kun painetaan ESCiä ohjelman tulisi sulkeutua. Käytä JFramen dispose-komentoa.");
		}
	}

	private static class EiKaatuja extends SecurityManager {

		public boolean kaatui = false;

		@Override
		public void checkExit(int status) {
			kaatui = true;
			throw new SecurityException("ETPÄS KAADU");
		}

		@Override
		public void checkPermission(Permission perm) {
		}

		@Override
		public void checkPermission(Permission perm, Object context) {
		}
	}

	private void tutkiNappaimistonKuuntelijaa() {
		KeyListener[] nappainKuuntelijaLista = frame.target.getKeyListeners();
		if (nappainKuuntelijaLista.length == 0) {
			boolean opastettiinko = opastaNappaimistonKuuntelijanTeossa();
			if (opastettiinko) {
				fail("JFramelta ei löytynyt näppäimistön kuuntelijaa.\nLiitä luotu näppäimistön kuuntelija ikkunaan.");
			}
			fail("JFramelta ei löytynyt näppäimistön kuuntelijaa.\nJos olet epävarma tämän luomisesta tee näppäimistökuuntelijaa varten luokka \"NappaimistonKuuntelija(Varitesti varitesti)\", jolloin testit voivat tarjota parempia virheilmoituksia.");
		}
	}

	private boolean opastaNappaimistonKuuntelijanTeossa() {
		try {
			ClassRef varitestiLuokka = Reflex.reflect("Varitesti");
			Object varitesti = onkoLuokkaa("Varitesti");
			Reflex.reflect("NappaimistonKuuntelija").constructor().taking(varitestiLuokka.getReferencedClass()).invoke(varitesti);
		} catch (EdtViolationException e) {
			fail("Ikkunan Swing-komponentteja ei saa luoda luokkaa rakentaessa (konstruktorissa tai privaatti muuttujien määrittelyissä). (EdtViolationException)");
		} catch (Throwable t) {
			return false;
		}

		onkoMetodia("NappaimistonKuuntelija", "keyTyped", KeyEvent.class);
		onkoMetodia("NappaimistonKuuntelija", "keyPressed", KeyEvent.class);
		onkoMetodia("NappaimistonKuuntelija", "keyReleased", KeyEvent.class);

		implementoikoLuokkaNappaimistonKuuntelijan("NappaimistonKuuntelija", "KeyListener");
		return true;
	}

	private static void implementoikoLuokkaNappaimistonKuuntelijan(String luokanNimi, String rajapinnanNimi) {
		Class luokka = null;
		try {
			luokka = ReflectionUtils.findClass(luokanNimi);
		} catch (Throwable t) {
			fail("Virhe rajapinnan tarkistuksessa, koska luokkaa " + luokanNimi + " ei löydetty.");
		}

		if (!KeyListener.class.isAssignableFrom(luokka)) {
			fail("Luokan " + luokanNimi + " tulee toteuttaa rajapinta " + rajapinnanNimi);
		}
	}

	@Test
	@Points("3.4.8 3.4.9")
	public void valikkoToimii() throws Throwable {
		JMenuItemFixture peli = annaMenu("Peli");
		if (!(peli.target instanceof JMenu)) {
			fail("Jmenun tuli olla Peli.");
		}
		JMenuItemFixture uusiPeli = annaMenuItem("Uusi peli");
		JMenuItemFixture tuloslista = annaMenuItem("Tuloslista");
		JMenuItemFixture sulje = annaMenuItem("Sulje");

		toimiikoUusiPeliMenunKautta(peli, uusiPeli);
		toimiikoEnnatyslistaMenunKautta(peli, tuloslista);
		toimiikoSuljeMenunKautta(peli, sulje);
	}

	private JMenuItemFixture annaMenu(String teksti) {
		try {
			return frame.menuItemWithPath(teksti);
		} catch (ComponentLookupException e) {
			fail("Ei löydetty JMenua, jossa olisi teksti \"" + teksti + "\".");
			return null;
		}
	}

	private JMenuItemFixture annaMenuItem(String teksti) {
		try {
			return frame.menuItemWithPath("Peli", teksti);
		} catch (ComponentLookupException e) {
			fail("Ei löydetty JMenuItemia, jossa olisi teksti \"" + teksti + "\" ja joka olisi JMenussa Peli.");
			return null;
		}
	}

	private void toimiikoUusiPeliMenunKautta(JMenuItemFixture menu, JMenuItemFixture menuItem) throws Throwable {
		JLabel aikaLabel = annaLabel("Aika: 60").target;
		int aika = 60;

		JButtonFixture nappula = annaNappula("sininen");
		nappula.focus();
		nappula.click();

		eteneSekunti(aikaLabel, aika);

		try {
			menu.focus();
			menu.click();
		} catch (Throwable t) {
			fail("Onhan JMenu \"Peli\" näkyvissä.");
		}
		menuItem.focus();
		menuItem.click();

		nuku(400);

		if (!aikaLabel.getText().equals("Aika: 60")) {
			tutkiMenunTapahtumakuuntelijaa(menuItem, "Uusi peli");
			fail("Kun painetaan menun uusi peli-valintaa, pelin tulisi alkaa alusta ajan tulisi olla jälleen 60. Nyt JLabelissa luki " + aikaLabel.getText());
		}

		nuku(1010);
		if (!aikaLabel.getText().equals("Aika: 60")) {
			fail("Kun painetaan menun uusi peli-valintaa, pelin tulisi alkaa alusta, mutta ajan ei tulisi vähentyä ennen kuin pelaaja klikkaa jotain nappulaa. Nyt sekunti uuden pelin valinnasta JLabelissa luki " + aikaLabel.getText());
		}
	}

	private int eteneSekunti(JLabel aikaLabel, int aika) throws Throwable {
		nuku(1400);
		aika--;
		String aikaString = aikaLabel.getText();
		if (Integer.parseInt(aikaString.split(" ")[1]) > aika) {
			fail("Pelin edetessä oletettiin, että aika olisi " + aika + ", mutta JLabelissa luki " + aikaString);
		}
		return aika;
	}

	private void toimiikoEnnatyslistaMenunKautta(JMenuItemFixture menu, JMenuItemFixture menuItem) throws Throwable {
		menu.focus();
		menu.click();
		menuItem.focus();
		menuItem.click();

		DialogFixture dialog = annaEnnatyslista(menuItem);
		JDialog dailog = (JDialog) dialog.target;
		String sisalto = ((JOptionPane) dailog.getContentPane().getComponent(0)).getMessage().toString();
		etsiSisallostaKymmenenSijaa(sisalto);

		dialog.close();
	}

	private DialogFixture annaEnnatyslista(JMenuItemFixture menuItem) {
		try {
			nuku(500);
			return frame.dialog();
		} catch (WaitTimedOutError e) {
			tutkiMenunTapahtumakuuntelijaa(menuItem, "Ennätyslista");
			fail("Ennätyslista-MessageDialog:ia ei ilmestynyt kun se koitettiin avata menun kautta.");
			return null;
		} catch (Throwable t) {
			tutkiMenunTapahtumakuuntelijaa(menuItem, "Ennätyslista");
			fail("Ennätyslistan avautumisessa (menun kautta) ilmeni ongelma.");
			return null;
		}
	}

	private void etsiSisallostaKymmenenSijaa(String ennatyslista) {
		int loydettyjenSijojenMaara = 0;
		int edellisenPisteet = 1000;
		boolean loytyyNollastaPoikkeavaTulos = false;

		for (String rivi : ennatyslista.split("\n")) {
			if (onkoSija(rivi, edellisenPisteet)) {
				loydettyjenSijojenMaara++;
				edellisenPisteet = Integer.parseInt(rivi.split(" ")[1]);
				if (edellisenPisteet != 0) {
					loytyyNollastaPoikkeavaTulos = true;
				}
			}
		}

		if (loydettyjenSijojenMaara == 10) {
			if (!loytyyNollastaPoikkeavaTulos) {
				fail("Varmista, että pelin loppuessa ja tuloksen päästessä ennätyslistalle kysytään pelaajan nimi ja tulos sijoitetaan ennätyslistalle." + "\nOlethan tallentanut tuloslistan Source Packages kansioon, jotta serverin testi pääsee tarkastelemaan sitä (esim. \"src/tiedosto.txt\")");
			}
			return;
		} else {
			fail("Ennätyslistasta ei löytynyt kymmentä sijaa, joiden muoto olisi ollut \"nimi numero\".\nLöydettyjä sopivia rivejä oli\n" + loydettyjenSijojenMaara + "\nTuloslistan sisältö oli: " + ennatyslista + "\nOlethan tallentanut tuloslistan Source Packages kansioon, jotta serverin testi pääsee tarkastelemaan sitä (esim. \"src/tiedosto.txt\")");
		}
	}

	private boolean onkoSija(String rivi, int edellisenPisteet) {
		String[] osat = rivi.split(" ");
		if (osat.length < 2) {
			return false;
		}
		for (int i = 0; i < osat.length; i++) {
			if (osat[i].matches("[0123456789]+")) {
				if (edellisenPisteet != 1000) {
					int tamanPisteet = Integer.parseInt(osat[1]);
					if (edellisenPisteet < tamanPisteet) {
						fail("Ennätyslista ei ollut järjestetty laskevasti. Edellisellä tarkastellulla rivillä pisteitä oli " + edellisenPisteet + " ja nyt tarkastellulla rivillä pisteitä oli " + tamanPisteet);
					}
				}
				return true;
			}
		}
		return false;
	}

	private void toimiikoSuljeMenunKautta(JMenuItemFixture menu, JMenuItemFixture menuItem) throws Throwable {
		final EiKaatuja eiKaatuja = new EiKaatuja();
		SecurityManager oletus = System.getSecurityManager();
		System.setSecurityManager(eiKaatuja);
		try {
			menu.focus();
			menu.click();
			menuItem.focus();
			menuItem.click();
		} catch (SecurityException e) {
			return;
		} catch (Throwable t) {
			fail("Tapahtui joku virhe." + t.getMessage());
		} finally {
			System.setSecurityManager(oletus);
		}

		try {
			nuku(300);
			frame.requireNotVisible();
		} catch (Throwable t) {
			tutkiMenunTapahtumakuuntelijaa(menuItem, "Sulje");
			fail("Kun painetaan menun sulje-vaihtoehtoa ohjelman tulisi sulkeutua. Käytä JFramen dispose-komentoa.");
		}

	}

	private void tutkiMenunTapahtumakuuntelijaa(JMenuItemFixture item, String valittuItem) {
		ActionListener[] tapahtumakuuntelijaLista = item.target.getActionListeners();
		if (tapahtumakuuntelijaLista.length == 0) {
			boolean opastettiinko = opastaValikonKuuntelijanTeossa(valittuItem);
			if (opastettiinko) {
				fail("Menun valinnalle " + valittuItem + " ei löydetty tapahtumakuuntelijaa.\nLiitä luotu tapahtumakuuntelija JMenuItemiin.");
			}
			fail("Menun valinnalle " + valittuItem + " ei löydetty tapahtumakuuntelijaa.\nJos olet epävarma tämän luomisesta tee tapahtumakuuntelijaa varten luokka \"ValikonKuuntelija(Varitesti varitesti, String tunniste)\", jolloin testit voivat tarjota parempia virheilmoituksia.");
		}
	}

	private boolean opastaValikonKuuntelijanTeossa(String valittuItem) {
		try {
			ClassRef varitestiLuokka = Reflex.reflect("Varitesti");
			Object varitesti = onkoLuokkaa("Varitesti");
			Reflex.reflect("ValikonKuuntelija").constructor().taking(varitestiLuokka.getReferencedClass(), String.class).invoke(varitesti, valittuItem);
		} catch (EdtViolationException e) {
			fail("Ikkunan Swing-komponentteja ei saa luoda luokkaa rakentaessa (konstruktorissa tai privaatti muuttujien määrittelyissä). (EdtViolationException)");
		} catch (Throwable t) {
			return false;
		}

		onkoMetodia("ValikonKuuntelija", "actionPerformed", ActionEvent.class);
		implementoikoLuokkaTapahtumanKuuntelijan("ValikonKuuntelija", "ActionListener");
		return true;
	}

	private static MethodRef1 onkoMetodia(String luokanNimi, String metodinNimi, Class parametri) {
		MethodRef1 metodi = Reflex.reflect(luokanNimi).method(metodinNimi).returningVoid().taking(parametri);
		if (!metodi.exists()) {
			fail("Olethan luonut metodin " + metodinNimi + " luokkaan " + luokanNimi + ". Metodin tulee ottaa parametriksi " + parametri.getSimpleName());
		}
		return metodi;
	}

	private static void implementoikoLuokkaTapahtumanKuuntelijan(String luokanNimi, String rajapinnanNimi) {
		Class luokka = null;
		try {
			luokka = ReflectionUtils.findClass(luokanNimi);
		} catch (Throwable t) {
			fail("Virhe rajapinnan tarkistuksessa, koska luokkaa " + luokanNimi + " ei löydetty.");
		}

		if (!ActionListener.class.isAssignableFrom(luokka)) {
			fail("Luokan " + luokanNimi + " tulee toteuttaa rajapinta " + rajapinnanNimi);
		}
	}
}
