
import fi.helsinki.cs.tmc.edutestutils.MockInOut;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef0;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef1;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author kviiri
 */
public class TuotevarastoTest {

    private String tuotevarastoLuokka = "Tuotevarasto";
    private MethodRef0<Object, Void> lisaaYksi;
    private MethodRef1<Object, Void, Integer> lisaaMonta;
    private MethodRef0<Object, Void> poistaYksi;
    private MethodRef1<Object, Void, Integer> poistaMonta;
    private MethodRef0<Object, Integer> haeMaara;

    public TuotevarastoTest() {
    }

    private void init1() {
        lisaaYksi = Reflex.reflect(tuotevarastoLuokka).method("lisaaYksi").returningVoid().takingNoParams();
        if (!lisaaYksi.exists()) {
            fail(tuotevarastoLuokka + " -luokastasi puuttuu tehtävän edellyttämä metodi public void lisaaYksi().");
        }
        lisaaMonta = Reflex.reflect(tuotevarastoLuokka).method("lisaaMonta").returningVoid().taking(int.class);
        if (!lisaaMonta.exists()) {
            fail(tuotevarastoLuokka + " -luokastasi puuttuu tehtävän edellyttämä metodi public void lisaaMonta(int maara).");
        }

        poistaYksi = Reflex.reflect(tuotevarastoLuokka).method("poistaYksi").returningVoid().takingNoParams();
        if (!poistaYksi.exists()) {
            fail(tuotevarastoLuokka + " -luokastasi puuttuu tehtävän edellyttämä metodi public void poistaYksi().");
        }
        poistaMonta = Reflex.reflect(tuotevarastoLuokka).method("poistaMonta").returningVoid().taking(int.class);
        if (!poistaMonta.exists()) {
            fail(tuotevarastoLuokka + " -luokastasi puuttuu tehtävän edellyttämä metodi public void poistaMonta(int maara).");
        }

        haeMaara = Reflex.reflect(tuotevarastoLuokka).method("haeMaara").returning(int.class).takingNoParams();
        if (!haeMaara.exists()) {
            fail(tuotevarastoLuokka + " -luokastasi puuttuu tehtävän edellyttämä metodi public int haeMaara().");
        }
    }

    @Test
    @Points("3.3.1")
    public void lisaysPoistoJaHakuTest() throws Throwable {
        init1();
        Object tuoteVarasto = Reflex.reflect(tuotevarastoLuokka).constructor().takingNoParams().invoke();
        testaaTuotevarasto(tuoteVarasto);

    }

    private void testaaPerusMuutoshistoria(Object muutoshistoria) throws Throwable {
        Random rnd = new Random();
        int[] expected = new int[5 + rnd.nextInt(5)];
        for (int i = 0; i < expected.length; i++) {
            int lisattava = rnd.nextInt(20) + 1;
            lisaaMerkinta.invokeOn(muutoshistoria, lisattava);
            expected[i] = lisattava;
        }
        String saatu = toString.invokeOn(muutoshistoria);
        if (!Arrays.toString(expected).equals(saatu)) {
            fail("Tarkista Muutoshistoria-luokan toString()-metodin toiminta. Tulostuksen piti olla \""
                    + Arrays.toString(expected) + "\" mutta toString()-metodisi palautti " + saatu + ".");
        }

        tyhjenna.invokeOn(muutoshistoria);
        saatu = toString.invokeOn(muutoshistoria);
        if (!saatu.equals("[]")) {
            fail("tyhjenna()-metodin kutsumisen jälkeen muutoshistoriasi toString()-metodin "
                    + "pitäisi palauttaa \"[]\" mutta palautus oli " + saatu + ".");
        }
    }

    private void testaaTilasto(Object tilasto) throws Throwable {
        Random rnd = new Random();
        int[] materiaali = new int[5 + rnd.nextInt(10)];
        int pieninExpected = Integer.MAX_VALUE;
        int suurinExpected = Integer.MIN_VALUE;
        int summa = 0;
        for (int i = 0; i < materiaali.length; i++) {
            int lisattava = 1 + rnd.nextInt(50);
            pieninExpected = pieninExpected > lisattava ? lisattava : pieninExpected;
            suurinExpected = suurinExpected < lisattava ? lisattava : suurinExpected;
            summa += lisattava;
            lisaaMerkinta.invokeOn(tilasto, lisattava);
            materiaali[i] = lisattava;
        }
        int saatuPienin = pienin.invokeOn(tilasto);
        if (saatuPienin != pieninExpected) {
            fail("Muutoshistorian " + Arrays.toString(materiaali) + " pienimmän alkion tulisi olla "
                    + pieninExpected + ", mutta pienin() -metodisi palautti " + saatuPienin + ".");
        }
        int saatuSuurin = suurin.invokeOn(tilasto);
        if (saatuSuurin != suurinExpected) {
            fail("Muutoshistorian " + Arrays.toString(materiaali) + " suurimman alkion tulisi olla "
                    + suurinExpected + ", mutta suurin() -metodisi palautti " + saatuSuurin + ".");
        }

        double saatuKeskiarvo = keskiarvo.invokeOn(tilasto);
        double oikeaKeskiarvo = (double) summa / materiaali.length;
        if (Math.abs(oikeaKeskiarvo - saatuKeskiarvo) > 0.001) {
            fail("Muutoshistorian " + Arrays.toString(materiaali) + " keskiarvon tulisi olla "
                    + oikeaKeskiarvo + ", mutta keskiarvo() -metodisi palautti "
                    + saatuKeskiarvo + ".");
        }

        double saatuVarianssi = varianssi.invokeOn(tilasto);
        double varianssiSumma = 0;
        for (int i = 0; i < materiaali.length; i++) {
            varianssiSumma += Math.pow(materiaali[i] - oikeaKeskiarvo, 2);
        }
        double oikeaVarianssi = varianssiSumma / materiaali.length;
        if (Math.abs(oikeaVarianssi - saatuVarianssi) > 0.001) {
            fail("Muutoshistorian " + Arrays.toString(materiaali) + " varianssin tulisi olla "
                    + oikeaVarianssi + ", mutta varianssi()-metodisi palautti "
                    + saatuVarianssi);
        }
    }

    private void testaaTuotevarasto(Object tuoteVarasto) throws Throwable {
        //Satunnaistestit!
        Random rnd = new Random();
        int lisays = 5 + rnd.nextInt(20);
        int poisto = 1 + rnd.nextInt(5);
        lisaaMonta.invokeOn(tuoteVarasto, lisays);
        int saatu = haeMaara.invokeOn(tuoteVarasto);
        if (saatu != lisays) {
            fail("Kun suoritettiin metodi lisaaMonta(" + lisays + "), saldon tulisi olla "
                    + lisays + " mutta haeMaara()-metodisi palautti tuloksen " + saatu + ".");
        }
        poistaMonta.invokeOn(tuoteVarasto, poisto);
        saatu = haeMaara.invokeOn(tuoteVarasto);
        if (saatu != (lisays - poisto)) {
            fail("Kun suoritettiin ensin metodi lisaaMonta(" + lisays + "), sitten metodi "
                    + "poistaMonta(" + poisto + "), saldon tulisi olla " + (lisays - poisto)
                    + " mutta haeMaara()-metodisi palautti tuloksen " + saatu + ".");
        }

        lisaaYksi.invokeOn(tuoteVarasto);
        saatu = haeMaara.invokeOn(tuoteVarasto);
        if (lisays - poisto + 1 != saatu) {
            fail("Kun suoritettiin ensin metodi lisaaMonta(" + lisays + "), sitten metodi "
                    + "poistaMonta(" + poisto + "), sitten metodi lisaaYksi(), tulisi saldon "
                    + "olla " + (lisays + poisto + 1) + " mutta haeMaara()-metodisi palautti "
                    + "tuloksen " + saatu + ".");
        }

        lisaaYksi.invokeOn(tuoteVarasto);
        lisaaYksi.invokeOn(tuoteVarasto);
        poistaYksi.invokeOn(tuoteVarasto);
        saatu = haeMaara.invokeOn(tuoteVarasto);
        if (lisays - poisto + 2 != saatu) {
            fail("Kun suoritettiin ensin metodi lisaaMonta(" + lisays + "), sitten metodi "
                    + "poistaMonta(" + poisto + "), sitten metodi lisaaYksi() kolme kertaa, "
                    + "ja lopuksi poistaYksi() kerran, tulisi saldon "
                    + "olla " + (lisays + poisto + 2) + " mutta haeMaara()-metodisi palautti "
                    + "tuloksen " + saatu + ".");
        }
    }
    private String muutoshistoriaLuokka = "Muutoshistoria";
    private MethodRef1<Object, Void, Integer> lisaaMerkinta;
    private MethodRef0<Object, Void> tyhjenna;
    private MethodRef0<Object, String> toString;

    private void init2() {
        lisaaMerkinta = Reflex.reflect(muutoshistoriaLuokka).method("lisaaMerkinta").returningVoid().taking(int.class);
        if (!lisaaMerkinta.exists()) {
            fail("Muutoshistoria-luokastasi puuttuu tehtävän edellyttämä metodi public void lisaaMerkinta(int maara).");
        }

        tyhjenna = Reflex.reflect(muutoshistoriaLuokka).method("tyhjenna").returningVoid().takingNoParams();
        if (!tyhjenna.exists()) {
            fail("Muutoshistoria-luokastasi puuttuu tehtävän edellyttämä metodi public void tyhjenna().");
        }
        toString = Reflex.reflect(muutoshistoriaLuokka).method("toString").returning(String.class).takingNoParams();
        if (!toString.exists()) {
            fail("Muutoshistoria-luokastasi puuttuu tehtävän edellyttämä metodi public String toString().");
        }
    }

    @Test
    @Points("3.3.2")
    public void muutoshistoriaTest() throws Throwable {
        init2();
        Object muutoshistoria = Reflex.reflect(muutoshistoriaLuokka).constructor().takingNoParams().invoke();
        testaaPerusMuutoshistoria(muutoshistoria);

    }
    private MethodRef0<Object, Integer> pienin;
    private MethodRef0<Object, Integer> suurin;
    private MethodRef0<Object, Double> keskiarvo;
    private MethodRef0<Object, Double> varianssi;

    private void init3() {
        init2();
        pienin = Reflex.reflect(muutoshistoriaLuokka).method("pienin").returning(int.class).takingNoParams();
        if (!pienin.exists()) {
            fail("Muutoshistoria-luokastasi puuttuu metodi public int pienin().");
        }

        suurin = Reflex.reflect(muutoshistoriaLuokka).method("suurin").returning(int.class).takingNoParams();
        if (!suurin.exists()) {
            fail("Muutoshistoria-luokastasi puuttuu metodi public int suurin().");
        }

        keskiarvo = Reflex.reflect(muutoshistoriaLuokka).method("keskiarvo").returning(double.class).takingNoParams();
        if (!keskiarvo.exists()) {
            fail("Muutoshistoria-luokastasi puuttuu metodi public double keskiarvo().");
        }

        varianssi = Reflex.reflect(muutoshistoriaLuokka).method("varianssi").returning(double.class).takingNoParams();
        if (!varianssi.exists()) {
            fail("Muutoshistoria-luokastasi puuttuu metodi public double varianssi().");
        }
    }

    @Test
    @Points("3.3.3")
    public void tilastoTest() throws Throwable {
        init3();
        Object tilasto = Reflex.reflect(muutoshistoriaLuokka).constructor().takingNoParams().invoke();
        testaaTilasto(tilasto);

    }
    private String muistavaTuotevarastoLuokka = "MuistavaTuotevarasto";
    private MethodRef0<Object, Object> haeHistoria;

    @Test
    @Points("3.3.4")
    public void muistavaTuotevarastoTest() throws Throwable {
        init1();
        init3();
        Object varasto = Reflex.reflect(muistavaTuotevarastoLuokka).constructor().takingNoParams().invoke();
        Class expected = Reflex.reflect(tuotevarastoLuokka).getReferencedClass();
      
        if (!expected.isAssignableFrom(varasto.getClass())) {
            fail("MuistavaTuotevarasto-luokan täytyy periä (extend) Tuotevarasto-luokka.");
        }
        testaaTuotevarasto(varasto);
        Class mh = Reflex.reflect(muutoshistoriaLuokka).getReferencedClass();
        haeHistoria = Reflex.reflect(muistavaTuotevarastoLuokka).method("haeHistoria").returning(mh).takingNoParams();
        Object muutoshistoria = haeHistoria.invokeOn(varasto);
        tyhjenna.invokeOn(muutoshistoria);
        testaaPerusMuutoshistoria(muutoshistoria);
        testaaTilasto(muutoshistoria);
		
		testaaMuutoshistoriaaVarastonKautta(varasto, muutoshistoria);
    }
	
	private void testaaMuutoshistoriaaVarastonKautta(Object varasto, Object muutoshistoria) throws Throwable {
		varasto = Reflex.reflect(muistavaTuotevarastoLuokka).constructor().takingNoParams().invoke();
        Class mh = Reflex.reflect(muutoshistoriaLuokka).getReferencedClass();
        haeHistoria = Reflex.reflect(muistavaTuotevarastoLuokka).method("haeHistoria").returning(mh).takingNoParams();
        muutoshistoria = haeHistoria.invokeOn(varasto);
		lisaaYksi.invokeOn(varasto);
		testaaVarastonMuutoshistoria(muutoshistoria, "[1]", "MuistavaanTuotevarastoon lisättiin yksi (lisaaYksi),");
		
		varasto = Reflex.reflect(muistavaTuotevarastoLuokka).constructor().takingNoParams().invoke();
        mh = Reflex.reflect(muutoshistoriaLuokka).getReferencedClass();
        haeHistoria = Reflex.reflect(muistavaTuotevarastoLuokka).method("haeHistoria").returning(mh).takingNoParams();
        muutoshistoria = haeHistoria.invokeOn(varasto);
		lisaaMonta.invokeOn(varasto, 4);
		testaaVarastonMuutoshistoria(muutoshistoria, "[4]", "MuistavaanTuotevarastoon lisättiin neljä (lisaaMonta),");
		
		varasto = Reflex.reflect(muistavaTuotevarastoLuokka).constructor().takingNoParams().invoke();
        mh = Reflex.reflect(muutoshistoriaLuokka).getReferencedClass();
        haeHistoria = Reflex.reflect(muistavaTuotevarastoLuokka).method("haeHistoria").returning(mh).takingNoParams();
        muutoshistoria = haeHistoria.invokeOn(varasto);
		lisaaMonta.invokeOn(varasto, 3);
		poistaYksi.invokeOn(varasto);
		testaaVarastonMuutoshistoria(muutoshistoria, "[3, 2]", "MuistavaanTuotevarastoon lisättiin kolme ja sitten poistettiin yksi (poistaYksi),");
		
		varasto = Reflex.reflect(muistavaTuotevarastoLuokka).constructor().takingNoParams().invoke();
        mh = Reflex.reflect(muutoshistoriaLuokka).getReferencedClass();
        haeHistoria = Reflex.reflect(muistavaTuotevarastoLuokka).method("haeHistoria").returning(mh).takingNoParams();
        muutoshistoria = haeHistoria.invokeOn(varasto);
		lisaaMonta.invokeOn(varasto, 5);
		poistaMonta.invokeOn(varasto, 3);
		testaaVarastonMuutoshistoria(muutoshistoria, "[5, 2]", "MuistavaanTuotevarastoon lisättiin viisi ja sitten poistettiin kolme (poistaMonta),");
	}
	
	private void testaaVarastonMuutoshistoria(Object muutoshistoria, String oletustuloste, String tapaus) throws Throwable {
        String saatu = toString.invokeOn(muutoshistoria);
        if (!oletustuloste.equals(saatu)) {
            fail(tapaus + " muutoshistorian palauttama toString oli kuitenkin \"" + saatu + "\" vaikka sen oletettiin olevan " + oletustuloste);
        }

        tyhjenna.invokeOn(muutoshistoria);
        saatu = toString.invokeOn(muutoshistoria);
        if (!saatu.equals("[]")) {
            fail("tyhjenna()-metodin kutsumisen jälkeen muutoshistoriasi toString()-metodin "
                    + "pitäisi palauttaa \"[]\" mutta palautus oli " + saatu + ".");
        }
    }
	
    MethodRef0<Object, Void> tulostaTilasto;

    @Test
    @Points("3.3.5")
    public void graafinenTilastoTest() throws Throwable {
        init3();
        tulostaTilasto = Reflex.reflect(muutoshistoriaLuokka).method("tulostaTilasto").returningVoid().takingNoParams();
        Object tilasto = Reflex.reflect(muutoshistoriaLuokka).constructor().takingNoParams().invoke();
        Random rnd = new Random();
        int[] saldot = new int[7];
        saldot[0] = 1 + rnd.nextInt(5);
        saldot[1] = 3 + rnd.nextInt(4);
        saldot[2] = 1 + rnd.nextInt(6);
        saldot[3] = 4 + rnd.nextInt(2);
        saldot[4] = 4 + rnd.nextInt(5);
        saldot[5] = 1 + rnd.nextInt(3);
        saldot[6] = 2 + rnd.nextInt(8);

        for (int i = 0; i < saldot.length; i++) {
            lisaaMerkinta.invokeOn(tilasto, saldot[i]);
        }

        MockInOut mio = new MockInOut("");
        tulostaTilasto.invokeOn(tilasto);
        String[] output = mio.getOutput().split("\n");
        mio.close();
        int[] saadut = {0, 0, 0, 0, 0, 0, 0};
        for (int rivi = 0; rivi < output.length; rivi++) {
            int sarake = 0;
            while (sarake * 2 < output[rivi].length() && sarake < saadut.length) {
                if (output[rivi].charAt(sarake * 2) == '*') {
                    saadut[sarake]++;
                }
                sarake++;
            }
        }
        if(!Arrays.equals(saadut, saldot)) {
            fail("Tarkista että graafinen tilastontulostuksesi menee oikein: " + Arrays.toString(saadut));
        }
    }
}
