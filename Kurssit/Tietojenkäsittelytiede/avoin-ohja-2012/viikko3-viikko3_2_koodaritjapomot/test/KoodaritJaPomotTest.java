/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import fi.helsinki.cs.tmc.edutestutils.Reflex.ClassRef;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodAndReturnType;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef0;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef1;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef2;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef3;
import java.lang.reflect.Method;
import java.util.Collections;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jarmo
 */
public class KoodaritJaPomotTest {

    private final String RAJAPINNAN_NIMI = "Tyontekija";

    @Before
    public void setUp() {
    }

    @Test
    @Points("3.2.1")
    public void testataanRajaPinnanOlemassaOloTest() {
        Class tyontekijaClass = null;
        try {
            tyontekijaClass = ReflectionUtils.findClass(RAJAPINNAN_NIMI);
        } catch (Throwable t) {
            fail("Olethan luonut rajapinnan \"Tyontekija\"");
        }
//        ClassRef<Object> c = Reflex.reflect(RAJAPINNAN_NIMI);
        if (!tyontekijaClass.isInterface()) {
            fail("Olethan luonut rajapinnan \"Tyontekija\"");
        }

        Method[] metodit = tyontekijaClass.getDeclaredMethods();
        StringBuilder stb = new StringBuilder();
        for (Method method : metodit) {
            stb.append(method.toString());
        }
        String kaikkiMetodit = stb.toString();
        if (!kaikkiMetodit.contains("java.lang.String Tyontekija.haeNimi()")) {
            fail("Tyontekija rajapinnan tulisi sisältää metodi \"public String haeNimi();\"");
        }
        if (!kaikkiMetodit.contains("int Tyontekija.haePalkka()")) {
            fail("Tyontekija rajapinnan tulisi sisältää metodi \"public int haePalkka();\"");
        }
        if (!kaikkiMetodit.contains("int Tyontekija.laskeAlaiset()")) {
            fail("Tyontekija rajapinnan tulisi sisältää metodi \"public int laskeAlaiset();\"");
        }
        if (!kaikkiMetodit.contains("void Tyontekija.lisaaKieli(java.lang.String)")) {
            fail("Tyontekija rajapinnan tulisi sisältää metodi \"public void lisaaKieli(String kieli);\"");
        }
        if (!kaikkiMetodit.contains("boolean Tyontekija.onkoTaitoa(java.lang.String)")) {
            fail("Tyontekija rajapinnan tulisi sisältää metodi \"public boolean onkoTaitoa(String kieli);\"");
        }

    }
    private final String KOODARILUOKKA = "Koodari";

    @Test
    @Points("3.2.2")
    public void KoodariTest() throws Throwable {
        ClassRef<Object> koodariClass = Reflex.reflect(KOODARILUOKKA);
        if (!Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).exists()) {
            fail("Olethan luonut luokan \"Koodari\" ja sille kaksiparametrisen konstruktorin \"public Koodari(String nimi, int palkka)\"");
        }
        Object koodari = Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).invoke("Kalle", 50000);
        MethodRef0<Object, String> haeNimi = Reflex.reflect(KOODARILUOKKA).method("haeNimi").returning(String.class).takingNoParams();
        if (!haeNimi.exists()) {
            fail("Olethan toteuttanut metodin \"public String haeNimi()\" luokkaan \"Koodari\"");
        }
        String nimi = haeNimi.invokeOn(koodari);
        String syote = "Koodari kalle = new Koodari(\"Kalle\", 50000);\n"
                + "kalle.haeNimi();";
        assertEquals("Seuraavan tulisi palauttaa koodarin nimeksi \"Kalle\" : " + syote, "Kalle", nimi);
        syote = "Koodari kalle = new Koodari(\"Kalle\", 50000);\n"
                + "kalle.haePalkka();";
        MethodRef0<Object, Integer> haePalkka = Reflex.reflect(KOODARILUOKKA).method("haePalkka").returning(int.class).takingNoParams();
        if (!haePalkka.exists()) {
            fail("Olethan toteuttanut metodin \"public int haePalkka()\" luokkaan \"Koodari\"");
        }
        int palkka = haePalkka.invokeOn(koodari);
        assertEquals("Seuraavan tulisi palauttaa koodarin palkaksi 50000" + syote, 50000, palkka);

        MethodRef0<Object, Integer> laskeAlaiset = Reflex.reflect(KOODARILUOKKA).method("laskeAlaiset").returning(int.class).takingNoParams();
        if (!laskeAlaiset.exists()) {
            fail("Olethan toteuttanut metodin \"public int laskeAlaiset()\" luokkaan \"Koodari\"");
        }

        syote = "Koodari kalle = new Koodari(\"Kalle\", 50000);\n"
                + "kalle.laskeAlaiset();";
        int alaisia = laskeAlaiset.invokeOn(koodari);
        assertEquals("Seuraavan tulisi palauttaa koodarin alaisiksi 0" + syote, 0, alaisia);

        MethodRef1<Object, Void, String> lisaaKieli = Reflex.reflect(KOODARILUOKKA).method("lisaaKieli").returningVoid().taking(String.class);
        if (!lisaaKieli.exists()) {
            fail("Olethan toteuttanut metodin \"public void lisaaKieli(String kieli)\" luokkaan \"Koodari\"");
        }

        syote = "Koodari kalle = new Koodari(\"Kalle\", 50000);\n"
                + "kalle.lisaaKieli(\"java\");";


        MethodRef1<Object, Boolean, String> onkoTaitoa = Reflex.reflect(KOODARILUOKKA).method("onkoTaitoa").returning(boolean.class).taking(String.class);
        if (!onkoTaitoa.exists()) {
            fail("Olethan toteuttanut metodin \"public boolean onkoTaitoa(String kieli)\" luokkaan \"Koodari\"");
        }
        syote += "kalle.onkoTaitoa(\"java\");";
        lisaaKieli.invokeOn(koodari, "java");
        boolean taitoa = onkoTaitoa.invokeOn(koodari, "java");
        assertTrue("Seuraavan tulisi palauttaa true: \n" + syote, taitoa);
        syote += "kalle.onkoTaitoa(\"ruby\");";
        boolean osaakoRubya = onkoTaitoa.invokeOn(koodari, "ruby");
        assertFalse("Seuraavan tulisi palauttaa false: \n" + syote, osaakoRubya);
//        koodariClass.
    }

    @Test
    @Points("3.2.3")
    public void rajoitetuPomoTest() throws Throwable {
        Class tyontekija = ReflectionUtils.findClass("Tyontekija");
        assertTrue("Pomon tulisi toteuttaa rajapinta Tyontekija", tyontekija.isAssignableFrom(Reflex.reflect("Pomo").getReferencedClass()));
        MethodRef1 lisaaAlainen = Reflex.reflect("Pomo").method("lisaaAlainen").returningVoid().taking(tyontekija);
        if (!lisaaAlainen.exists()) {
            fail("Olethan toteuttanut metodin \"public void lisaaAlainen(Tyontekija alainen)\" luokkaan Pomo ");
        }

        if (!Reflex.reflect("Pomo").constructor().taking(String.class, int.class).exists()) {
            fail("Olethan tehnyt luokalle Pomo kaksi parametrisen konstruktorin \"public Pomo(String nimi, int palkka)\"");
        }
        MethodRef0<Object, String> pomoGetNimi = Reflex.reflect("Pomo").method("haeNimi").returning(String.class).takingNoParams();
        if (!pomoGetNimi.exists()){
            Assert.fail("Olethan toteuttanut metodin public String haeNimi() luokkaan Pomo");
        }
        
        MethodRef0<Object, Integer> pomoGetPalkka = Reflex.reflect("Pomo").method("haePalkka").returning(int.class).takingNoParams();
        if (!pomoGetNimi.exists()){
            Assert.fail("Olethan toteuttanut metodin public int haePalkka() luokkaan Pomo");
        }
        
        MethodRef1<Object, Void, String> pomolisaaKieli = Reflex.reflect("Pomo").method("lisaaKieli").returningVoid().taking(String.class);
        if (!pomoGetNimi.exists()){
            Assert.fail("Olethan toteuttanut metodin public void lisaaKieli(String kieli) luokkaan Pomo");
        }
        
        MethodRef1<Object, Boolean, String> pomoOnkoTaitoa = Reflex.reflect("Pomo").method("onkoTaitoa").returning(boolean.class).taking(String.class);
        if (!pomoGetNimi.exists()){
            Assert.fail("Olethan toteuttanut metodin public boolean onkoTaitoa(String kieli) luokkaan Pomo");
        }
        Object mattiPomo = Reflex.reflect("Pomo").constructor().taking(String.class, int.class).invoke("Matti", 20);
        String nimi = pomoGetNimi.invokeOn(mattiPomo);
        String syote = "Pomo matti = new Pomo(\"Matti\", 20);\n"
                + "matti.haeNimi();\n"
                + "tulisi palauttaa Matti";
        Assert.assertEquals(""+syote, "Matti", nimi);
        
        int palkka = pomoGetPalkka.invokeOn(mattiPomo);
         syote = "Pomo matti = new Pomo(\"Matti\", 20);\n"
                + "matti.haePalkka();\n"
                + "tulisi palattaa 20";
        Assert.assertEquals(""+syote, 20, palkka);
        
        pomolisaaKieli.invokeOn(mattiPomo, "Java");
        boolean tulos = pomoOnkoTaitoa.invokeOn(mattiPomo, "Java");
        syote = "Pomo matti = new Pomo(\"Matti\", 20);\n"
                + "matti.lisaaKieli(\"Java\");\n"
                + "matti.onkoTaitoa(\"Java\");\n"
                + "tulisi palattaa true";
        Assert.assertTrue(""+syote, tulos);
        
        tulos = pomoOnkoTaitoa.invokeOn(mattiPomo, "Kiina");
        syote = "Pomo matti = new Pomo(\"Matti\", 20);\n"
                + "matti.lisaaKieli(\"Java\");\n"
                + "matti.onkoTaitoa(\"Kiina\");\n"
                + "tulisi palauttaa false";
        Assert.assertFalse(""+syote, tulos);
    }

    @Test
    @Points("3.2.4")
    public void pomonJaAlaistenMaaranLaskuTest() throws Throwable {
        Object kalle = Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).invoke("Kalle", 50000);
        Object pekka = Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).invoke("Pekka", 50000);
        Object antti = Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).invoke("Antti", 50000);
        Object jarmo = Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).invoke("Jarmo", 50000);
//        MethodRef0<Object, String> KoodarihaeNimi = Reflex.reflect(KOODARILUOKKA).method("haeNimi").returning(String.class).takingNoParams();
//        MethodRef0<Object, Integer> KoodarihaePalkka = Reflex.reflect(KOODARILUOKKA).method("haePalkka").returning(int.class).takingNoParams();
//        MethodRef0<Object, Integer> KoodarilaskeAlaiset = Reflex.reflect(KOODARILUOKKA).method("laskeAlaiset").returning(int.class).takingNoParams();
//        MethodRef1<Object, Void, String> KoodarilisaaKieli = Reflex.reflect(KOODARILUOKKA).method("lisaaKieli").returningVoid().taking(String.class);
//        MethodRef1<Object, Boolean, String> KoodarionkoTaitoa = Reflex.reflect(KOODARILUOKKA).method("onkoTaitoa").returning(boolean.class).taking(String.class);
        Object eskoPomo = Reflex.reflect("Pomo").constructor().taking(String.class, int.class).invoke("Esko", 100);
        Object mattiPomo = Reflex.reflect("Pomo").constructor().taking(String.class, int.class).invoke("Matti", 500);
        Object artoPomo = Reflex.reflect("Pomo").constructor().taking(String.class, int.class).invoke("Arto", 5000);
        Class pomoClass = ReflectionUtils.findClass("Pomo");
        Class koodariClass = ReflectionUtils.findClass("Koodari");
        Class tyontekijaClass = ReflectionUtils.findClass("Tyontekija");
        Method pomoLisaaAlainen = ReflectionUtils.requireMethod(pomoClass, "lisaaAlainen", tyontekijaClass);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, eskoPomo, mattiPomo);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, mattiPomo, artoPomo);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, mattiPomo, antti);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, artoPomo, kalle);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, artoPomo, pekka);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, artoPomo, jarmo);


        Method PomonAlaiset = ReflectionUtils.requireMethod(pomoClass, int.class, "laskeAlaiset");
        Method koodarinAlaiset = ReflectionUtils.requireMethod(koodariClass, int.class, "laskeAlaiset");
        int pekanAlaiset = ReflectionUtils.invokeMethod(int.class, koodarinAlaiset, pekka);
        int eskonAlaiset = ReflectionUtils.invokeMethod(int.class, PomonAlaiset, eskoPomo);
        int artonAlaiset = ReflectionUtils.invokeMethod(int.class, PomonAlaiset, artoPomo);

        String syote = "Koodari kalle = new Koodari(\"Kalle\", 50000);\n"
                + "Koodari pekka = new Koodari(\"Pekka\", 50000);\n"
                + "Koodari antti = new Koodari(\"Antti\", 50000);\n"
                + "Koodari jarmo = new Koodari(\"Jarmo\", 50000);\n"
                + "Pomo esko = new Pomo(\"Esko\", 10);\n"
                + "Pomo matti = new Pomo(\"Matti\", 100);\n"
                + "Pomo arto = new Pomo(\"Arto\", 5000);\n"
                + "esko.lisaaAlainen(matti);\n"
                + "matti.lisaaAlainen(arto);\n"
                + "matti.lisaaAlainen(antti);\n"
                + "arto.lisaaAlainen(kalle);\n"
                + "arto.lisaaAlainen(pekka);\n"
                + "arto.lisaaAlainen(jarmo);\n";
        assertEquals("Seuraavalla syötteellä Pekalla tulisi olla 0 alaista, sillä hän on Koodari.\n" + syote, 0, pekanAlaiset);
        assertEquals("Seuraavalla syötteellä Eskolla tulisi olla 6 alaista, kaikki muut ovat hänen alaisiaan.\n" + syote, 6, eskonAlaiset);
        assertEquals("Seuraavalla syötteellä Artolla tulisi olla 4 alaista, kaikki muut ovat hänen alaisiaan.\n" + syote, 3, artonAlaiset);

    }

    @Test
    @Points("3.2.5")
    public void pomonJaAlaistenOhjelmointikieltonOppimisJaloytamisTest() throws Throwable {
        Object kalle = Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).invoke("Kalle", 50000);
        Object pekka = Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).invoke("Pekka", 50000);
        Object antti = Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).invoke("Antti", 50000);
        Object jarmo = Reflex.reflect(KOODARILUOKKA).constructor().taking(String.class, int.class).invoke("Jarmo", 50000);
//        MethodRef0<Object, String> KoodarihaeNimi = Reflex.reflect(KOODARILUOKKA).method("haeNimi").returning(String.class).takingNoParams();
//        MethodRef0<Object, Integer> KoodarihaePalkka = Reflex.reflect(KOODARILUOKKA).method("haePalkka").returning(int.class).takingNoParams();
//        MethodRef0<Object, Integer> KoodarilaskeAlaiset = Reflex.reflect(KOODARILUOKKA).method("laskeAlaiset").returning(int.class).takingNoParams();
        MethodRef1<Object, Void, String> KoodarilisaaKieli = Reflex.reflect(KOODARILUOKKA).method("lisaaKieli").returningVoid().taking(String.class);
//        MethodRef1<Object, Boolean, String> KoodarionkoTaitoa = Reflex.reflect(KOODARILUOKKA).method("onkoTaitoa").returning(boolean.class).taking(String.class);
        Object eskoPomo = Reflex.reflect("Pomo").constructor().taking(String.class, int.class).invoke("Esko", 100);
        Object mattiPomo = Reflex.reflect("Pomo").constructor().taking(String.class, int.class).invoke("Matti", 500);
        Object artoPomo = Reflex.reflect("Pomo").constructor().taking(String.class, int.class).invoke("Arto", 5000);
        KoodarilisaaKieli.invokeOn(kalle, "java");
        KoodarilisaaKieli.invokeOn(kalle, "python");
        KoodarilisaaKieli.invokeOn(kalle, "prolog");
        KoodarilisaaKieli.invokeOn(kalle, "c");
        KoodarilisaaKieli.invokeOn(pekka, "java");
        KoodarilisaaKieli.invokeOn(antti, "java");
        KoodarilisaaKieli.invokeOn(antti, "mathlab");
        KoodarilisaaKieli.invokeOn(jarmo, "java");
        KoodarilisaaKieli.invokeOn(jarmo, "ruby");
        KoodarilisaaKieli.invokeOn(jarmo, "html");

        Class pomoClass = ReflectionUtils.findClass("Pomo");
        Class koodariClass = ReflectionUtils.findClass("Koodari");
        Class tyontekijaClass = ReflectionUtils.findClass("Tyontekija");
        Method pomoLisaaAlainen = ReflectionUtils.requireMethod(pomoClass, "lisaaAlainen", tyontekijaClass);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, eskoPomo, mattiPomo);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, mattiPomo, artoPomo);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, mattiPomo, antti);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, artoPomo, kalle);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, artoPomo, pekka);
        ReflectionUtils.invokeMethod(Void.TYPE, pomoLisaaAlainen, artoPomo, jarmo);
        
        Method onkoTaitoa = ReflectionUtils.requireMethod(pomoClass, boolean.class, "onkoTaitoa", String.class);
        String syote = " Koodari kalle = new Koodari(\"Kalle\", 50000);\n"
                + " kalle.lisaaKieli(\"java\");\n"
                + " kalle.lisaaKieli(\"python\");\n"
                + " kalle.lisaaKieli(\"prolog\");\n"
                + " kalle.lisaaKieli(\"c\");\n"
                + " Koodari pekka = new Koodari(\"Pekka\", 50000);\n"
                + " pekka.lisaaKieli(\"java\");\n"
                + " Koodari antti = new Koodari(\"Antti\", 50000);\n"
                + " antti.lisaaKieli(\"mathlab\");\n"
                + " antti.lisaaKieli(\"java\");\n"
                + " Koodari jarmo = new Koodari(\"Jarmo\", 50000);\n"
                + " jarmo.lisaaKieli(\"java\");\n"
                + " jarmo.lisaaKieli(\"ruby\");\n"
                + " jarmo.lisaaKieli(\"html\");\n"
                + " Pomo esko = new Pomo(\"Esko\", 10);\n"
                + " Pomo matti = new Pomo(\"Matti\", 100);\n"
                + " Pomo arto = new Pomo(\"Arto\", 5000);\n"
                + " esko.lisaaAlainen(matti);\n"
                + " matti.lisaaAlainen(arto);\n"
                + " matti.lisaaAlainen(antti);\n"
                + " arto.lisaaAlainen(kalle);\n"
                + " arto.lisaaAlainen(pekka);\n"
                + " arto.lisaaAlainen(jarmo);\n";

        boolean eskoPomoJavaa = ReflectionUtils.invokeMethod(boolean.class, onkoTaitoa, eskoPomo, "java");
        assertTrue("Seuraavalla syötteellä tulisi eskoPomon onkotaitoa(\"java\") palauttaa true; \n" + syote, eskoPomoJavaa);

        eskoPomoJavaa = ReflectionUtils.invokeMethod(boolean.class, onkoTaitoa, eskoPomo, "haskel");
        assertFalse("Seuraavalla syötteellä tulisi eskoPomon onkotaitoa(\"haskel\") palauttaa false; \n" + syote, eskoPomoJavaa);

        eskoPomoJavaa = ReflectionUtils.invokeMethod(boolean.class, onkoTaitoa, eskoPomo, "ruby");
        assertTrue("Seuraavalla syötteellä tulisi eskoPomon onkotaitoa(\"ruby\") palauttaa true; \n" + syote, eskoPomoJavaa);

        eskoPomoJavaa = ReflectionUtils.invokeMethod(boolean.class, onkoTaitoa, artoPomo, "java");
        assertTrue("Seuraavalla syötteellä tulisi artoPomon onkotaitoa(\"java\") palauttaa true; \n" + syote, eskoPomoJavaa);

        eskoPomoJavaa = ReflectionUtils.invokeMethod(boolean.class, onkoTaitoa, eskoPomo, "ranska");
        assertFalse("Seuraavalla syötteellä tulisi eskoPomon onkotaitoa(\"ranska\") palauttaa false; \n" + syote, eskoPomoJavaa);

        eskoPomoJavaa = ReflectionUtils.invokeMethod(boolean.class, onkoTaitoa, mattiPomo, "perl");
        assertFalse("Seuraavalla syötteellä tulisi mattiPomon onkotaitoa(\"perl\") palauttaa false; \n" + syote, eskoPomoJavaa);
    }
}
