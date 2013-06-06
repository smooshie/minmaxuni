
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LyyraKorttiTest {

    Class lyyraClass;
    Class unicafeClass;
    Constructor<Object> lyyraYksConst;

    @Before
    public void setUp() {
        try {
            lyyraClass = ReflectionUtils.findClass("LyyraKortti");

            unicafeClass = ReflectionUtils.findClass("Unicafe");
        } catch (Throwable t) {
        }
    }

    @Test
    @Points("1.1.1")
    public void onhanLuokkaLyyrakortti() {
        assertNotNull("Olethan tehnyt luokan \"LyyraKortti\"", lyyraClass);
    }

    private Constructor getLyyrayksKonst() {
        try {
            return ReflectionUtils.requireConstructor(lyyraClass, String.class);
        } catch (Throwable r) {
            fail("Tee luokalle \"LyyraKortti\" konstuktori, joka saa parametrina kortin haltijan nimen");
        }
        return null;
    }

    private Method getLyyraLisaaRahaa() {
        try {
            return ReflectionUtils.requireMethod(lyyraClass, "lisaaRahaa", int.class);
        } catch (Throwable r) {
            fail("Tee luokalle \"LyyraKortti\" metodi \"public void lisaaRahaa(int rahamaara)\"" + r);
        }
        return null;
    }

    private Method getLyyraGetNimi() {
        try {
            return ReflectionUtils.requireMethod(lyyraClass, "haeOmistaja");
        } catch (Throwable r) {
            fail("Tee luokalle \"LyyraKortti\" metodi \"public string haeOmistaja()\"");
        }
        return null;
    }

    private Method getLyyraGetRahaaSaldo() {
        try {
            return ReflectionUtils.requireMethod(lyyraClass, "haeRahamaara");
        } catch (Throwable r) {
            fail("Tee luokalle \"LyyraKortti\" metodi \"public int haeRahamaara()\"");
        }
        return null;
    }

    private Method getLyyraGetNimiSyoEdullisesti() {
        try {
            return ReflectionUtils.requireMethod(lyyraClass, "syoEdullisesti");
        } catch (Throwable r) {
            fail("Tee luokalle \"LyyraKortti\" metodi \"public void syoEdullisesti()\"");
        }
        return null;
    }

    private Method getLyyraGetNimiSyoMaukkaasti() {
        try {
            return ReflectionUtils.requireMethod(lyyraClass, "syoMaukkaasti");
        } catch (Throwable r) {
            fail("Tee luokalle \"LyyraKortti\" metodi \"public void syoMaukkasti()\"");
        }
        return null;
    }

    @Test
    @Points("1.1.1")
    public void LyyrakortissaYksiparametrinenKonstruktoriJokaOttaaStringinJaGetNimiToimii() {
        lyyraYksConst = getLyyrayksKonst();

        Method lyyraAsetaRahaa = getLyyraLisaaRahaa();
        Method haenimi = getLyyraGetNimi();
        Object lyyraKortti = null;
        try {
            lyyraKortti = ReflectionUtils.invokeConstructor(lyyraYksConst, "Tapani");
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" konstuktori, joka saa parametrina kortin haltijan nimen");
        }
        String nimi = "";
        try {
            nimi = ReflectionUtils.invokeMethod(String.class, haenimi, lyyraKortti);
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" metodi \"public String haeOmistaja()\"\n");
        }
        String getnimiEiToimi = "Kokeile ohjelmasi toimintaa seuraavalla:\n"
                + " LyyraKortti kortti = new LyyraKortti(\"Tapani\")\n"
                + "kortti.haeOmistaja()\n"
                + "Tulisi palauttaa omistajan nimeksi Tapani";
        assertEquals(getnimiEiToimi, "Tapani", nimi);

        int saldo = 11;
        try {
            saldo = ReflectionUtils.invokeMethod(int.class, getLyyraGetRahaaSaldo(), lyyraKortti);
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" metodi \"public int haeRahamaara()\"");
        }
        assertEquals("Kun LyyraKortti on luotu, tulisi saldon olla nolla (0)", 0, saldo);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getLyyraLisaaRahaa(), lyyraKortti, 1337);
        } catch (Throwable ex) {
            fail("Kortin saldon lisääminen ei toiminut, olethan toteuttanut metodin \"public void lisaaRahaa(int rahamaara)\"");
        }

        saldo = 0;
        try {
            saldo = ReflectionUtils.invokeMethod(int.class, getLyyraGetRahaaSaldo(), lyyraKortti);
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" metodi \"public int haeRahamaara()\"");
        }
        assertEquals("Kokeile seuraavalla ohjelmasi toimintaa:\n"
                + "LyyraKortti kortti = new LyyraKortti(\"Tapani\")\n"
                + "kortti.lisaaRahaa(1337)\n"
                + "Tulisi kortilla olla rahaa 1337 eli 13 € 17 snt.", 1337, saldo);


    }

    private int getKortinSaldo(Object lyyraKortti) {
        try {
            return ReflectionUtils.invokeMethod(int.class, getLyyraGetRahaaSaldo(), lyyraKortti);
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" metodi \"public int haeRahamaara()\"");
        }
        return Integer.MIN_VALUE;
    }

    @Test
    @Points("1.1.2")
    public void LyyrakortissaYksiparametrinenKonstruktoriJSyoEdullisestiToimii() {
        lyyraYksConst = getLyyrayksKonst();
        Method lyyraAsetaRahaa = getLyyraLisaaRahaa();
        Object lyyraKortti = null;
        try {
            lyyraKortti = ReflectionUtils.invokeConstructor(lyyraYksConst, "Tapani");
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" konstuktori, joka saa parametrina kortin haltijan nimen");
        }
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getLyyraLisaaRahaa(), lyyraKortti, 3000);
        } catch (Throwable ex) {
            fail("Kortin saldon lisääminen ei toiminut, olethan toteuttanut metodin \"public void lisaaRahaa(int rahamaara)\"");
        }
        final int syoEdullisestiVahennus = 260;
        final int syoMaukkaastiVahennus = 420;
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getLyyraGetNimiSyoEdullisesti(), lyyraKortti);
        } catch (Throwable ex) {
            fail("Olethan tehnyt luokalle \"LyyraKortti\" metodin \"public void syoEdullisesti()\"");
        }
        assertEquals("Kokeile seuraavalla ohjelmasi toimintaa:\n"
                + "LyyraKortti kortti = new LyyraKortti(\"Tapani\")\n"
                + "kortti.lisaaRahaa(3000)\n"
                + "kortti.syoEdullisesti()\n"
                + "Tulisi kortilla olla rahaa: " + (3000 - syoEdullisestiVahennus) + " mutta kortilla rahaa on:" + getKortinSaldo(lyyraKortti), 3000 - syoEdullisestiVahennus, getKortinSaldo(lyyraKortti));



        for (int i = 0; i < 1000; i++) {
            try {
                ReflectionUtils.invokeMethod(Void.TYPE, getLyyraGetNimiSyoEdullisesti(), lyyraKortti);
            } catch (Throwable ex) {
                fail("Olethan tehnyt luokalle \"Lyyrakortti\" metodin \"public void syoEdullisesti()\"");
            }
            assertTrue("Kortin saldo ei saa olla pienempi kuin nolla\n"
                    + "Kokeile seuraavalla ohjelmasi toimintaa:\n"
                    + "LyyraKortti kortti = new LyyraKortti(\"Tapani\")\n"
                    + "kortti.lisaaRahaa(30)\n"
                    + "kortti.syoEdullisesti()\n", getKortinSaldo(lyyraKortti) >= 0);
        }

    }

    @Test
    @Points("1.1.2")
    public void LyyrakortissaYksiparametrinenKonstruktoriJSyoMaukkaastiToimii() {
        lyyraYksConst = getLyyrayksKonst();
        Method lyyraAsetaRahaa = getLyyraLisaaRahaa();
        Object lyyraKortti = null;
        try {
            lyyraKortti = ReflectionUtils.invokeConstructor(lyyraYksConst, "Tapani");
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" konstuktori, joka saa parametrina kortin haltijan nimen");
        }
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getLyyraLisaaRahaa(), lyyraKortti, 3000);
        } catch (Throwable ex) {
            fail("Kortin saldon lisääminen ei toiminut, olethan toteuttanut metodin \"public void lisaaRahaa(int rahamaara)\"");
        }
        final int syoEdullisestiVahennus = 260;
        final int syoMaukkaastiVahennus = 420;
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getLyyraGetNimiSyoMaukkaasti(), lyyraKortti);
        } catch (Throwable ex) {
            fail("Olethan tehnyt luokalle \"LyyraKortti\" metodin \"public void syoMaukkaasti()\"");
        }
        assertEquals("Kokeile seuraavalla ohjelmasi toimintaa:\n"
                + "LyyraKortti kortti = new LyyraKortti(\"Tapani\")\n"
                + "kortti.lisaaRahaa(3000)\n"
                + "kortti.syoMaukkaasti()\n"
                + "Tulisi kortilla olla rahaa: " + (3000 - syoMaukkaastiVahennus) + " mutta kortilla rahaa on:" + getKortinSaldo(lyyraKortti), 3000 - syoMaukkaastiVahennus, getKortinSaldo(lyyraKortti));


        for (int i = 0; i < 1000; i++) {
            try {
                ReflectionUtils.invokeMethod(Void.TYPE, getLyyraGetNimiSyoMaukkaasti(), lyyraKortti);
            } catch (Throwable ex) {
                fail("Olethan tehnyt luokalle \"LyyraKortti\" metodin \"public void syoMaukkaasti()\"");
            }
            assertTrue("Kortin saldo ei saa olla pienempi kuin nolla\n"
                    + "Kokeile seuraavalla ohjelmasi toimintaa:\n"
                    + "LyyraKortti kortti = new LyyraKortti(\"Tapani\")\n"
                    + "kortti.lisaaRahaa(30)\n"
                    + "kortti.syoMaukkaasti()\n", getKortinSaldo(lyyraKortti) >= 0);
        }
    }

    private Constructor getKaksParamKonstruktor() {
        return ReflectionUtils.requireConstructor(lyyraClass, String.class, int.class);
    }

    @Test
    @Points("1.1.3")
    public void LyyrakortissaKaksiParametrinenKonstruktori() {
        Constructor kaksparam = ReflectionUtils.requireConstructor(lyyraClass, String.class, int.class);

        Method lyyraAsetaRahaa = getLyyraLisaaRahaa();
        Object lyyraKortti = null;
        try {
            lyyraKortti = ReflectionUtils.invokeConstructor(kaksparam, "Tapani", 200);
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" konstuktori, joka saa parametrina kortin haltijan nimen, ja alkusaldon");
        }

        assertTrue("Kokeile ohjelmasi toimitaa seuraavalla:\n"
                + "new Lyyrakortti(\"Tapsa\", 200) \n"
                + "Tulisi kortin saldon olla 200", getKortinSaldo(lyyraKortti) == 200);

        lyyraAsetaRahaa = getLyyraLisaaRahaa();
        lyyraKortti = null;
        try {
            lyyraKortti = ReflectionUtils.invokeConstructor(kaksparam, "Tapani", -200);
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" konstuktori, joka saa parametrina kortin haltijan nimen, ja alkusaldon");
        }

        assertTrue("Kokeile ohjelmasi toimitaa seuraavalla:\n"
                + "new LyyraKortti(\"Tapsa\", -200) \n"
                + "Tulisi kortin saldon olla 0", getKortinSaldo(lyyraKortti) == 0);


        lyyraAsetaRahaa = getLyyraLisaaRahaa();
        lyyraKortti = null;
        try {
            lyyraKortti = ReflectionUtils.invokeConstructor(kaksparam, "Pekka", 30000);
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" konstuktori, joka saa parametrina kortin haltijan nimen, ja alkusaldon");
        }
        String nimi = "";
        try {
            nimi = ReflectionUtils.invokeMethod(String.class, getLyyraGetNimi(), lyyraKortti);
        } catch (Throwable ex) {
            fail("" + ex);
        }
        assertEquals("Asettaahan kaksiparametrinen konstruktori kortin haltijan nimen?", "Pekka", nimi);

    }

    @Test
    @Points("1.1.4")
    public void LyyrakortissaKaksiParametrinenJaToString() {
        Constructor kaksparam = ReflectionUtils.requireConstructor(lyyraClass, String.class, int.class);

        Method lyyraAsetaRahaa = getLyyraLisaaRahaa();
        Object lyyraKortti = null;
        try {
            lyyraKortti = ReflectionUtils.invokeConstructor(kaksparam, "Tapani", 1337);
        } catch (Throwable ex) {
            fail("Tee luokalle \"LyyraKortti\" konstuktori, joka saa parametrina kortin haltijan nimen, ja alkusaldon");
        }
        Method toStringLyyra = ReflectionUtils.requireMethod(lyyraClass, "toString");
        String to_s = "";
        try {
            to_s = ReflectionUtils.invokeMethod(String.class, toStringLyyra, lyyraKortti);
        } catch (Throwable ex) {
            fail("Olethan tehnyt methodin \"toString\" luokalle \"Lyyrakortti\"");
        }
//        fail(to_s);
        if (to_s.contains("@")) {
            fail("Olethan tehnyt methodin \"toString\" luokalle \"Lyyrakortti\"");
        }
        assertTrue("Näkyyhän toStringin palauttamassa Stringissä nykyinen rahasumma muodossa <eurot>.<sentit>", to_s.contains("13.37"));
        assertTrue("Näkyyhän toStringin palauttamassa Stringissä kortin haltijan nimi?", to_s.contains("Tapani"));
        String regex = "^Tapani.*\\(.*\\d.*[.].*\\d.*[euro|euroa].*\\).*";
        assertTrue("Vastaahan tulostuksen ulkoasu tehtävänannossa vaadittua?", to_s.matches(regex));
    }

    private Method getLataaKateisella() {
        try {
            return ReflectionUtils.requireMethod(unicafeClass, "lataaKateisella", lyyraClass, int.class);
        } catch (Throwable t) {
            fail("Olethan luonut metodin public void lataaKateisella(LyyraKortti kortti, int rahamaara) luokkaan Unicafe");
        }
        return null;
    }

    private Method getLataaKortilla() {
        try {
            return ReflectionUtils.requireMethod(unicafeClass, "lataaKortilla", lyyraClass, int.class);
        } catch (Throwable t) {
            fail("Olethan luonut metodin public void lataaKortilla(LyyraKortti kortti, int rahamaara) luokkaan Unicafe");
        }
        return null;
    }

    private Method getUnicafeHaeRahamaaraMethod() {
        return ReflectionUtils.requireMethod(unicafeClass, "haeRahamaara");
    }

    private int getHaeRahamaaraUnicafe(Object unicafe) {
        try {
//            ReflectionUtils.invo
            int m = ReflectionUtils.invokeMethod(int.class, getUnicafeHaeRahamaaraMethod(), unicafe);
            return m;
        } catch (Throwable ex) {
            fail("Tee luokalle \"Unicafe\" metodi \"public int haeRahamaara()\"\n" + ex);
        }
        return Integer.MIN_VALUE;


    }

    private Object getNewLyyra(String nimi, int rahaa) {
        Object lyraKortti = null;
        try {
            lyraKortti = ReflectionUtils.invokeConstructor(ReflectionUtils.requireConstructor(lyyraClass, String.class, int.class), nimi, rahaa);
        } catch (Throwable ex) {
            fail("Lyyrakortin kaksiparametrisen konstruktorin käyttö ei onnistu.");
        }
        return lyraKortti;
    }

    private Method getsyoEdullisestiUnicafe() {
        return ReflectionUtils.requireMethod(unicafeClass, "syoEdullisesti", lyyraClass);
    }

    private Method getsyoMaukkaastiUnicafe() {
        return ReflectionUtils.requireMethod(unicafeClass, "syoMaukkaasti", lyyraClass);
    }

    @Test
    @Points("1.1.5")
    public void onLuokkaUnicafe() {
        assertNotNull("Olethan luonut luokan \"Unicafe\"", unicafeClass);
    }

    private Object getnewUnicafe() {
        Object unicafe = null;
        try {
            unicafe = ReflectionUtils.invokeConstructor(ReflectionUtils.requireConstructor(unicafeClass));
        } catch (Throwable ex) {
            fail("Olethan luonut luokan \"Unicafe\"");
        }
        return unicafe;

    }

    @Test
    @Points("1.1.5")
    public void lataaKateisellaLyyrakorttia() {
        Object lyyraKorttiObject = getNewLyyra("Pertti", 0);

        getLataaKateisella();
        getLataaKortilla();
        Object unicafe = null;
        try {
            unicafe = getnewUnicafe();
        } catch (Error t) {
            fail("Tee luokalle \"Unicafe\" metodi \"public int haeRahamaara()\"");
        }
        int rahaaUnicafessaHetiAlussa = getHaeRahamaaraUnicafe(unicafe);
        assertTrue("kun unicafe luodaan, tulee kassan olla tyhjä.", 0 == rahaaUnicafessaHetiAlussa);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getLataaKateisella(), unicafe, lyyraKorttiObject, 12345);
        } catch (Throwable ex) {
            fail("Lyyrakortille lataaminen käteisellä ei onnistu; lisätietoa: " + ex);
        }
        assertEquals("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\");\n"
                + "exactum.lataaKateisella(aapelinKortti, 12345);", 12345, getKortinSaldo(lyyraKorttiObject));

        int rahaaUnicafessa = getHaeRahamaaraUnicafe(unicafe);
        assertTrue("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\");\n"
                + "exactum.lataaKateisella(aapelinKortti, 12345);", 12345 == rahaaUnicafessa);
    }

    @Test
    @Points("1.1.5")
    public void lataaKortillaLyyrakorttia() {
        Object lyyraKorttiObject = getNewLyyra("Pertti", 0);
        Object unicafe = getnewUnicafe();
        int rahaaUnicafessaHetiAlussa = getHaeRahamaaraUnicafe(unicafe);
        assertTrue("Kun unicafe luodaan, tulee kassan olla tyhjä.", 0 == rahaaUnicafessaHetiAlussa);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getLataaKortilla(), unicafe, lyyraKorttiObject, 12345);
        } catch (Throwable ex) {
            fail("Lyyra-kortin lataaminen kortilla ei onnistu: " + ex);
        }
        assertEquals("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\");\n"
                + "exactum.lataaKateisella(aapelinKortti, 12345);", 12345, getKortinSaldo(lyyraKorttiObject));

        int rahaaUnicafessa = getHaeRahamaaraUnicafe(unicafe);
        assertTrue("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\");\n"
                + "exactum.lataaKortilla(aapelinKortti, 12345);", 0 == rahaaUnicafessa);
    }

    @Test
    @Points("1.1.5")
    public void lataaKortillaLyyrakorttiaKunNegatiivisiaArvoja() {
        Object lyyraKorttiObject = getNewLyyra("Pertti", 0);
        Object unicafe = getnewUnicafe();
        int rahaaUnicafessaHetiAlussa = getHaeRahamaaraUnicafe(unicafe);
        assertTrue("Kun unicafe luodaan, tulee kassan olla tyhjä.", 0 == rahaaUnicafessaHetiAlussa);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getLataaKortilla(), unicafe, lyyraKorttiObject, -12345);
        } catch (Throwable ex) {
            fail("Lyyra-kortin lataaminen kortilla ei onnistu, kun ladattiin negatiivinen arvo: " + ex);
        }
        assertEquals("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\");\n"
                + "exactum.lataaKortilla(aapelinKortti, -12345);\n"
                + "Kortin saldon ei pitäisi tällöin muuttua.", 0, getKortinSaldo(lyyraKorttiObject));

        int rahaaUnicafessa = getHaeRahamaaraUnicafe(unicafe);
        assertTrue("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\");\n"
                + "exactum.lataaKortilla(aapelinKortti, -12345);", 0 == rahaaUnicafessa);
    }

//    private void saniteettitarkastus(String luokanNimi, int muuttujia, String msg) throws SecurityException {
//
//        Field[] kentat = ReflectionUtils.findClass(luokanNimi).getDeclaredFields();
//
//        String viesti = ", HUOM: jos haluat tallettaa lounaan hinnan olion muuttujaan, tee se seuraavasti: "
//                + " private static final double EDULLISEN_HINTA = 2.5; ";
//
//        for (Field field : kentat) {
//            assertFalse("et tarvitse \"stattisia muuttujia\", poista " + kentta(field.toString()) + viesti, field.toString().contains("static") && !field.toString().contains("final"));
//            assertTrue("luokan kaikkien oliomuuttujien näkyvyyden tulee olla private, muuta " + kentta(field.toString()) + viesti,
//                    field.toString().contains("private"));
//        }
//
//        if (kentat.length > 1) {
//            int var = 0;
//            for (Field field : kentat) {
//                if (!field.toString().contains("final")) {
//                    var++;
//                }
//            }
//            assertTrue(msg + viesti, var <= muuttujia);
//        }
//    }
    private Method getUnicafeMethodSyoEdullisestiKortilla() {
        return ReflectionUtils.requireMethod(unicafeClass, "syoEdullisesti", lyyraClass);
    }

    private Method getUnicafeMethodSyoMaukkaastiKortilla() {
        return ReflectionUtils.requireMethod(unicafeClass, "syoMaukkaasti", lyyraClass);
    }

    private Method getUnicafeMethodSyoEdullisestiKateisella() {
        return ReflectionUtils.requireMethod(unicafeClass, "syoEdullisesti");
    }

    private Method getUnicafeMethodSyoMaukkaastiKateisella() {
        return ReflectionUtils.requireMethod(unicafeClass, "syoMaukkaasti");
    }

    @Test
    @Points("1.1.6")
    public void MaksakortillaUnicafessaSyoEdullisesti() {
        Object lyyraKorttiObject = getNewLyyra("Pertti", 1000);
        Object unicafe = getnewUnicafe();
        int rahaaUnicafessaHetiAlussa = getHaeRahamaaraUnicafe(unicafe);
//        assertTrue("kun unicafe luodaan, tulee kassan olla tyhjä.", 0 == rahaaUnicafessaHetiAlussa);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeMethodSyoEdullisestiKortilla(), unicafe, lyyraKorttiObject);
        } catch (Throwable ex) {
            fail("Oltehan toteuttanut metodin \"public void syoEdullisesti(LyyraKortti kortti)\"?\n ");
        }
        assertEquals("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\", 1000);\n"
                + "exactum.syoEdullisesti(aapelinkortti);"
                + "kortin saldon pitäisi vähentyä 240.", 1000 - 260, getKortinSaldo(lyyraKorttiObject));

        int rahaaUnicafessa = getHaeRahamaaraUnicafe(unicafe);
        assertEquals("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\", 1000);\n"
                + "exactum.syoEdullisesti(aapelinkortti);"
                + "Exactumin kassassa ei pitäisi olla yhtään enempää rahaa.", 0, getHaeRahamaaraUnicafe(unicafe));
    }

    @Test
    @Points("1.1.6")
    public void MaksakortillaUnicafessaSyoMaukkaasti() {
        Object lyyraKorttiObject = getNewLyyra("Pertti", 1000);
        Object unicafe = getnewUnicafe();
        int rahaaUnicafessaHetiAlussa = getHaeRahamaaraUnicafe(unicafe);
//        assertTrue("kun unicafe luodaan, tulee kassan olla tyhjä.", 0 == rahaaUnicafessaHetiAlussa);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeMethodSyoMaukkaastiKortilla(), unicafe, lyyraKorttiObject);
        } catch (Throwable ex) {
            fail("Olethan toteuttanut metodin \"public void syoMaukkaasti(LyyraKortti kortti)\"?\n ");
        }
        assertEquals("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\", 1000);\n"
                + "exactum.syoEdullisesti(aapelinkortti);"
                + "kortin saldon pitäisi vähentyä 420.", 1000 - 420, getKortinSaldo(lyyraKorttiObject));

        int rahaaUnicafessa = getHaeRahamaaraUnicafe(unicafe);
        assertEquals("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\", 1000);\n"
                + "exactum.syoEdullisesti(aapelinkortti);"
                + "Exactumin kassassa ei pitäisi olla yhtään enempää rahaa.", 0, getHaeRahamaaraUnicafe(unicafe));
    }

    @Test
    @Points("1.1.6")
    public void MaksaKateisellaUnicafessaSyoMaukkaasti() {
        Object lyyraKorttiObject = getNewLyyra("Pertti", 1000);
        Object unicafe = getnewUnicafe();
        int rahaaUnicafessaHetiAlussa = getHaeRahamaaraUnicafe(unicafe);
//        assertTrue("kun unicafe luodaan, tulee kassan olla tyhjä.", 0 == rahaaUnicafessaHetiAlussa);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeMethodSyoMaukkaastiKateisella(), unicafe);
        } catch (Throwable ex) {
            fail("Olethan toteuttanut metodin \"public void syoMaukkaasti()\"?\n " + ex);
        }

        int rahaaUnicafessa = getHaeRahamaaraUnicafe(unicafe);
        assertEquals("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\", 1000);\n"
                + "exactum.syoEdullisesti(aapelinkortti);"
                + "Exactumin kassassa pitäisi olla nyt 4.20€ enemmän rahaa.", 420, getHaeRahamaaraUnicafe(unicafe));
    }

    @Test
    @Points("1.1.6")
    public void MaksaKateisellaUnicafessaSyoEdullisesti() {
        Object lyyraKorttiObject = getNewLyyra("Pertti", 1000);
        Object unicafe = getnewUnicafe();
        int rahaaUnicafessaHetiAlussa = getHaeRahamaaraUnicafe(unicafe);
//        assertTrue("kun unicafe luodaan, tulee kassan olla tyhjä.", 0 == rahaaUnicafessaHetiAlussa);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeMethodSyoEdullisestiKateisella(), unicafe);
        } catch (Throwable ex) {
            fail("Olethan toteuttanut metodin \"public void syoEdullisesti()\"?\n ");
        }


        int rahaaUnicafessa = getHaeRahamaaraUnicafe(unicafe);
        assertEquals("Unicafe exactum = new Unicafe();\n"
                + "LyyraKortti aapelinKortti = new LyyraKortti(\"Aapeli\", 1000);\n"
                + "exactum.syoEdullisesti(aapelinkortti);"
                + "Exactumin kassassa pitäisi olla nyt 2.60€ enemmän rahaa", 260, getHaeRahamaaraUnicafe(unicafe));
    }

    private Method getUnicafeLuokkaretkiMethod() {
        return ReflectionUtils.requireMethod(unicafeClass, "luokkaretki", lyyraClass, int.class, int.class);
    }

    private void initLuokkaretki(Object unicafe, Object lyyra, int edukk, int kalliita) {

        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeLuokkaretkiMethod(), unicafe, lyyra, edukk, kalliita);
//            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeLuokkaretkiMethod(), lyyra, unicafe, edukk, kalliita);
//            ReflectionUtils.in
        } catch (Throwable ex) {
            fail("Olethan luonut metodin \"public void luokkaretki(LyyraKortti kortti, int edulliset, int maukkaat)\" luokkaan \"Unicafe\"?");
        }

    }

    @Test
    @Points("1.1.7")
    public void unicafessaVoiKaydaLuokkaRetkella() {
        Object lyyraKorttiObject = getNewLyyra("Opettaja", 100000);
        Object unicafe = getnewUnicafe();
        int rahaaUnicafessaHetiAlussa = getHaeRahamaaraUnicafe(unicafe);
        int rahaaAlussaKortilla = getKortinSaldo(lyyraKorttiObject);
        int edull = 1;
        int kall = 0;
        initLuokkaretki(unicafe, lyyraKorttiObject, edull, kall);

        String esim = "Unicafe chemicum = new Unicafe();\n"
                + "LyyraKortti opettajanKortti = new LyyraKortti(\"Opettaja\", 10000);\n"
                + "chemicum.luokkaretki(opettajanKortti, 1, 0);";
        assertTrue("Metodin luokkaretki pitäisi vähentää kortin saldoa 2.60€, kun kortilla on riittävästi rahaa, ja ostetan yksi edullinen lounas.\n"
                + "Nyt rahaa oli: " + getKortinSaldo(lyyraKorttiObject) + "\n"
                + "Kokeie seuraavalla: \n" + esim, rahaaAlussaKortilla - 260 == getKortinSaldo(lyyraKorttiObject));


    }

    @Test
    @Points("1.1.7")
    public void unicafessaVoiKaydaLuokkaRetkellaMuttaEiMeneRahaaKunOstetaanLiikaaTest() {
        Object lyyraKorttiObject = getNewLyyra("Opettaja", 100000);
        Object unicafe = getnewUnicafe();
        int rahaaUnicafessaHetiAlussa = getHaeRahamaaraUnicafe(unicafe);
        int rahaaAlussaKortilla = getKortinSaldo(lyyraKorttiObject);
        int edull = 1000;
        int kall = 133717;
        initLuokkaretki(unicafe, lyyraKorttiObject, edull, kall);

        String esim = "Unicafe chemicum = new Unicafe();\n"
                + "LyyraKortti opettajanKortti = new LyyraKortti(\"Opettaja\", 10000);\n"
                + "chemicum.luokkaretki(opettajanKortti, 100, 133717);";
        assertTrue("Metodin luokkaretki ei pitäisi vähentää saldoa kortilta, kun kortin saldo ei riitä ostosten maksamiseen.\n"
                + "Nyt rahaa oli: " + getKortinSaldo(lyyraKorttiObject) + "\n"
                + "Kokeie seuraavalla:\n" + esim, rahaaAlussaKortilla == getKortinSaldo(lyyraKorttiObject));


    }

    private Method getUnicafeMethodAsiakasTulee() {
        assertNotNull("Olethan toteuttanut metodin \"public void asiakasTulee()\" luokkaan \"Uncafe\"", ReflectionUtils.requireMethod(unicafeClass, "asiakasTulee"));
        return ReflectionUtils.requireMethod(unicafeClass, "asiakasTulee");
    }

    private Method getUnicafeMethodAsiakasPoistuu() {
        Method pal = ReflectionUtils.requireMethod(unicafeClass, "asiakasPoistuu");
        assertNotNull("Olethan toteuttanut metodin \"public void asiakasPoistuu()\" luokkaan \"Uncafe\"", pal);
        return pal;
    }

    private Method getUnicafeMethodLaskeAsiakkaat() {
        Method pal = ReflectionUtils.requireMethod(unicafeClass, int.class, "laskeAsiakkaat");
        assertNotNull("Olethan toteuttanut metodin \"public void laskeAsiakkaat()\" luokkaan \"Uncafe\"", pal);
        return pal;
    }

    private void initAsiakasTulee(Object unicafe) {
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeMethodAsiakasTulee(), unicafe);
        } catch (Throwable ex) {
            fail("Olethan luonut metodin \"public void asiakasTulee()\" luokkaan \"Uncafe\"?");
        }
    }

    private void initAsiakasPoistuu(Object unicafe) {
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeMethodAsiakasPoistuu(), unicafe);
        } catch (Throwable ex) {
            fail("Olethan luonut metodin \"public void asiakasPoistuu()\" luokkaan \"Uncafe\"?");
        }
    }

    private int initLaskeAsiakkaat(Object unicafe) {
        try {
            return ReflectionUtils.invokeMethod(int.class, getUnicafeMethodLaskeAsiakkaat(), unicafe);
        } catch (Throwable ex) {
            fail("Olethan luonut metodin \"public void LaskeAsiakkaat()\" luokkaan \"Uncafe\"?");
        }
        return -1337;
    }

    private void init1337(){
        Object unicafe = getnewUnicafe();
        initAsiakasTulee(unicafe);
        initAsiakasPoistuu(unicafe);
    }
    
    private String liikutaDataa(Object unicafe) {
        init1337();
        initAsiakasTulee(unicafe);
        int kahvilla = initLaskeAsiakkaat(unicafe);
        String syote = "Unicafe chemicum = new Unicafe();\n"
                + "chemicum.asiakasTulee();\n";
        assertEquals("Seuraavan syötteen jälkeen pitäisi Unicafessa olla yksi kävijä.\n" + syote, 1, kahvilla);
        initAsiakasTulee(unicafe);
        syote += "chemicum.asiakasTulee();\n";
        kahvilla = initLaskeAsiakkaat(unicafe);
        assertEquals("Seuraavan syötteen jälkeen pitäisi Unicafessa olla kaksi kävijää.\n" + syote, 2, kahvilla);
        for (int i = 0; i < 10; i++) {
            initAsiakasTulee(unicafe);
            syote += "chemicum.asiakasTulee();\n";

        }
        kahvilla = initLaskeAsiakkaat(unicafe);
        assertEquals("Seuraavan syötteen jälkeen pitäisi Unicafessa olla " + 12 + " kävijää.\n" + syote, 12, kahvilla);

        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        syote += "chemicum.asiakasPoistuu();\n";
        syote += "chemicum.asiakasPoistuu();\n";

        kahvilla = initLaskeAsiakkaat(unicafe);
        assertEquals("Seuraavan syötteen jälkeen pitäisi Unicafessa olla " + 10 + " kävijää.\n" + syote, 10, kahvilla);


        for (int i = 0; i < 9; i++) {
            initAsiakasPoistuu(unicafe);
            syote += "chemicum.asiakasPoistuu();\n";

        }
        initAsiakasPoistuu(unicafe);
        syote += "chemicum.asiakasPoistuu();\n";

        kahvilla = initLaskeAsiakkaat(unicafe);
        assertEquals("Seuraavan syötteen jälkeen pitäisi Unicafessa olla " + 0 + " kävijää.\n" + syote, 0, kahvilla);
        return syote;
    }

    @Test
    @Points("1.1.8")
    public void unicafePitaaKirjaaSiellaKavijoista() {
                Object unicafe = getnewUnicafe();

//        initAsiakasTulee(unicafeClass);
//        initAsiakasPoistuu(unicafeClass);
//        initLaskeAsiakkaat(unicafeClass);
//        liikutaDataa(unicafe);

    }
    
     @Test
    @Points("1.1.8")
    public void unicafePitaaOnnistuneestiKirjaaSiellaKavijoista() {

        Object unicafe = getnewUnicafe();
        liikutaDataa(unicafe);

    }

    @Test
    @Points("1.1.9")
    public void uniCafeAsiakasTuleeJaMeneeOsaaLaskaEnnatyksenToimivasti() {
        Object unicafe = getnewUnicafe();
        String syote = liikutaDataa(unicafe);
        Method ennatys = null;
        try {
        ennatys = ReflectionUtils.requireMethod(unicafeClass, int.class, "asiakasennatys");
        } catch (Throwable ex) {
            fail("Olethan tehnyt luokkaan \"Unicafe\" metodin \"public int asiakasennatys()\"");
        }
        int tulos = 0;
        try {
            tulos = ReflectionUtils.invokeMethod(int.class, ennatys, unicafe);
        } catch (Throwable ex) {
            fail("Olethan tehnyt luokkaan \"Unicafe\" metodin \"public int asiakasennatys()\"");
        }
        assertEquals("Syötteellä: " + syote + " tulisi asiakasennätyksen olla 12.", 12, tulos);
        for (int i = 0; i < 100; i++) {
            initAsiakasTulee(unicafe);
        }
        syote += "100 x chemicum.asiakasTulee()";
        try {
            tulos = ReflectionUtils.invokeMethod(int.class, ennatys, unicafe);
        } catch (Throwable ex) {
            fail("Olethan tehnyt luokkaan \"Unicafe\" metodin \"public int asiakasennatys()\"");
        }
        assertEquals("Syötteellä: " + syote + "Tulisi asiakasennätyksen olla 100.", 100, tulos);
    }

    private String kentta(String toString) {
        return toString.replace("LyyraKortti" + ".", "");
    }
}