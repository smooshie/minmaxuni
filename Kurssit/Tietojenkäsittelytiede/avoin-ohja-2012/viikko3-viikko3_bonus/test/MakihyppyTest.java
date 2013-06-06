import fi.helsinki.cs.tmc.edutestutils.MockInOut;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.*;
import static org.junit.Assert.fail;
import org.junit.Test;

@Points("b1 b2 b3")
public class MakihyppyTest {
	private static String ekaHyppaaja = "Mikael";

    @Test
    public void sovelluksessaEiHypataJosLopetetaanHeti() {
        MockInOut io = new MockInOut(this.ekaHyppaaja + "\n\nlopeta\n");
        try {
            Main.main(new String[]{""});
        } catch (Throwable t) {
            fail("Varmista että mäkihyppysimulaattorissasi luodaan vain yksi Scanner-olio ja että se käynnistyy Main-luokassa olevasta main-metodista. Toimiihan sovelluksesi vaikka yhtäkään hyppyä ei tehdä?\n"
                    + "Tapahtui poikkeus: " + t.getMessage());
        }

        String output = io.getOutput();
        if (output == null || output.length() < 10) {
            fail("Tulostaahan ohjelmasi jotain?");
        }

        if (output.contains("kierros")) {
            fail("Ethän hyppää kierroksia ennen kuin käyttäjä syöttää merkkijonon \"hyppaa\"?");
        }


        if (!output.contains("loppu")) {
            fail("Tulostathan kilpailun lopputulokset vaikka kilpailussa ei hypätä kertaakaan.");
        }
    }

    @Test
    public void sovellusToimiiYhdellaHyppaajallaJaKahdellaHypylla() {
        MockInOut io = new MockInOut(this.ekaHyppaaja + "\n\nhyppaa\nhyppaa\nlopeta\n");
        try {
            Main.main(new String[]{""});
        } catch (Throwable t) {
            fail("Varmista että mäkihyppysimulaattorissasi luodaan vain yksi Scanner-olio ja että se käynnistyy Main-luokassa olevasta main-metodista. Toimiihan sovelluksesi vaikka yhtäkään hyppyä ei tehdä?\n"
                    + "Tapahtui poikkeus: " + t.getMessage());
        }

        String output = io.getOutput();
        if (output == null || output.length() < 10) {
            fail("Tulostaahan ohjelmasi jotain?");
        }

        if (!containsInOrder(output, "1", "kierros", "2", "kierros")) {
            fail("Tulostaahan ohjelmasi kierroksen numeron ja kierros-tekstin?");
        }

        if (!containsInOrder(output, "1", "kierros", "1", "0")) {
            fail("Tulostaahan ohjelmasi hyppyjärjestyksessä numerot ja pisteet? (Alussa 0 pistettä)");
        }

        try {
            output = output.substring(0, output.indexOf("lopputulokset"));
        } catch (Throwable t) {
            fail("Kirjoitathan loppuun kilpailun lopputulokset.");
        }

        if (!onRivi(output, "pituus")) {
            fail("Tulostaahan sovelluksesi hypyn yhteydessä pituus-tekstin.");
        }
		
		if (onHaluttuMaara(output, "2. " + this.ekaHyppaaja, 2)) {
			fail("Hyppääjiä asetettiin yksi (" + this.ekaHyppaaja + ") ja kierroksia käytiin kaksi, mutta hyppääjiä " + this.ekaHyppaaja + " oli kilpailussa kaksi (kaksi kertaa \"2. " + this.ekaHyppaaja + "\").\nTämä voi johtua siitä, että käytät staattisia muuttujia, jotka pysyvät testistä toiseen samoina. Tällöin testin ensimmäisen osan aikana hyppäämään lähtenyt henkilö jää hyppääjäksi myös testin toiseen osaan.\nSaat tällöin helpoiten korjattua tämän alustamalla kaikki staattiset muuttujat main:ssa.");
		}

        if (!onHaluttuMaara(output, "pituus", 2)) {
            fail("Tulostathan hypyn pituuden kierrostulosten yhteydessä.");
        }

        if (!onRivi(output, "tuomaripisteet")) {
            fail("Tulostaahan sovelluksesi hypyn yhteydessä tuomaripisteet-tekstin.");
        }

        if (!onHaluttuMaara(output, "tuomaripisteet", 2)) {
            fail("Tulostathan hypyn tuomaripisteet kierrostulosten yhteydessä.");
        }

        if (!jokaisellaRivillaSamaMaaraNumeroita(output, "tuomaripisteet", 5)) {
            fail("Onhan tuomaripisteitä aina 5?");
        }
    }

    @Test
    public void sovellusToimiiKahdellaHyppaajallaJaKahdellaHypylla() {
        MockInOut io = new MockInOut("Mikael\nMika\n\nhyppaa\nhyppaa\nlopeta\n");
        try {
            Main.main(new String[]{""});
        } catch (Throwable t) {
            fail("Varmista että mäkihyppysimulaattorissasi luodaan vain yksi Scanner-olio ja että se käynnistyy Main-luokassa olevasta main-metodista. Toimiihan sovelluksesi vaikka yhtäkään hyppyä ei tehdä?\n"
                    + "Tapahtui poikkeus: " + t.getMessage());
        }

        String output = io.getOutput();
        if (output == null || output.length() < 10) {
            fail("Tulostaahan ohjelmasi jotain?");
        }

        if (!containsInOrder(io.getOutput(), "1", "kierros", "2", "kierros")) {
            fail("Tulostaahan ohjelmasi kierroksen numeron ja kierros-tekstin?");
        }

        try {
            output = output.substring(0, output.indexOf("lopputulokset"));
        } catch (Throwable t) {
            fail("Kirjoitathan loppuun kilpailun lopputulokset.");
        }

        if (!onHaluttuMaara(output, "pituus", 4)) {
            fail("Tulostathan hypyn pituuden jokaisen hyppääjän kierrostulosten yhteydessä.");
        }

        if (!onHaluttuMaara(output, "tuomaripisteet", 4)) {
            fail("Tulostathan hypyn tuomaripisteet jokaisen hyppääjän kierrostulosten yhteydessä.");
        }
    }

    @Test
    public void pistemaaraKasvaa() {
        MockInOut io = new MockInOut("Mikael\n\nhyppaa\nhyppaa\nhyppaa\nhyppaa\nhyppaa\nlopeta\n");
        try {
            Main.main(new String[]{""});
        } catch (Throwable t) {
            fail("Varmista että mäkihyppysimulaattorissasi luodaan vain yksi Scanner-olio ja että se käynnistyy Main-luokassa olevasta main-metodista. Toimiihan sovelluksesi vaikka yhtäkään hyppyä ei tehdä?\n"
                    + "Tapahtui poikkeus: " + t.getMessage());
        }

        if (!jokaisellaRivillaSamaMaaraNumeroita(io.getOutput(), "pituudet", 5)) {
            fail("Onhan lopussa jokaisen hypyn pituudet?");
        }

        String output = io.getOutput();
        try {
            output = output.substring(0, output.indexOf("lopputulokset"));
        } catch (Throwable t) {
            fail("Kirjoitathan loppuun kilpailun lopputulokset.");
        }

        List<String> rivit = rivitJoissa(output, "Mikael", "pistett");

        int pienin = -1;
        for (String rivi : rivit) {
            int numero = vikaNumero(rivi);
            if (numero == -1) {
                continue;
            }

            if (pienin <= numero) {
                pienin = numero;
            } else {
                fail("Tulostathan pistemäärän aina hyppyjärjestyksen yhteydessä." + rivit);
            }
        }

        if (pienin == -1) {
            fail("Tulostathan pistemäärän aina hyppyjärjestyksen yhteydessä.");
        }
    }

    @Test
    public void pituudetOikeillaValeilla() {
        StringBuilder sb = new StringBuilder("Matti\n\n");
        for (int i = 0; i < 100; i++) {
            sb.append("hyppaa\n");
        }
        sb.append("lopeta\n");

        MockInOut io = new MockInOut(sb.toString());
        try {
            Main.main(new String[]{""});
        } catch (Throwable t) {
            fail("Varmista että mäkihyppysimulaattorissasi luodaan vain yksi Scanner-olio ja että se käynnistyy Main-luokassa olevasta main-metodista. Toimiihan sovelluksesi vaikka yhtäkään hyppyä ei tehdä?\n"
                    + "Tapahtui poikkeus: " + t.getMessage());
        }

        if (!jokaisellaRivillaSamaMaaraNumeroita(io.getOutput(), "pituudet", 100)) {
            fail("Onhan lopussa jokaisen hypyn pituudet?");
        }

        if (!onHaluttuMaara(io.getOutput(), "pituus", 100)) {
            fail("Tulostetaanhan jokaisen hypyn yhteydessä hypyn pituus?");
        }

        Set<Integer> pituudet = new HashSet();
        List<String> pituusRivit = rivitJoissa(io.getOutput(), "pituus");
        for (String rivi : pituusRivit) {
            int vikaNumero = vikaNumero(rivi);
            if (vikaNumero < 60 || vikaNumero > 120) {
                fail("Onhan hyppyjesi pituudet aina välillä 60-120?");
            }
            pituudet.add(vikaNumero);
        }

        if (pituudet.size() < 30) {
            fail("Arvothan jokaisen hypyn pituuden väliltä 60-120?");
        }
    }

    @Test
    public void pisteetOikeillaValeilla() {
        StringBuilder sb = new StringBuilder("Matti\n\n");
        for (int i = 0; i < 100; i++) {
            sb.append("hyppaa\n");
        }
        sb.append("lopeta\n");

        MockInOut io = new MockInOut(sb.toString());
        try {
            Main.main(new String[]{""});
        } catch (Throwable t) {
            fail("Varmista että mäkihyppysimulaattorissasi luodaan vain yksi Scanner-olio ja että se käynnistyy Main-luokassa olevasta main-metodista. Toimiihan sovelluksesi vaikka yhtäkään hyppyä ei tehdä?\n"
                    + "Tapahtui poikkeus: " + t.getMessage());
        }

        if (!onHaluttuMaara(io.getOutput(), "tuomaripisteet", 100)) {
            fail("Tulostetaanhan jokaisen hypyn jälkeen tuomaripisteet?");
        }

        Set<Integer> pisteet = new HashSet();
        List<String> pisteRivit = rivitJoissa(io.getOutput(), "tuomaripisteet");
        for (String rivi : pisteRivit) {
            int vikaNumero = vikaNumero(rivi);
            if (vikaNumero < 10 || vikaNumero > 20) {
                fail("Onhan tuomaripisteet aina välillä 10-20?");
            }

            pisteet.add(vikaNumero);
        }

        if (pisteet.size() < 5) {
            fail("Arvothan tuomaripisteet aina väliltä 10-20?");
        }
    }

    @Test
    public void pisteetLasketaanOikein() {
        MockInOut io = new MockInOut("Mikael\n\nhyppaa\nhyppaa\nhyppaa\nlopeta\n");
        try {
            Main.main(new String[]{""});
        } catch (Throwable t) {
            fail("Varmista että mäkihyppysimulaattorissasi luodaan vain yksi Scanner-olio ja että se käynnistyy Main-luokassa olevasta main-metodista. Toimiihan sovelluksesi vaikka yhtäkään hyppyä ei tehdä?\n"
                    + "Tapahtui poikkeus: " + t.getMessage());
        }

        String output = io.getOutput();
        if (output == null || output.length() < 10) {
            fail("Tulostaahan ohjelmasi jotain?");
        }

        if (!containsInOrder(output, "1", "kierros", "2", "kierros")) {
            fail("Tulostaahan ohjelmasi kierroksen numeron ja kierros-tekstin?");
        }

        if (!containsInOrder(output, "1", "kierros", "1", "0")) {
            fail("Tulostaahan ohjelmasi hyppyjärjestyksessä numerot ja pisteet? (Alussa 0 pistettä)");
        }

        List<List<Integer>> tuomariPisteet = new ArrayList();
        List<String> pisteRivit = rivitJoissa(io.getOutput(), "tuomaripisteet");
        for (String rivi : pisteRivit) {
            tuomariPisteet.add(numerot(rivi));
        }

        for (List<Integer> pisteet : tuomariPisteet) {
            pisteet.remove(Collections.min(pisteet));
            pisteet.remove(Collections.max(pisteet));
        }

        List<String> pituusrivit = rivitJoissa(io.getOutput(), "pituus");
        List<Integer> pituudet = new ArrayList();
        for (String rivi : pituusrivit) {
            pituudet.add(vikaNumero(rivi));
        }

        int yhteispisteet = 0;
        for (List<Integer> pisteet : tuomariPisteet) {
            yhteispisteet += sum(pisteet);
        }

        yhteispisteet += sum(pituudet);



        try {
            output = output.substring(output.indexOf("lopputulokset"));
        } catch (Throwable t) {
            fail("Kirjoitathan loppuun kilpailun lopputulokset.");
        }

        List<Integer> lopputulokset = numerot(output);
        if (!lopputulokset.contains(yhteispisteet)) {
            fail("Laskeehan ohjelmasi lopputulosten pisteet oikein? Pisteet ovat hyppyjen ja tuomaripisteiden yhteissumma siten, että jokaisen kierroksen tuomariäänten huonointa ja parasta ei oteta huomioon.\nLoppupisteiden tulostus oli:\n" + lopputulokset + "\nTuomarien antamat pisteet olivat:\n" + pisteRivit);
        }
    }

    private int sum(Collection<Integer> luvut) {
        int sum = 0;
        for (int luku : luvut) {
            sum += luku;
        }

        return sum;
    }

    private List<String> rivitJoissa(String data, String... rivit) {
        List<String> palautettavatRivit = new ArrayList();
        Scanner sc = new Scanner(data);

        NextLine:
        while (sc.hasNextLine()) {
            String r = sc.nextLine();

            for (String rivi : rivit) {
                if (!r.contains(rivi)) {
                    continue NextLine;
                }
            }

            palautettavatRivit.add(r);
        }

        return palautettavatRivit;
    }

    private boolean onRivi(String data, String rivi) {
        Scanner sc = new Scanner(data);
        while (sc.hasNextLine()) {
            String r = sc.nextLine();
            if (r.contains(rivi)) {
                return true;
            }
        }

        return false;
    }

    private boolean onHaluttuMaara(String data, String haluttuSana, int haluttuMaara) {
        int lastIndex = 0;
        int count = 0;

        while (true) {

            lastIndex = data.indexOf(haluttuSana, lastIndex);
            if (lastIndex < 0) {
                break;
            }

            lastIndex += 1;
            count++;
        }

        return count == haluttuMaara;
    }

    private boolean jokaisellaRivillaSamaMaaraNumeroita(String data, String rivi, int haluttuMaara) {
        return jokaisellaRivillaSamaMaaraNumeroita(data, rivi, haluttuMaara, 0);
    }

    private boolean jokaisellaRivillaSamaMaaraNumeroita(String data, String rivi, int haluttuMaara, int okEro) {
        Scanner sc = new Scanner(data);
        while (sc.hasNextLine()) {
            String r = sc.nextLine();
            if (!r.contains(rivi)) {
                continue;
            }

            if (Math.abs(numeroita(r) - haluttuMaara) > okEro) {
                return false;
            }
        }

        return true;
    }

    private int numeroita(String rivi) {
        int num = 0;
        rivi = rivi.replaceAll("[^0-9]", " ");

        for (String elementti : rivi.split("\\s+")) {
            try {
                Integer.parseInt(elementti);
                num++;
            } catch (Exception e) {
            }
        }

        return num;
    }

    private List<Integer> numerot(String rivi) {
        List<Integer> num = new ArrayList();
        rivi = rivi.replaceAll("[^0-9]", " ");

        for (String elementti : rivi.split("\\s+")) {
            try {
                num.add(Integer.parseInt(elementti));
            } catch (Exception e) {
            }
        }

        return num;
    }

    private int vikaNumero(String rivi) {
        int vikaNumero = -1;

        rivi = rivi.replaceAll("[^0-9]", " ").trim();
        for (String s : rivi.split("\\s+")) {
            try {
                vikaNumero = Integer.parseInt(s);
            } catch (Exception e) {
            }
        }

        return vikaNumero;
    }

    private boolean containsInOrder(String data, String[]... args) {
        data = data.toLowerCase();
        for (int i = 0; i < args.length; i++) {
            String[] row = args[i];
            if (!containsInOrder(data, row)) {
                return false;
            }
        }

        return true;
    }

    private boolean containsInOrder(String data, String... args) {
        data = data.toLowerCase();
        int idx = 0;
        for (int i = 0; i < args.length; i++) {
            String element = args[i];
            element = element.toLowerCase();

            int nextIdx = data.indexOf(element, idx);

            if (nextIdx < 0) {
                return false;
            }

            idx = nextIdx;
        }

        return true;
    }
}