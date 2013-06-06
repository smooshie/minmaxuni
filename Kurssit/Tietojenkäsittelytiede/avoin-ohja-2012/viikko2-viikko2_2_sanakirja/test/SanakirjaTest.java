
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import fi.helsinki.cs.tmc.edutestutils.Reflex.ClassRef;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef0;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef1;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef2;
import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jarmo
 */
public class SanakirjaTest {

    private ClassRef<Object> sana;
    private Class sanakirja;
    private String luokannimi = "Sanakirja";
    private MethodRef2<Object, Void, String, String> lisaaSanakirjaan;
    private MethodRef1<Object, String, String> kaannaSana;
    private MethodRef0 sanojenMaara;

    @Before
    public void setUp() throws Throwable {
//        sanakirja = ReflectionUtils.findClass("Sanakirja");
        sana = Reflex.reflect(luokannimi);
    }

    private void init() {

        if (!Reflex.reflect("Sanakirja").constructor().takingNoParams().exists()) {
            fail("Olethan luonut metodin \"public void lisaaSana(String sana, String kaannos)\" luokkaan \"Sanakirja\"");
        }

        lisaaSanakirjaan = Reflex.reflect(luokannimi).method("lisaaSana").returningVoid().taking(String.class, String.class);
//        lisaaSanakirjaan.requireExists();

        if (!lisaaSanakirjaan.exists()) {
            fail("Olethan toteuttanut metodin \"public void lisaaSana(String sana, String kaannos)\" luokkaan \"Sanakirja\"");
        }

        kaannaSana = Reflex.reflect(luokannimi).method("kaannaSana").returning(String.class).taking(String.class);

        if (!kaannaSana.exists()) {
            fail("Olethan toteuttanut metodin \"public void lisaaSana(String sana, String kaannos)\" luokkaan \"Sanakirja\"");
        }

        sanojenMaara = Reflex.reflect(luokannimi).method("sanojenMaara").returning(int.class).takingNoParams();
        if (!sanojenMaara.exists()) {
            fail("Olethan toteuttanut metodin \"public int sanojenMaara()\" luokkaan \"Sanakirja\"");
        }
    }

    @Test
    @Points("2.2.1")
    public void testataanLuokanOlemassaOloa() throws Throwable {
        init();
        Object sanis = null;

        sanis = Reflex.reflect(luokannimi).constructor().takingNoParams().invoke();

        lisaaSanakirjaan.invokeOn(sanis, "apina", "monkey");

        int sanoja = (Integer) sanojenMaara.invokeOn(sanis);

        assertTrue("Kun sanakirjaan on lisätty yksi sana, tulisi sanakirjassa olla yksi sana", 1 == sanoja);
        lisaaSanakirjaan.invokeOn(sanis, "Talo", "House");
        sanoja = (Integer) sanojenMaara.invokeOn(sanis);
        assertTrue("Kun sanakirjaan on lisätty kaksi sanaparia, tulisi sanakijassa olla kaksi sanaa", 2 == sanoja);
        String syote = "Sanakirja suomestaEnglanniksi = new Sanakirja();\n"
                + "suomestaEnglanniksi.lisaaSana(\"apina\", \"monkey\");\n"
                + "suomestaEnglanniksi.kaannaSana(\"apina\"));";
        String saatu = kaannaSana.invokeOn(sanis, "apina");
        assertEquals("Seuraavalla syötteellä tulisi \"apina\"n käännöksen olla \"monkey\":\n"
                + "" + syote, "monkey", saatu);

        String saatu2 = kaannaSana.invokeOn(sanis, "tätaSanaaSanakirjassaEiOle");
        assertTrue("Kun sana käännetään ja sitä ei löydy, tulee palauttaa tyhjä merkkijono", "".equals(saatu2));

    }
    MethodRef1 poistaSana;
    MethodRef0 tyhjenna;
    MethodRef0 tulostaSisalto;

    private void init2() {
        init();
        poistaSana = Reflex.reflect(luokannimi).method("poistaSana").returningVoid().taking(String.class);
        if (!poistaSana.exists()) {
            fail("Olethan luonut metodin \"public void poistaSana(String sana)\" luokkaan \"Sanakirja\"");
        }

        tyhjenna = Reflex.reflect(luokannimi).method("tyhjenna").returningVoid().takingNoParams();
        if (!poistaSana.exists()) {
            fail("Olethan luonut metodin \"public void tyhjenna()\" luokkaan \"Sanakirja\"");
        }
        tulostaSisalto = Reflex.reflect(luokannimi).method("tulostaSisalto").returningVoid().takingNoParams();
        if (!tulostaSisalto.exists()) {
            fail("Olethan luonut metodin \"public void tulostaSisalto()\"");
        }
    }

    @Test
    @Points("2.2.2")
    public void sanakirjaanLisaaToimintojaTest() throws Throwable {
        Object sanis = Reflex.reflect(luokannimi).constructor().takingNoParams().invoke();

        init2();
        lisaaSanakirjaan.invokeOn(sanis, "apina", "monkey");
        lisaaSanakirjaan.invokeOn(sanis, "talo", "house");
        lisaaSanakirjaan.invokeOn(sanis, "tiedosto", "file");
        lisaaSanakirjaan.invokeOn(sanis, "linux", "linux");
        lisaaSanakirjaan.invokeOn(sanis, "MOOC", "is_the_best");
        lisaaSanakirjaan.invokeOn(sanis, "helppo_nakki", "easy_sausage");
        lisaaSanakirjaan.invokeOn(sanis, "pala_kakkua", "piece_of_cake");
        String syote = "Sanakirja suomestaEnglanniksi = new Sanakirja();\n"
                + "suomestaEnglanniksi.lisaaSana(\"apina\", \"monkey\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"talo\", \"house\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"linux\", \"linux\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"MOOC\", \"is_the_best\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"helppo_nakki\", \"easy_sausage\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"pala_kakkua\", \"piece_of_cake\");\n";

        assertEquals("Seuraavalla syötteellä metodin sanojenMaara tulisi palauttaa 7. Se kuitenkin oli: " + sanojenMaara.invokeOn(sanis) + "\n" + syote, 7, sanojenMaara.invokeOn(sanis));
        if (true) {
            OutputStream outs = System.out;
            PrintStream dos = new PrintStream(outs);
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            System.setOut(new PrintStream(ba));

            tulostaSisalto.invokeOn(sanis);

            System.setOut(dos);
//        System.out.println(ba.toString());
            String[] sanoja = ba.toString().replaceAll("\r\n", "\n").trim().split("\n");
            String[] pitaisiOlla = {"MOOC", "is_the_best", "apina", "monkey", "helppo_nakki",
                "easy_sausage", "linux", "linux", "pala_kakkua", "piece_of_cake", "talo", "house", "tiedosto", "file"};
            StringBuilder stb = new StringBuilder();
            ArrayList<String> arrraylist = new ArrayList<String>();
            for (int i = 0; i < sanoja.length; i++) {
                String[] rivi = sanoja[i].split("=");
                if (rivi.length > 2) {
                    fail("tulostaSanat metodin tulisi tulostaa sanat '='-merkillä eriteltyinä. Merkin kummallakin puolella tulisi olla sana. Tässä käytettiin syötettä: \n" + syote);
                }
                arrraylist.add(rivi[0]);
                arrraylist.add(rivi[1]);
//            stb.append("---").append(rivi[0]).append("----").append(rivi[1]).append("----");

////            System.out.println("suomestaEnglanniksi.lisaaSana(\""+rivi[0]+"\", \""+rivi[1]+"\")");
////            System.out.print("\"" + rivi[0] + "\", \"" + rivi[1] + "\", ");
//           
            }
            String sanojaListassa = stb.toString();
            for (String string : pitaisiOlla) {

                if (!arrraylist.remove(string)) {
                    fail("Sanan " + string + " pitäisi olla \"suomestaEnglanniksi.tulostaSisalto();\" -metodin tulostuksessa.");
                }
            }

            if (arrraylist.size() != 0) {
                fail("\"suomestaEnglanniksi.tulostaSisalto();\" - tulostuksessa tulisi olla vain syötteessä sinne lisätyt sanat.\n"
                        + "" + syote + "\n"
                        + "nyt siinä kuitenkin oli myös " + arrraylist.toString());
            }

        }
        try {
            poistaSana.invokeOn(sanis, "apina");
        } catch (Exception e) {
            fail("Sanan poisto tuotti virheen: " + e + "\n"
                    + "Sanakirjaan oli lisätty sanat seuraavalla komennolla: " + syote);
        }
        OutputStream outs2 = System.out;
        PrintStream dos2 = new PrintStream(outs2);
        ByteArrayOutputStream ba2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(ba2));

        tulostaSisalto.invokeOn(sanis);

        System.setOut(dos2);
        String tulos2 = ba2.toString();
        if (tulos2.contains("apina")) {
            fail("Kun sanakirjasta poistetaan sana, ei sen pitäisi enää olla tulostuksessa: " + syote + "suomestaEnglanniksi.poistaSana(\"apina\");");
        }

        tyhjenna.invokeOn(sanis);
        int sanoja = (Integer) sanojenMaara.invokeOn(sanis);
        assertTrue("\"tyhjenna()\" -metodin tulisi tyhjentää sanalista.", 0 == sanoja);
        File tr = File.createTempFile("testi", ".txt");
    }
    MethodRef1<Object, Void, String> tallenna;

    private void init3() {
        init2();
        tallenna = Reflex.reflect(luokannimi).method("tallenna").returningVoid().taking(String.class);
        if (!tallenna.exists()) {
            fail("Olethan luonut methodin \"public void tallenna(String tiedostonimi)\"");
        }

    }

    @Test
    @Points("2.2.3")
    public void tallentavaSanakirjaTallentaaKayttajanAntamaanTiedostoon() throws Throwable {

        Object sanis = Reflex.reflect(luokannimi).constructor().takingNoParams().invoke();

        init3();
        lisaaSanakirjaan.invokeOn(sanis, "apina", "monkey");
        lisaaSanakirjaan.invokeOn(sanis, "talo", "house");
        lisaaSanakirjaan.invokeOn(sanis, "tiedosto", "file");
        lisaaSanakirjaan.invokeOn(sanis, "linux", "linux");
        lisaaSanakirjaan.invokeOn(sanis, "MOOC", "is_the_best");
        lisaaSanakirjaan.invokeOn(sanis, "helppo_nakki", "easy_sausage");
        lisaaSanakirjaan.invokeOn(sanis, "pala_kakkua", "piece_of_cake");
        String syote = "Sanakirja suomestaEnglanniksi = new Sanakirja();\n"
                + "suomestaEnglanniksi.lisaaSana(\"apina\", \"monkey\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"talo\", \"house\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"linux\", \"linux\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"MOOC\", \"is_the_best\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"helppo_nakki\", \"easy_sausage\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"pala_kakkua\", \"piece_of_cake\");\n";
//                + "suomestaEnglanniksi.lisaaSana(\"hattu\", \"hat\");\n"
//                + "suomestaEnglanniksi.lisaaSana(\"asd\", \"asd\");\n"
//                + "suomestaEnglanniksi.lisaaSana(\"paikallinen\", \"local\");\n"
//                + "suomestaEnglanniksi.lisaaSana(\"keks\", \"cookie\");\n";
        File tmp = File.createTempFile("TestiFile", "txt");
        String filu = tmp.getPath();
        tmp.deleteOnExit();
        String tiedNimi = filu + System.currentTimeMillis() + "__TMC";
        String syote2 = syote + "suomestaEnglanniksi.tallenna(\"" + filu + "\");";
        tallenna.invokeOn(sanis, tiedNimi);
        Scanner s = new Scanner(System.in);
        try {
            File f = new File(tiedNimi);
            f.deleteOnExit();
            s = new Scanner(f);
        } catch (Exception e) {
            fail("\"tallenna\" -metodin tallentaman tiedoston avaaminen ei onnistu. Virhe: " + e + "\nSyöte: " + syote2);
        }
        HashMap<String, String> hm = new HashMap<String, String>() {

            {
                put("MOOC", "is_the_best");
                put("apina", "monkey");
                put("helppo_nakki", "easy_sausage");
                put("linux", "linux");
                put("pala_kakkua", "piece_of_cake");
                put("talo", "house");
                put("tiedosto", "file");

            }
        };
        HashMap<String, String> hm2 = new HashMap<String, String>();
        while (s.hasNextLine()) {
            String[] rivi = s.nextLine().split(" ");
            if (rivi.length != 2) {
                fail("rivillä tulisi olla kaksi sanaa välillä erotettuna. Nyt rivillä oli: " + rivi);
            }
//            System.out.println("put(\"" + rivi[0] + "\", \"" + rivi[1] + "\");");
            hm2.put(rivi[0], rivi[1]);
        }
        ArrayList<String> ar = new ArrayList<String>();
        ArrayList<String> ar2 = new ArrayList<String>();
        for (String string : hm2.keySet()) {
//            System.out.println(hm.get(string));
//            System.out.println(hm2.get(string));
            if (hm2.get(string).equals(hm.get(string))) {
//                System.out.println("true");
//                hm.remove(string);
                ar.add(string);
//                hm2.remove(string);
                ar2.add(string);
            }
        }
        for (String string : ar) {
            hm.remove(string);
        }
        for (Iterator<String> it = ar2.iterator(); it.hasNext();) {
            String string = it.next();
            hm2.remove(string);
        }
        if (hm.size() != 0 || hm2.size() != 0) {
            fail("Tiedostossa ei ollut kaikkia lisättyjä sanoja. Siellä oli vielä seuraavat: " + hm2 + ", kokeile.% " + syote2);
        }
    }
    MethodRef1<Object, Void, String> lataa;

    private void init4() {
        init3();
        lataa = Reflex.reflect(luokannimi).method("lataa").returningVoid().taking(String.class);
        if (!lataa.exists()) {
            fail("Olethan toteuttanut metodin \"public void lataa(String tiedostonimi)\"");
        }

    }

    @Test
    @Points("2.2.4")
    public void tallentavaSanakirjaTallentaaLukeeKayttajanAntamanTiedoston() throws Throwable {

        Object sanis = Reflex.reflect(luokannimi).constructor().takingNoParams().invoke();

        init4();
        lisaaSanakirjaan.invokeOn(sanis, "apina", "monkey");
        lisaaSanakirjaan.invokeOn(sanis, "talo", "house");
        lisaaSanakirjaan.invokeOn(sanis, "tiedosto", "file");
        lisaaSanakirjaan.invokeOn(sanis, "linux", "linux");
        lisaaSanakirjaan.invokeOn(sanis, "MOOC", "is_the_best");
        lisaaSanakirjaan.invokeOn(sanis, "helppo_nakki", "easy_sausage");
        lisaaSanakirjaan.invokeOn(sanis, "pala_kakkua", "piece_of_cake");
        lisaaSanakirjaan.invokeOn(sanis, "hattu", "hat");
        lisaaSanakirjaan.invokeOn(sanis, "asd", "asd");
        lisaaSanakirjaan.invokeOn(sanis, "paikallinen", "local");
        lisaaSanakirjaan.invokeOn(sanis, "keksi", "cookie");
        String syote = "Sanakirja suomestaEnglanniksi = new Sanakirja();\n"
                + "suomestaEnglanniksi.lisaaSana(\"apina\", \"monkey\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"talo\", \"house\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"linux\", \"linux\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"MOOC\", \"is_the_best\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"helppo_nakki\", \"easy_sausage\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"pala_kakkua\", \"piece_of_cake\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"hattu\", \"hat\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"asd\", \"asd\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"paikallinen\", \"local\");\n"
                + "suomestaEnglanniksi.lisaaSana(\"keksi\", \"cookie\");\n";
        File tmp = null;

        try {
            tmp = File.createTempFile("tmp", "txt");
            tmp.deleteOnExit();
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(tmp), "UTF-8");
            out.append("apina monkey\ntalo house\nlinux linux\nMOOC is_the_best\nhelppo_nakki easy_sausage\npala_kakkua piece_of_cake\nhattu hat\nasd asd\npaikallinen local\nkeksi cookie\n");
            out.flush();
            out.close();
        } catch (IOException ex) {
            fail("Testitiedoston kirjoittaminen ei onnistu. Tähän ei olisi pitänyt päätyä!\n" + ex);
        }
        lataa.invokeOn(sanis, tmp.getPath());
        OutputStream outs2 = System.out;
        PrintStream dos2 = new PrintStream(outs2);
        ByteArrayOutputStream ba2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(ba2));

        tulostaSisalto.invokeOn(sanis);

        System.setOut(dos2);
        String tulos2 = ba2.toString();
        String[] tulostettu = tulos2.trim().replaceAll("\r\n", "\n").trim().split("\n");
        HashMap<String, String> hm = new HashMap<String, String>() {

            {
                put("MOOC", "is_the_best");
                put("apina", "monkey");
                put("asd", "asd");
                put("hattu", "hat");
                put("helppo_nakki", "easy_sausage");
                put("keksi", "cookie");
                put("linux", "linux");
                put("paikallinen", "local");
                put("pala_kakkua", "piece_of_cake");
                put("talo", "house");
                put("tiedosto", "file");

            }
        };
        String pitiOlla = "MOOC is_the_best\n"
                + "apina monkey\n"
                + "asd asd\n"
                + "hattu hat\n"
                + "helppo_nakki easy_sausage\n"
                + "keksi cookie\n"
                + "linux linux\n"
                + "paikallinen local\n"
                + "pala_kakkua piece_of_cake\n"
                + "talo house\n"
                + "tiedosto file\n";
        for (String string : tulostettu) {
            String[] rivi = string.split("=");
            if (!hm.containsKey(rivi[0])) {
                fail("Sanan " + rivi[0] + " olisi pitänyt tulla luetuksi tiedostosta"
                        + "Tiedoston sisältö: \n"
                        + pitiOlla);
            }
            if (!hm.get(rivi[0]).equals(rivi[1])) {
                fail("Luodusta tiedostosta pitäisi löytyä sana " + rivi[0] + " ja sillä pitäisi olla vastine " + hm.get(rivi[0]) + "\n"
                        + "Tiedoston sisältö: \n"
                        + pitiOlla);
            }
        }
    }

    @Test
    @Points("2.2.5")
    public void lauseenKaantamisTesti() throws Throwable {
        Object sanis = Reflex.reflect(luokannimi).constructor().takingNoParams().invoke();
        MethodRef1<Object, String, String> kaannaLause = Reflex.reflect(luokannimi).method("kaannaLause").returning(String.class).taking(String.class);
        if (!kaannaLause.exists()) {
            fail("Olethan toteuttanut metodin \"public String kaannaLause(String lause)\"");
        }
        init4();

        lisaaSanakirjaan.invokeOn(sanis, "this", "tämä");
        lisaaSanakirjaan.invokeOn(sanis, "program", "ohjelma");
        lisaaSanakirjaan.invokeOn(sanis, "works", "toimii");
        lisaaSanakirjaan.invokeOn(sanis, "very", "erittäin");
        lisaaSanakirjaan.invokeOn(sanis, "well", "hyvin");
        String syote = "Sanakirja englannistaSuomeksi = new Sanakirja();"
                + "englannistaSuomeksi.lisaaSana(\"this\", \"tämä\");"
                + "englannistaSuomeksi.lisaaSana(\"program\", \"ohjelma\");"
                + "englannistaSuomeksi.lisaaSana(\"works\", \"toimii\");"
                + "englannistaSuomeksi.lisaaSana(\"very\", \"erittäin\");"
                + "englannistaSuomeksi.lisaaSana(\"well\", \"hyvin\");";

        String ekakaannos = kaannaLause.invokeOn(sanis, "This program works very well");
        ekakaannos = ekakaannos.replaceAll(" ", "");
        assertTrue(ekakaannos, ekakaannos.equals("tämäohjelmatoimiierittäinhyvin"));
        lisaaSanakirjaan.invokeOn(sanis, "experience", "koe");
        lisaaSanakirjaan.invokeOn(sanis, "next", "seuraava");
        lisaaSanakirjaan.invokeOn(sanis, "big", "iso");
        lisaaSanakirjaan.invokeOn(sanis, "step", "askel");
        lisaaSanakirjaan.invokeOn(sanis, "computational", "laskennallisessa");
        lisaaSanakirjaan.invokeOn(sanis, "knowledge", "tiedossa");
        syote += "englannistaSuomeksi.lisaaSana(\"experience\", \"koe\");";
        syote += "englannistaSuomeksi.lisaaSana(\"next\", \"seuraava\");";
        syote += "englannistaSuomeksi.lisaaSana(\"big\", \"iso\");";
        syote += "englannistaSuomeksi.lisaaSana(\"step\", \"askel\");";
        syote += "englannistaSuomeksi.lisaaSana(\"computational\", \"laskennallisesa\");";
        syote += "englannistaSuomeksi.lisaaSana(\"knowledge\", \"tiedossa\");";
        String toinenKaannetty = kaannaLause.invokeOn(sanis, "experience the next big step in computational knowledge"); //wolframalpha.com
        assertTrue("Syötteellä " + syote + "\n"
                + "tulisi lauseen \"experience the next big step in computational knowledge\" käännöksen olla \"koe seuraava iso askel laskennallisessa tiedossa\" ", toinenKaannetty.replaceAll(" ", "").equals("koeseuraavaisoaskellaskennallisessatiedossa"));
    }
}
