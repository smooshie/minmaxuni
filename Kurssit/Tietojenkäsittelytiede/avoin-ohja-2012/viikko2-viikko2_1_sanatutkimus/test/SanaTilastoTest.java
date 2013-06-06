
import fi.helsinki.cs.tmc.edutestutils.MockInOut;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SanaTilastoTest {
    //<editor-fold defaultstate="collapsed" desc="fields">

    private int[] sanaMaarat = {0, 25, 191, 1033, 3122, 4250, 6107, 8412,
        11061, 12090, 10494, 8804, 7533, 6192, 4411, 2842, 1971,
        1214, 794, 452, 296, 140, 69, 47, 20, 11, 4, 4, 1, 1};
	private int[] virheSanaMaarat = {0, 21, 169, 881, 2720, 3716, 5329, 7332,
        9913, 11461, 10681, 9356, 7983, 6709, 4999, 3508, 2499,
        1641, 1042, 665, 421, 215, 152, 94, 38, 22, 9, 5, 4, 3};
    private int[] kirjainMaarat = {112291, 1496, 213, 7315, 66488, 1634,
        2613, 21396, 114398, 12803, 64692, 57584, 29316, 63515, 53396, 29840,
        16, 38919, 73470, 85166, 65604, 23864, 65, 16, 21348, 72, 1, 31475, 7250};
    private String[] palindromit = {"ajaja", "akka", "ala", "alla", "autioitua", "ele",
        "enne", "hah", "heh", "huh", "hyh", "häh", "imaami", "isi", "niin", "oho", "olo",
        "opo", "otto", "piip", "pop", "sadas", "sammas", "sees", "siis", "sus", "suuruus",
        "sylys", "sytytys", "syys", "syöppöys", "tuut", "tyyt", "tööt", "utu", "yty", "älä",
        "ämmä", "ässä"};
    private String[] ristikko1 = {"saalistus", "salaisuus", "sateisuus", "satoisuus",
        "sekaisuus", "sijaisuus", "siloisuus", "sisäistys", "sopuisuus", "sotaisuus",
        "suloisuus", "suoristus",
        "syntisyys", "säröisyys", "sävyisyys"};
    private String[] ristikko2 = {"aliedustus", "alijohtaja", "alijäähtyä",
        "alikäytävä", "alimmainen", "aliravittu", "alisteinen", "alistuvuus",
        "alitajunta", "alituiseen", "aliupseeri", "alivalotus"};
    private String[] anagrammit1 = {"antoisa", "asiaton", "isontaa", "tosiaan"};
    private String[] anagrammit2 = {"avantouinti", "nuottiavain"};
    private String[] anagrammit3 = {"altis", "lasti", "lista", "litsa", "silat", "silta", "tilsa"};
    private String[] anagrammit4 = {"alistuva", "luistava", "ulvaista", "valistua", "vilustaa"};
    private Object sanatutkimus;
    private Class luokka;
    private Class listaLuokka;
    private Method sanojenMaara;
    private Method onkoSanaa;
    private Method laskeSanat;
    private Method pituusTilasto;
    private Method laskeKirjaimet;
    private Method kirjainTilasto;
    private Method haePituudella;
    private Method haeOsalla;
    private Method haePalindromit;
    private Method haeRistikkoon;
    private Method haeAnagrammit;
    //</editor-fold>

    @Before
    public void setUp() {
        listaLuokka = new ArrayList<String>().getClass();
        luokka = ReflectionUtils.findClass("Sanatutkimus");
        Constructor kons = ReflectionUtils.requireConstructor(luokka, String.class);
        try {
            sanatutkimus = ReflectionUtils.invokeConstructor(kons, "sanalista.txt");
        } catch (Throwable ex) {
            fail("Konstruktorin public Sanatutkimus(\"sanalista.txt\") kutsu epäonnistui");
        }
    }

    @Points("2.1.1")
    @Test
    public void SanojenMaara() {
        reku1();
        ajaMetodi(91591, sanojenMaara);
    }

    @Points("2.1.1")
    @Test
    public void onkoSanaa() throws Throwable {
        reku2();
        ajaMetodi(false, onkoSanaa, "asd");
        ajaMetodi(true, onkoSanaa, "porkkana");
        ajaMetodi(false, onkoSanaa, "porkana");
        ajaMetodi(true, onkoSanaa, "kissa");
        ajaMetodi(false, onkoSanaa, "keijo");
    }

    @Points("2.1.2")
    @Test
    public void laskeSanat() {
        reku3();
        for (int i = 0; i < sanaMaarat.length; i++) {
            //ajaMetodi(sanaMaarat[i], laskeSanat, i + 1);
			ajaMetodiUtfHuomautuksenKanssa(virheSanaMaarat[i], sanaMaarat[i], laskeSanat, i + 1);
        }
    }

    @Points("2.1.3")
    @Test
    public void sanaPituusTilasto() {
        reku4();
        MockInOut io = new MockInOut("");
        ajaMetodi(pituusTilasto);
        String out = io.getOutput();
        String[] rivit = out.split("\n");
        assertEquals("Rivejä ei ollut oikea määrä", 30, rivit.length);
        for (int i = 0; i < sanaMaarat.length; i++) {
            assertEquals((i + 1) + ". rivi oli vääränlainen", i + 1 + " " + sanaMaarat[i], rivit[i].trim());
        }
    }

    @Points("2.1.4")
    @Test
    public void laskeKirjaimet() {
        reku5();
        String kirjaimet = "abcdefghijklmnopqrstuvwxyzåäö";
        for (int i = 0; i < kirjainMaarat.length; i++) {
            ajaMetodi(kirjainMaarat[i], laskeKirjaimet, kirjaimet.charAt(i));
        }
    }

    @Points("2.1.5")
    @Test
    public void kirjainTilasto() {
        reku6();
        String kirjaimet = "abcdefghijklmnopqrstuvwxyzåäö";
        MockInOut io = new MockInOut("");
        ajaMetodi(kirjainTilasto);
        String[] rivit = io.getOutput().split("\n");
        assertEquals("Rivejä oli väärä määrä kirjainTilasto()a kutsuttaessa", 29, rivit.length);
        for (int i = 0; i < rivit.length; i++) {
            assertEquals(i + 1 + ". rivi ei ollut oikein ", kirjaimet.charAt(i) + " " + kirjainMaarat[i], rivit[i].trim());
        }
    }

    @Points("2.1.6")
    @Test
    public void haePituudella() {
        reku7();
        for (int i = 0; i < sanaMaarat.length; i++) {
            oletaKoko(sanaMaarat[i], haePituudella, i + 1);
        }
        oletaKuulumaan("emulsio", haePituudella, 7);
        oletaKuulumaan("tietojenkäsittelyjärjestelmä", haePituudella, 28);
        oletaKuulumaan("epäys", haePituudella, 5);
    }

    @Points("2.1.7")
    @Test
    public void haeOsalla() {
        reku8();
        oletaKoko(13, haeOsalla, "koodi");
        oletaKoko(390, haeOsalla, "ovi");
        oletaKoko(61073, haeOsalla, "a");
        oletaKoko(1819, haeOsalla, "all");
        oletaKuulumaan("halli", haeOsalla, "all");
    }

    @Points("2.1.8")
    @Test
    public void haePalindromit() {
        reku9();
        oletaKoko(39, haePalindromit);
        for (String string : palindromit) {
            oletaKuulumaan(string, haePalindromit);
        }
    }

    @Points("2.1.9")
    @Test
    public void ristikkoApuri() {
        reku10();
        oletaKoko(15, haeRistikkoon, "s???is??s");
        for (String string : ristikko1) {
            oletaKuulumaan(string, haeRistikkoon, "s???is??s");
        }
        oletaKoko(ristikko2.length, haeRistikkoon, "ali???????");
        for (String string : ristikko2) {
            oletaKuulumaan(string, haeRistikkoon, "ali???????");
        }
    }

    @Points("2.1.10")
    @Test
    public void anagrammit() {
        reku11();
        oletaKoko(anagrammit1.length, haeAnagrammit, "antoisa");
        for (String string : anagrammit1) {
            oletaKuulumaan(string, haeAnagrammit, "antoisa");
        }
        oletaKoko(anagrammit2.length, haeAnagrammit, "nuottiavain");
        for (String string : anagrammit2) {
            oletaKuulumaan(string, haeAnagrammit, "nuottiavain");
        }
        oletaKoko(anagrammit3.length, haeAnagrammit, "altis");
        for (String string : anagrammit3) {
            oletaKuulumaan(string, haeAnagrammit, "altis");
        }
        oletaKoko(anagrammit4.length, haeAnagrammit, "alistuva");
        for (String string : anagrammit4) {
            oletaKuulumaan(string, haeAnagrammit, "alistuva");
        }
    }
    //<editor-fold defaultstate="collapsed" desc="apumetodit">

    private void ajaMetodi(Object oletettu, Method metodi, Object... parametrit) {
        assertEquals("Metodista " + metodi.getName() + stringiksi(parametrit) + " saatiin väärä tulos", oletettu, ajaMetodi(metodi, parametrit));
    }
	
	private void ajaMetodiUtfHuomautuksenKanssa(Object oletettuVirhe, Object oletettu, Method metodi, Object... parametrit) {
		String lisahuomautus = "";
		if (ajaMetodi(metodi, parametrit).equals(oletettuVirhe) && !oletettuVirhe.equals(new Integer(0))) {
			lisahuomautus = "\nTuloksen todennäköisesti korjaa jos määrität Scannerin lukemaan UTF-8 tiedostoa.\n";
		}
        assertEquals("Metodista " + metodi.getName() + stringiksi(parametrit) + " saatiin väärä tulos" + lisahuomautus, oletettu, ajaMetodi(metodi, parametrit));
    }

    private Object ajaMetodi(Method metodi, Object... parametrit) {
        try {
            return ReflectionUtils.invokeMethod(metodi.getReturnType(), metodi, sanatutkimus, parametrit);
        } catch (Throwable ex) {
            fail("Metodia " + metodi.getName() + stringiksi(parametrit) + " ei onnistuttu kutsumaan.");
            return null;
        }
    }

    private void oletaKoko(Object oletettu, Method metodi, Object... params) {
        assertEquals("Metodista " + metodi.getName() + stringiksi(params) + " saatiin väärä määrä vastauksia", oletettu, ((ArrayList) ajaMetodi(metodi, params)).size());
    }

    private void oletaKuulumaan(Object oletettu, Method metodi, Object... params) {
        assertTrue("Metodin " + metodi.getName() + stringiksi(params) + " palautukseen tulisi kuulua arvo" + oletettu, ((ArrayList) ajaMetodi(metodi, params)).contains(oletettu));
    }

    private void reku1() {
        sanojenMaara = vaadi(int.class, "sanojenMaara");
    }

    private void reku2() {
        reku1();
        onkoSanaa = vaadi(boolean.class, "onkoSanaa", String.class);
    }

    private void reku3() {
        reku2();
        laskeSanat = vaadi(int.class, "laskeSanat", int.class);
    }

    private void reku4() {
        reku3();
        pituusTilasto = vaadi(void.class, "pituustilasto");
    }

    private void reku5() {
        reku4();
        laskeKirjaimet = vaadi(int.class, "laskeKirjaimet", char.class);
    }

    private void reku6() {
        reku5();
        kirjainTilasto = vaadi(void.class, "kirjaintilasto");
    }

    private void reku7() {
        reku6();

        haePituudella = vaadi(listaLuokka, "haePituudella", int.class);
    }

    private void reku8() {
        reku7();
        haeOsalla = vaadi(listaLuokka, "haeOsalla", String.class);
    }

    private void reku9() {
        reku8();
        haePalindromit = vaadi(listaLuokka, "haePalindromit");
    }

    private void reku10() {
        reku9();
        haeRistikkoon = vaadi(listaLuokka, "haeRistikkoon", String.class);
    }

    private void reku11() {
        reku10();
        haeAnagrammit = vaadi(listaLuokka, "haeAnagrammit", String.class);
    }

    private Method vaadi(Class ret, String nimi, Class... params) {
        return ReflectionUtils.requireMethod(false, luokka, ret, nimi, params);
    }

    private String stringiksi(Object[] param) {
        if (param.length == 0) {
            return "()";
        }
        StringBuilder rak = new StringBuilder();
        rak.append("(");
        for (Object object : param) {
            rak.append(object).append(",");
        }
        rak.deleteCharAt(rak.length() - 1).append(")");
        return rak.toString();
    }

    private void vaadiAika(long aika, Method metodi, long oletus) {
        long kesto = System.currentTimeMillis() - aika;
        assertTrue("Metodi " + metodi.getName() + " toimii nyt liian nopeasti, aika tulee olla yli " + oletus + "ms, oli: "
                + (kesto) + "ms", kesto > oletus);
    }
    //</editor-fold>
}
