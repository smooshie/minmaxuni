
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef0;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef1;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TuloslistaTest {

    private final String TULOS = "Tulos";

    @Before
    public void setUp() {
    }
    MethodRef2<Object, Object, String, Integer> tuloslistaKonstruktori;
    MethodRef0<Object, String> haeNimi;
    MethodRef0<Object, Integer> haePisteet;
    MethodRef0<Object, String> toString;

    private void init() {
        tuloslistaKonstruktori = Reflex.reflect(TULOS).constructor().taking(String.class, int.class);
        if (!tuloslistaKonstruktori.exists()) {
            Assert.fail("Olethan luonut Luokalle \"Tulos\" kaksiparametrisen konstruktorin \"public Tulos(String nimi, int pisteet)\"");
        }
        haeNimi = Reflex.reflect(TULOS).method("haeNimi").returning(String.class).takingNoParams();
        if (!haeNimi.exists()) {
            Assert.fail("Olethan luonut Luokalle \"Tulos\" hetodin \"public String haeNimi()\"");
        }
        haePisteet = Reflex.reflect(TULOS).method("haePisteet").returning(int.class).takingNoParams();
        if (!haePisteet.exists()) {
            Assert.fail("Olethan luonut Luokalle \"Tulos\" hetodin \"public int haePisteet()\"");

        }
        toString = Reflex.reflect(TULOS).method("toString").returning(String.class).takingNoParams();
        if (!toString.exists()) {
            Assert.fail("Olethan luonut Luokalle \"Tulos\" hetodin \"public String toString()\"");

        }

    }

    @Test
    @Points("3.1.1")
    public void ekaTestiTest() throws Throwable {
        init();
        Object matti = tuloslistaKonstruktori.invoke("Matti", 20);

        String nimi = haeNimi.invokeOn(matti);
        Assert.assertEquals("Syötteellä: \n"
                + "Tulos matti = new Tulos(\"Matti\", 20);\n"
                + "matti.haeNimi();\n"
                + "Tulisi palauttaa Matti.", "Matti", nimi);

        int pisteet = (Integer) haePisteet.invokeOn(matti);
        junit.framework.Assert.assertEquals("Syötteellä: \n"
                + "Tulos matti = new Tulos(\"Matti\", 20);\n"
                + "matti.haePisteet();\n"
                + "Tulisi palauttaa 20.", 20, pisteet);

        String tostr = toString.invokeOn(matti);

        Assert.assertTrue("Syötteellä: \n"
                + "Tulos matti = new Tulos(\"Matti\", 20);\n"
                + "matti.toString();\n"
                + "tulisi palauttaa \"Matti 20\"", tostr.contains("Matti") && tostr.contains("20"));
//        Assert.assertTrue("Syötteellä: \n"
//                + "Tulos matti = new Tulos(\"Matti\", 20);\n"
//                + "matti.toString();\n"
//                + "tulisi palauttaa \"Matti 20\"\n"
//                + "Rivin tulisi päättyä henkilön pisteisiin.",tostr.matches("\\d+$"));

        matti = tuloslistaKonstruktori.invoke("Arto", 1337);

        nimi = haeNimi.invokeOn(matti);
        Assert.assertEquals("Syötteellä: \n"
                + "Tulos arto = new Tulos(\"Arto\", 137);\n"
                + "arto.haeNimi();\n"
                + "Tulisi palauttaa Arto.", "Arto", nimi);

        pisteet = (Integer) haePisteet.invokeOn(matti);
        junit.framework.Assert.assertEquals("Syötteellä: \n"
                + "Tulos arto = new Tulos(\"Arto\", 20);\n"
                + "arto.haePisteet();\n"
                + "Tulisi palauttaa 1337.", 1337, pisteet);

        tostr = toString.invokeOn(matti);

        Assert.assertTrue("Syötteellä: \n"
                + "Tulos matti = new Tulos(\"Arto\", 1337);\n"
                + "matti.toString();\n"
                + "tulisi palauttaa \"Arto 1337\"", tostr.contains("Arto") && tostr.contains("1337"));

    }

    @Test
    @Points("3.1.2")
    public void testataanCompareToMethodia() {
        //public int compareTo(Tulos toinen)
        Class tulos = ReflectionUtils.findClass(TULOS);
        Method compTo = ReflectionUtils.requireMethod(tulos, int.class, "compareTo", tulos);
        Constructor tulosConst = ReflectionUtils.requireConstructor(tulos, String.class, int.class);
        Object matti = null;
        try {
            matti = ReflectionUtils.invokeConstructor(tulosConst, "Matti", 15);
        } catch (Throwable ex) {
            Assert.fail("Olethan luonut konstruktorin \"public Tulos(String nimi, int pisteet)\" luokkaan Tulos");
        }

        Object arto = null;
        try {
            arto = ReflectionUtils.invokeConstructor(tulosConst, "Arto", 150);
        } catch (Throwable ex) {
            Assert.fail("Olethan luonut konstruktorin \"public Tulos(String nimi, int pisteet)\" luokkaan Tulos");
        }
        int vertailu = 0;
        try {
            vertailu = ReflectionUtils.invokeMethod(int.class, compTo, matti, arto);
        } catch (Throwable ex) {
            Assert.fail("Olethan toteuttanut methdin  \"public int compareTo(Tulos toinen)\" luokkaan \"Tulos\"");
        }

        String syote = "Tulos matti = new Tulos(\"Matti\", 15);\n"
                + "Tulos arto = new Tulos(\"Arto\", 150);\n"
                + "matti.compareTo(arto);";
        Assert.assertTrue("Seuraavan tulisi palauttaa luku joka on suurempi tai yhtäsuurikuin 1 \n" + syote, 1 <= vertailu);


        try {
            arto = ReflectionUtils.invokeConstructor(tulosConst, "Arto", 15);
        } catch (Throwable ex) {
            Assert.fail("Olethan luonut konstruktorin \"public Tulos(String nimi, int pisteet)\" luokkaan Tulos");
        }
        try {
            vertailu = ReflectionUtils.invokeMethod(int.class, compTo, matti, arto);
        } catch (Throwable ex) {
            Assert.fail("Olethan toteuttanut methdin  \"public int compareTo(Tulos toinen)\" luokkaan \"Tulos\"");
        }

        syote = "Tulos matti = new Tulos(\"Matti\", 15);\n"
                + "Tulos arto = new Tulos(\"Arto\", 15);\n"
                + "matti.compareTo(arto);";
        Assert.assertTrue("Seuraavan tulisi palauttaa luku joka on suurempi tai yhtäsuurikuin 1. \n"
                + "Koska pisteet ovat samat, tulee vertailla nimiä. Käytä tässä String-luokan omaa .compareTo-methodia \n" + syote, 1 <= vertailu);


        try {
            matti = ReflectionUtils.invokeConstructor(tulosConst, "Matti", 5);
        } catch (Throwable ex) {
            Assert.fail("Olethan luonut konstruktorin \"public Tulos(String nimi, int pisteet)\" luokkaan Tulos");
        }


        try {
            vertailu = ReflectionUtils.invokeMethod(int.class, compTo, arto, matti);
        } catch (Throwable ex) {
            Assert.fail("Olethan toteuttanut methdin  \"public int compareTo(Tulos toinen)\" luokkaan \"Tulos\"");
        }

        syote = "Tulos matti = new Tulos(\"Matti\", 15);\n"
                + "Tulos arto = new Tulos(\"Arto\", 15);\n"
                + "arto.compareTo(matti);";
        Assert.assertTrue("Seuraavan tulisi palauttaa luku joka on pienempi tai yhtäpienikuin 1. \n" + syote, 1 >= vertailu);
    }
    private final String TULOSLISTA = "Tuloslista";
    MethodRef1<Object, Object, Integer> tulostaConst;
    MethodRef2<Object, Void, String, Integer> lisaaTulos;
    MethodRef1<Object, Boolean, Integer> paaseeListalle;
    MethodRef0<Object, String> toString2;

    private void init3() {
        tulostaConst = Reflex.reflect(TULOSLISTA).constructor().taking(int.class);
        if (!tulostaConst.exists()) {
            Assert.fail("Olethan luonut konstruktorin \"public Tuloslista(int koko)\" luokalle \"Tuloslista\"");
        }

        lisaaTulos = Reflex.reflect(TULOSLISTA).method("lisaaTulos").returningVoid().taking(String.class, int.class);
        if (!lisaaTulos.exists()) {
            Assert.fail("Olethan luonut konstruktorin \"public void lisaaTulos(String nimi, int pisteet)\" luokalle \"Tuloslista\"");
        }
        //public boolean paaseeListalle(int pisteet)
        paaseeListalle = Reflex.reflect(TULOSLISTA).method("paaseeListalle").returning(boolean.class).taking(int.class);
        if (!paaseeListalle.exists()) {
            Assert.fail("Olethan luonut konstruktorin \"public boolean paaseeListalle(int pisteet)\" luokalle \"Tuloslista\"");
        }

        toString2 = Reflex.reflect(TULOSLISTA).method("toString").returning(String.class).takingNoParams();
        if (!toString2.exists()) {
            Assert.fail("Olethan luonut Luokalle \"Tuloslista\" metodin \"public String toString()\"");

        }
    }

    @Test
    @Points("3.1.3")
    public void tuloslistaPerusMetoditOlemassaTest() throws Throwable {
        init3();
        Object lista = tulostaConst.invoke(5);
        String tulostus = toString2.invokeOn(lista);
        String[] rivit = tulostus.replace("\r\n", "\n").split("\n");
        String syote = "Tuloslista lista = new Tuloslista(5);\n"
                + "lista.toString();";
        for (String string : rivit) {
            Assert.assertTrue("Seuraavalla syötteellä: " + syote + " tulisi jokaisen rivin olla: \"Uolevi 0\"", string.contains("Uolevi"));
            Assert.assertTrue("Seuraavalla syötteellä: " + syote + " tulisi jokaisen rivin olla: \"Uolevi 0\"", string.contains("0"));

        }
        lisaaTulos.invokeOn(lista, "Jarmo", 20);
        lisaaTulos.invokeOn(lista, "Pekka", 200);
        lisaaTulos.invokeOn(lista, "Arto", 40000);
        lisaaTulos.invokeOn(lista, "Arto", 4000);
        lisaaTulos.invokeOn(lista, "Matti", 35000);
        lisaaTulos.invokeOn(lista, "Matti", 30);

        syote = "Tuloslista lista = new Tuloslista(5);\n"
                + "lista.lisaaTulos(\"Jarmo\", 20);\n"
                + "lista.lisaaTulos(\"Pekka\", 200);\n"
                + "lista.lisaaTulos(\"Arto\", 40000);\n"
                + "lista.lisaaTulos(\"Arto\", 4000);\n"
                + "lista.lisaaTulos(\"Matti\", 35000);\n"
                + "lista.lisaaTulos(\"Matti\", 30);\n"
                + "lista.toString();";
        tulostus = toString2.invokeOn(lista);
        rivit = tulostus.replace("\r\n", "\n").split("\n");
		
		try {
            Assert.assertTrue("Seuraavalla syöteellä, tulisi ensimmäisellä rivillä lukea: \"Arto 40000\": " + syote, rivit[0].contains("Arto") && rivit[0].contains("40000"));
            Assert.assertTrue("Seuraavalla syöteellä, tulisi toisella rivillä lukea: \"Matti 35000\": " + syote, rivit[1].contains("Matti") && rivit[1].contains("35000"));
            Assert.assertTrue("Seuraavalla syöteellä, tulisi 3. rivillä lukea: \"Arto 4000\": " + syote, rivit[2].contains("Arto") && rivit[2].contains("4000"));
            Assert.assertTrue("Seuraavalla syöteellä, tulisi 4. rivillä lukea: \"Pekka 200\": " + syote, rivit[3].contains("Pekka") && rivit[3].contains("200"));
            Assert.assertTrue("Seuraavalla syöteellä, tulisi 5. rivillä lukea: \"Matti 30\": " + syote, rivit[4].contains("Matti") && rivit[4].contains("30"));
        } catch (ArrayIndexOutOfBoundsException aie) {
			Assert.fail("Tuloslistan toString:stä puuttuu rivejä.\nTehtiin viiden kokoinen tuloslista ja sen tulostus näytti seuraavalta:\n" + tulostus);
		}
		
        syote = "Tuloslista lista = new Tuloslista(5);\n"
                + "lista.lisaaTulos(\"Jarmo\", 20);\n"
                + "lista.lisaaTulos(\"Pekka\", 200);\n"
                + "lista.lisaaTulos(\"Arto\", 40000);\n"
                + "lista.lisaaTulos(\"Arto\", 4000);\n"
                + "lista.lisaaTulos(\"Matti\", 35000);\n"
                + "lista.lisaaTulos(\"Matti\", 30);\n";
        Assert.assertFalse("Seuraavalla syötteellä tulisi lista.paaseeListalle(2) palauttaa false: \n" + syote + "lista.paaseeListalle(20);", paaseeListalle.invokeOn(lista, 2));
        Assert.assertTrue("Seuraavalla syötteellä tulisi lista.paaseeListalle(500000) palauttaa true: \n" + syote + "lista.paaseeListalle(500000);", paaseeListalle.invokeOn(lista, 500000));

//        

    }

    @Test
    @Points("3.1.4")
    public void tiedostonTallennusTest() throws Throwable {
        //<editor-fold defaultstate="collapsed" desc="perus kamaa piilossa">
        init3();
        Object lista = tulostaConst.invoke(5);
//        String tulostus = toString2.invokeOn(lista);
//        String[] rivit = tulostus.replace("\r\n", "\n").split("\n");
//        String syote = "Tuloslista lista = new Tuloslista(5);\n"
//                + "lista.toString();";
//        for (String string : rivit) {
//            Assert.assertTrue("Seuraavalla syötteellä: " + syote + " tulisi jokaisen rivin olla: \"Uolevi 0\"", string.contains("Uolevi"));
//            Assert.assertTrue("Seuraavalla syötteellä: " + syote + " tulisi jokaisen rivin olla: \"Uolevi 0\"", string.contains("0"));
//            
//        }
        lisaaTulos.invokeOn(lista, "Jarmo", 20);
        lisaaTulos.invokeOn(lista, "Pekka", 200);
        lisaaTulos.invokeOn(lista, "Arto", 40000);
        lisaaTulos.invokeOn(lista, "Arto", 4000);
        lisaaTulos.invokeOn(lista, "Matti", 35000);
        lisaaTulos.invokeOn(lista, "Kaarlo", 30);

//        syote = "Tuloslista lista = new Tuloslista(5);\n"
//                + "lista.lisaaTulos(\"Jarmo\", 20);\n"
//                + "lista.lisaaTulos(\"Pekka\", 200);\n"
//                + "lista.lisaaTulos(\"Arto\", 40000);\n"
//                + "lista.lisaaTulos(\"Arto\", 4000);\n"
//                + "lista.lisaaTulos(\"Matti\", 35000);\n"
//                + "lista.lisaaTulos(\"Matti\", 30);\n"
//                + "lista.toString();";
//        tulostus = toString2.invokeOn(lista);
//        rivit = tulostus.replace("\r\n", "\n").split("\n");
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi ensimmäisellä rivillä lukea: \"Arto 40000\": " + syote, rivit[0].contains("Arto") && rivit[0].contains("40000"));
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi toisella rivillä lukea: \"Matti 35000\": " + syote, rivit[1].contains("Matti") && rivit[1].contains("35000"));
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi 3. rivillä lukea: \"Arto 4000\": " + syote, rivit[2].contains("Arto") && rivit[2].contains("4000"));
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi 4. rivillä lukea: \"Pekka 200\": " + syote, rivit[3].contains("Pekka") && rivit[3].contains("200"));
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi 5. rivillä lukea: \"Matti 30\": " + syote, rivit[4].contains("Matti") && rivit[4].contains("30"));


//        Assert.assertFalse("Seuraavalla syötteellä tulisi lista.paaseeListalle(2) palauttaa false: \n" + syote + "lista.paaseeListalle(20);", paaseeListalle.invokeOn(lista, 2));
//        Assert.assertTrue("Seuraavalla syötteellä tulisi lista.paaseeListalle(500000) palauttaa true: \n" + syote + "lista.paaseeListalle(500000);", paaseeListalle.invokeOn(lista, 500000));
        //</editor-fold>

        //public void tallenna(String tiedostonimi)
        MethodRef1<Object, Void, String> tallennaFilu = Reflex.reflect(TULOSLISTA).method("tallenna").returningVoid().taking(String.class);


        File tmp = null;
        String polku = "";
        try {
            tmp = File.createTempFile("tmp", "txt");
            tmp.deleteOnExit();
            polku = tmp.getPath() + System.currentTimeMillis();
//            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(tmp), "UTF-8");
//            out.append("apina monkey\ntalo house\nlinux linux\nMOOC is_the_best\nhelppo_nakki easy_sausage\npala_kakkua piece_of_cake\nhattu hat\nasd asd\npaikallinen local\nkeksi cookie\n");
//            out.flush();
//            out.close();
        } catch (IOException ex) {
            Assert.fail("Testitiedoston kirjoittaminen ei onnistu. Tähän ei olisi pitänyt päätyä!\n" + ex);
        }
        tallennaFilu.invokeOn(lista, polku);
        File tehtavassa;
        String syote = "Tuloslista lista = new Tuloslista(5);\n"
                + "lista.lisaaTulos(\"Jarmo\", 20);\n"
                + "lista.lisaaTulos(\"Pekka\", 200);\n"
                + "lista.lisaaTulos(\"Arto\", 40000);\n"
                + "lista.lisaaTulos(\"Arto\", 4000);\n"
                + "lista.lisaaTulos(\"Matti\", 35000);\n"
                + "lista.lisaaTulos(\"Kaarlo\", 30);\n";
        Scanner s = null;
        try {
            tehtavassa = new File(polku);
            s = new Scanner(tehtavassa);
        } catch (Exception e) {
            Assert.fail("Avattaessa tiedostoa, joka luotiin sijaintiin " + polku + " ei voitu avata. Saatiin exception: " + e + "\n\n"
                    + "Tässä vielä kommennot, jolla data tiedostoon tuotettiin:\n"
                    + "" + syote + "\n"
                    + "lista.tallenna(\"" + polku + "\");");
        }
        int riveja = 0;
        ArrayList<String> ar = new ArrayList<String>();
        while (s.hasNextLine()) {
            riveja++;
            ar.add(s.nextLine());
//            System.out.println(s.nextLine());
        }


        Assert.assertTrue("Tallentamasi tiedoston ensimmäisellä rivillä tulisi olla vain luku \"5\" joka on listan koko", 5 == Integer.parseInt(ar.get(0).trim()));
        Assert.assertTrue("Tallentamasi tiedoston ensimmäisellä rivillä tulisi olla vain luku \"5\" joka on listan koko", 11 == riveja);

        Assert.assertTrue("Seuraavalla syöteellä, tulisi 2 ja 3. riveillä tulisilukea: \"Arto\n40000\": " + syote, ar.get(1).contains("Arto") && ar.get(2).contains("40000"));
        Assert.assertTrue("Seuraavalla syöteellä, tulisi 4 ja 5. riveillä tulisi lukea: \"Matti\n35000\": " + syote, ar.get(3).contains("Matti") && ar.get(4).contains("35000"));
        Assert.assertTrue("Seuraavalla syöteellä, tulisi 6 ja 7. riveillä tulisi lukea: \"Art\n4000\": " + syote, ar.get(5).contains("Arto") && ar.get(6).contains("4000"));
        Assert.assertTrue("Seuraavalla syöteellä, tulisi 8 ja 9. riveillä tulisi lukea: \"Pekka\n200\": " + syote, ar.get(7).contains("Pekka") && ar.get(8).contains("200"));
        Assert.assertTrue("Seuraavalla syöteellä, tulisi 10 ja 11. riveillä tulisi lukea: \"Kaarlo\n30\": " + syote, ar.get(9).contains("Kaarlo") && ar.get(10).contains("30"));

    }

    @Test
    @Points("3.1.5")
    public void tiedostonLatausOnnistuuJaDataOsataanHyodyntaaTest() throws Throwable {
        //<editor-fold defaultstate="collapsed" desc="perus kamaa piilossa">
        init3();
        Object lista = tulostaConst.invoke(7);
//        String tulostus = toString2.invokeOn(lista);
//        String[] rivit = tulostus.replace("\r\n", "\n").split("\n");
//        String syote = "Tuloslista lista = new Tuloslista(5);\n"
//                + "lista.toString();";
//        for (String string : rivit) {
//            Assert.assertTrue("Seuraavalla syötteellä: " + syote + " tulisi jokaisen rivin olla: \"Uolevi 0\"", string.contains("Uolevi"));
//            Assert.assertTrue("Seuraavalla syötteellä: " + syote + " tulisi jokaisen rivin olla: \"Uolevi 0\"", string.contains("0"));
//            
//        }
//        lisaaTulos.invokeOn(lista, "Jarmo", 20);
//        lisaaTulos.invokeOn(lista, "Pekka", 200);
//        lisaaTulos.invokeOn(lista, "Arto", 40000);
//        lisaaTulos.invokeOn(lista, "Arto", 4000);
//        lisaaTulos.invokeOn(lista, "Matti", 35000);
//        lisaaTulos.invokeOn(lista, "Kaarlo", 30);

//        syote = "Tuloslista lista = new Tuloslista(5);\n"
//                + "lista.lisaaTulos(\"Jarmo\", 20);\n"
//                + "lista.lisaaTulos(\"Pekka\", 200);\n"
//                + "lista.lisaaTulos(\"Arto\", 40000);\n"
//                + "lista.lisaaTulos(\"Arto\", 4000);\n"
//                + "lista.lisaaTulos(\"Matti\", 35000);\n"
//                + "lista.lisaaTulos(\"Matti\", 30);\n"
//                + "lista.toString();";
//        tulostus = toString2.invokeOn(lista);
//        rivit = tulostus.replace("\r\n", "\n").split("\n");
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi ensimmäisellä rivillä lukea: \"Arto 40000\": " + syote, rivit[0].contains("Arto") && rivit[0].contains("40000"));
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi toisella rivillä lukea: \"Matti 35000\": " + syote, rivit[1].contains("Matti") && rivit[1].contains("35000"));
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi 3. rivillä lukea: \"Arto 4000\": " + syote, rivit[2].contains("Arto") && rivit[2].contains("4000"));
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi 4. rivillä lukea: \"Pekka 200\": " + syote, rivit[3].contains("Pekka") && rivit[3].contains("200"));
//        Assert.assertTrue("Seuraavalla syöteellä, tulisi 5. rivillä lukea: \"Matti 30\": " + syote, rivit[4].contains("Matti") && rivit[4].contains("30"));


//        Assert.assertFalse("Seuraavalla syötteellä tulisi lista.paaseeListalle(2) palauttaa false: \n" + syote + "lista.paaseeListalle(20);", paaseeListalle.invokeOn(lista, 2));
//        Assert.assertTrue("Seuraavalla syötteellä tulisi lista.paaseeListalle(500000) palauttaa true: \n" + syote + "lista.paaseeListalle(500000);", paaseeListalle.invokeOn(lista, 500000));
        //</editor-fold>

        //public void tallenna(String tiedostonimi)
        MethodRef1<Object, Void, String> lataaFilu = Reflex.reflect(TULOSLISTA).method("lataa").returningVoid().taking(String.class);
        if (!lataaFilu.exists()) {
            Assert.fail("olethan luonut metodin \"public void lataa(String tiedostonimi)\" luokkaan Tuloslista");
        }

        File tmp = null;
        String polku = "";
        try {
            tmp = File.createTempFile("tmp", "txt");
            tmp.deleteOnExit();
            polku = tmp.getPath();
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(tmp), "UTF-8");
            out.append("7\nPekka\n40000\nJarmo\n3999\nMatti\n3500\nPekka\n200\nArto\n40\nKaarlo\n30\nMika\n-50\n");
            out.flush();
            out.close();
        } catch (IOException ex) {
            Assert.fail("Testitiedoston kirjoittaminen ei onnistu. Tähän ei olisi pitänyt päätyä!\n" + ex);
        }
        lataaFilu.invokeOn(lista, polku);
        File tehtavassa;
        String data = "7\nPekka\n40000\nJarmo\n3999\nMatti\n3500\nPekka\n200\nArto\n40\nKaarlo\n30\nMika\n-50\n";
        String syote = "Tuloslista lista = new Tuloslista(5);\n"
                + "lista.lataa(\"" + polku + "\");\n";


        init3();

        try {
            lataaFilu.invokeOn(lista, polku);
        } catch (Exception e) {
            Assert.fail("Tiedoston lataaminen heitti virheen komennolla: " + syote + "kun tiedostossa oli " + data + "Lataamisen tuottama exception: " + e);
        }
        String tulos = toString2.invokeOn(lista);

        String[] rivit = tulos.replace("\r\n", "\n").trim().split("\n");
//        int i = 0;
//        for (int i = 0; i < rivit.length; i++) {
//            System.out.println(++i +"--" +string);
//            String[] pala = rivit[i].split(" ");
//            System.out.println("Assert.assertTrue(\"Rivillä " + i + " Tulisi lukea: \\\"" + rivit[i] + "\\\" mutta siinä oli: \"+" + "rivit[" + i + "]" + ", rivit[" + i + "].contains(\"" + pala[0] + "\") && rivit[" + i + "].contains(\"" + pala[1] + "\"));");
//        }

        Assert.assertEquals("Seuraavalla tulisi toString palauttaa 7 riviä tilastoja: " + syote + "Tiedostoon tallennettu data: \n"
                + "" + data, 7, rivit.length);

        Assert.assertTrue("Rivillä 0 Tulisi lukea: \"Pekka 40000\" mutta siinä oli: " + rivit[0], rivit[0].contains("Pekka") && rivit[0].contains("40000"));
        Assert.assertTrue("Rivillä 1 Tulisi lukea: \"Jarmo 3999\" mutta siinä oli: " + rivit[1], rivit[1].contains("Jarmo") && rivit[1].contains("3999"));
        Assert.assertTrue("Rivillä 2 Tulisi lukea: \"Matti 3500\" mutta siinä oli: " + rivit[2], rivit[2].contains("Matti") && rivit[2].contains("3500"));
        Assert.assertTrue("Rivillä 3 Tulisi lukea: \"Pekka 200\" mutta siinä oli: " + rivit[3], rivit[3].contains("Pekka") && rivit[3].contains("200"));
        Assert.assertTrue("Rivillä 4 Tulisi lukea: \"Arto 40\" mutta siinä oli: " + rivit[4], rivit[4].contains("Arto") && rivit[4].contains("40"));
        Assert.assertTrue("Rivillä 5 Tulisi lukea: \"Kaarlo 30\" mutta siinä oli: " + rivit[5], rivit[5].contains("Kaarlo") && rivit[5].contains("30"));
        Assert.assertTrue("Rivillä 6 Tulisi lukea: \"Mika -50\" mutta siinä oli: " + rivit[6], rivit[6].contains("Mika") && rivit[6].contains("-50"));



    }
}