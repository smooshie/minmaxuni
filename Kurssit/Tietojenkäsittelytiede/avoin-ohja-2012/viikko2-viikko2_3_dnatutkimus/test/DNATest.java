/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.helsinki.cs.tmc.edutestutils.MockInOut;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import fi.helsinki.cs.tmc.edutestutils.Reflex.ClassRef;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef0;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef1;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author kviiri
 */
public class DNATest {

    private String luokanNimi = "DNA";
    private MethodRef0 haePituus;
    private String tiedostonNimi = "dna.txt";

    public DNATest() {
    }

    private void init1() {

        if (!Reflex.reflect(luokanNimi).constructor().taking(String.class).exists()) {
            fail("Luokalta " + luokanNimi + " puuttuu tehtävän vaatima konstruktori public DNA(String tiedostonNimi).");
        }

        haePituus = Reflex.reflect(luokanNimi).method("haePituus").returning(int.class).takingNoParams();
        if (!haePituus.exists()) {
            fail("Luokasta " + luokanNimi + " puuttuu tehtävän vaatima metodi public int haePituus().");
        }

    }
    private MethodRef1<Object, ArrayList, String> haeOsanKohdat;

    private void init2() {
        init1();
        haeOsanKohdat = Reflex.reflect(luokanNimi).method("haeOsanKohdat").returning(ArrayList.class).taking(String.class);
        if (!haeOsanKohdat.exists()) {
            fail("Luokasta " + luokanNimi + " puuttuu tehtävän vaatima metodi public ArrayList<Integer> haeOsanKohdat(String osa).");
        }

    }
    private MethodRef1<Object, TreeSet, Integer> haeOsat;

    private void init3() {
        init2();
        haeOsat = Reflex.reflect(luokanNimi).method("haeOsat").returning(TreeSet.class).taking(int.class);
        if (!haeOsat.exists()) {
            fail("Luokasta " + luokanNimi + " puuttuu tehtävän vaatima metodi public TreeSet<String> haeOsat(int pituus).");
        }
    }
    private MethodRef1<Object, TreeMap, Integer> haeOsienMaarat;

    private void init4() {
        init3();
        haeOsienMaarat = Reflex.reflect(luokanNimi).method("haeOsienMaarat").returning(TreeMap.class).taking(int.class);
        if (!haeOsienMaarat.exists()) {
            fail("Luoaksta " + luokanNimi + " puuttuu tehtävän vaatima metodi public TreeMap<String, Integer>"
                    + "haeOsienMaarat(int pituus).");
        }
    }

    @Test
    @Points("2.3.1")
    public void konstruktoriJaPituusTest() throws Throwable {
        init1();
        Object dnaOlio = Reflex.reflect(luokanNimi).constructor().taking(String.class).invoke(tiedostonNimi);
        Object saatuPituus = haePituus.invokeOn(dnaOlio);
        if ((Integer) saatuPituus != 4639221) {
            fail(tiedostonNimi + " -tiedoston DNA-ketjun pituuden tulisi olla " + 4639221 + ". "
                    + "haePituus-metodisi mukaan se oli " + saatuPituus + ".");
        }

    }

    @Test
    @Points("2.3.2")
    public void haeOsanKohdatTest() throws Throwable {
        init2();
        Object dnaOlio = Reflex.reflect(luokanNimi).constructor().taking(String.class).invoke(tiedostonNimi);
        ArrayList<Integer> saadutOsanKohdat = haeOsanKohdat.invokeOn(dnaOlio, "ACCTCTCCA");
        ArrayList<Integer> expected = new ArrayList<Integer>() {

            {
                add(267756);
                add(1772869);
                add(1984914);
                add(3041202);
                add(3994701);
            }
        };
        tarkistaListat(saadutOsanKohdat, expected, "ACCTCTCCA");

        saadutOsanKohdat = haeOsanKohdat.invokeOn(dnaOlio, "GACTACACCA");
        expected = new ArrayList<Integer>() {

            {
                add(1008245);
                add(1319509);
            }
        };
        tarkistaListat(saadutOsanKohdat, expected, "GACTACACCA");

        saadutOsanKohdat = haeOsanKohdat.invokeOn(dnaOlio, "ATCGGCTTCAGA");
        expected = new ArrayList<Integer>() {

            {
                add(4303585);
            }
        };
        tarkistaListat(saadutOsanKohdat, expected, "ATCGGCTTCAGA");
    }

    @Test
    @Points("2.3.3")
    public void haeOsatTest() throws Throwable {
        init3();
        Object dnaOlio = Reflex.reflect(luokanNimi).constructor().taking(String.class).invoke(tiedostonNimi);
        TreeSet<String> saadutOsat = haeOsat.invokeOn(dnaOlio, 2);
        TreeSet<String> expected = new TreeSet<String>() {{
            add("AA");
            add("AC");
            add("AG");
            add("AT");
            add("CA");
            add("CC");
            add("CG");
            add("CT");
            add("GA");
            add("GC");
            add("GG");
            add("GT");
            add("TA");
            add("TC");
            add("TG");
            add("TT");
        }};
        if(!saadutOsat.equals(expected)) {
            fail("haeOsat-metodisi antoi väärän joukon parametrillä 2 - haluttiin joukko " + expected + " mutta saatiin "
                    + "joukko " + saadutOsat + ".");
        }
        
        saadutOsat = haeOsat.invokeOn(dnaOlio, 6);
        if(saadutOsat.size() != 4096) {
            fail("haeOsat-metodisi antoi väärän joukon parametrillä 6 - haluttiin 4096 alkiota, mutta saatiin " +
                    saadutOsat.size());
        }
        
        saadutOsat = haeOsat.invokeOn(dnaOlio, 8);
        if(saadutOsat.size() != 65363) {
            fail("haeOsat-metodisi antoi väärän joukon parametrillä 8 - haluttiin 65363 alkiota, mutta saatiin " +
                    saadutOsat.size());
        }
        
    }

    private void tarkistaListat(ArrayList<Integer> saadutOsanKohdat, ArrayList<Integer> expected, String osa) {
        Collections.sort(saadutOsanKohdat);
        if (!saadutOsanKohdat.equals(expected)) {
            fail("Kun haettiin osalla " + osa + ", odotettiin tulosta " + expected + ". Koodisi tuotti " + saadutOsanKohdat);
        }
    }
    
     
    @Test
    @Points("2.3.4")
    public void haeOsienMaaratTest() throws Throwable {
        init4();
        Object dnaOlio = Reflex.reflect(luokanNimi).constructor().taking(String.class).invoke(tiedostonNimi);
        TreeMap<String, Integer> osienMaarat = haeOsienMaarat.invokeOn(dnaOlio, 2);
        if(osienMaarat.size() != 16) {
            fail("haeOsienMaarat(2) pitäisi palauttaa 16-alkioinen TreeMap - nyt palautettuja alkioita oli " + osienMaarat.size());
        }
        if(!osienMaarat.containsKey("GA")) {
            fail("haeOsienMaarat(2) pitäisi palauttaa TreeMap, jossa on yhtenä avaimena merkkijono \"GA\". Tämä avain puuttui.");
        }
        if(osienMaarat.get("GA") != 267234) {
            fail("haeOsienMaarat(2) - metodistasi saadun TreeMapin mukaan \"GA\" esiintyy " + osienMaarat + " kertaa. Oikea"
                    + " luku on 267234.");
        }
        osienMaarat = haeOsienMaarat.invokeOn(dnaOlio, 8);
        if(osienMaarat.size() != 65363) {
            fail("haeOsienMaarat(8) pitäisi palauttaa 65363-alkioinen TreeMap - nyt palautettuja alkioita oli " + osienMaarat.size());
        }
        if(!osienMaarat.containsKey("GCAGAGAT")) {
            fail("haeOsienMaarat(8) pitäisi palauttaa TreeMap, jossa on yhtenä avaimena merkkijono \"GCAGAGAT\". Tämä avain puuttui.");
        }
        if(osienMaarat.get("GCAGAGAT") != 100) {
            fail("haeOsienMaarat(8) - metodistasi saadun TreeMapin mukaan \"GCAGAGAT\" esiintyy " + osienMaarat + " kertaa. Oikea"
                    + " luku on 100.");
        }
        if(!osienMaarat.containsKey("GCAAAACG")) {
            fail("haeOsienMaarat(8) pitäisi palauttaa TreeMap, jossa on yhtenä avaimena merkkijono \"GCAAAACG\". Tämä avain puuttui.");
        }
        if(osienMaarat.get("GCAAAACG") != 224) {
            fail("haeOsienMaarat(8) - metodistasi saadun TreeMapin mukaan \"GCAAAACG\" esiintyy " + osienMaarat + " kertaa. Oikea"
                    + " luku on 224.");
        }
        if(!osienMaarat.containsKey("AAGGAATA")) {
            fail("haeOsienMaarat(8) pitäisi palauttaa TreeMap, jossa on yhtenä avaimena merkkijono \"GCAGAGAT\". Tämä avain puuttui.");
        }
        if(osienMaarat.get("AAGGAATA") != 74) {
            fail("haeOsienMaarat(8) - metodistasi saadun TreeMapin mukaan \"AAGGAATA\" esiintyy " + osienMaarat + " kertaa. Oikea"
                    + " luku on 74.");
        }
    }
    
    @Test
    @Points("2.3.5")
    public void haeLausekkeellaTest() throws Throwable {
        init1();
        Object dnaOlio = Reflex.reflect(luokanNimi).constructor().taking(String.class).invoke(tiedostonNimi);
        Class c = dnaOlio.getClass();
        MethodRef1<Object, Void, Object> lausekkeet = Reflex.reflect("Main").staticMethod("lausekkeet").returningVoid().taking(c);
        MockInOut mio = new MockInOut("");
        lausekkeet.invoke(dnaOlio);
        String[] output = mio.getOutput().split("\n");
        mio.close();
        if(output.length < 5) {
            fail("Lausekkeet-metodisi tulostaa liian vähän rivejä! Metodin tulisi tulostaa viisi riviä.");
        }
        //Tekemällä oppii, tekemättä ei opi - tee regexit äläkä kopioi vastauksia täältä! --kviiri
        String[] expected = { "{1541917=ACTGCGCTAA, 1615643=CACTGAAAAG, 3088678=ACTGCGCTAA, 3902827=CACTGAAAAG, 4231217=ACTGCGCTAA}",
            "{3192573=AGCCGGGGATTTT, 3217273=ATCCGGGGTTTTT, 4393294=AGCCGGGGCTTTT}",
            "{952873=CAACCCAACCAACAAACCAC, 1793973=AACAACCCCAACAAAACCAA, 2894645=CACAACAAAACCAACCAACA, 4245479=CACCCCACAAAACACACAAA, 4578118=AACCCACAAACACCAACAAA}",
            "{2908967=ACACACAGACACATAC, 3723499=AGATACAGAGACACAG}",
            "{11992=AAAAATTTTT, 59172=CCCCCAAAAA, 160643=GGGGGAAAAA, 190789=CCCCCTTTTT, 303237=GGGGGTTTTT, 311742=CCCCCTTTTT, 354014=TTTTTCCCCC, 354129=AAAAAGGGGG, 457952=AAAAAGGGGG, 528937=AAAAACCCCC, 573609=CCCCCAAAAA, 645977=CCCCCTTTTT, 956601=GGGGGAAAAA, 1064135=TTTTTAAAAA, 1073279=TTTTTAAAAA, 1092570=TTTTTGGGGG, 1184457=GGGGGTTTTT, 1197431=TTTTTGGGGG, 1339751=TTTTTAAAAA, 1410460=TTTTTGGGGG, 1419669=CCCCCGGGGG, 1420469=AAAAATTTTT, 1503447=AAAAAGGGGG, 1630379=TTTTTAAAAA, 1728391=TTTTTCCCCC, 1992692=TTTTTAAAAA, 2083376=AAAAACCCCC, 2102454=AAAAATTTTT, 2288280=TTTTTAAAAA, 2307200=CCCCCGGGGG, 2516026=AAAAACCCCC, 2528213=AAAAACCCCC, 2538891=TTTTTAAAAA, 2620967=TTTTTCCCCC, 2785047=CCCCCAAAAA, 2797057=AAAAATTTTT, 2812773=TTTTTAAAAA, 3106781=TTTTTAAAAA, 3165981=CCCCCAAAAA, 3246286=CCCCCAAAAA, 3457578=CCCCCAAAAA, 3530401=CCCCCAAAAA, 3724153=GGGGGTTTTT, 3733718=TTTTTAAAAA, 3947809=TTTTTAAAAA, 3948086=GGGGGTTTTT, 4077090=AAAAATTTTT, 4103954=GGGGGTTTTT, 4368147=TTTTTCCCCC, 4370297=GGGGGTTTTT, 4389841=TTTTTGGGGG, 4392646=GGGGGAAAAA, 4425334=TTTTTAAAAA}"};
        
        for(int i = 0; i < expected.length; i++) {
            if(!expected[i].equals(output[i])) {
                fail("Lausekkeet-metodisi tuottaa " + (i+1) + ". kohtaan väärän tuloksen.");
            }
        }
    }
}
