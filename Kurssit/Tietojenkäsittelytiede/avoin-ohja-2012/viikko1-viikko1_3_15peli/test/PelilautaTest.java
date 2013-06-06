
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import junit.framework.AssertionFailedError;
import org.junit.*;
import static org.junit.Assert.*;

public class PelilautaTest {

    private Object pelilauta;
    private Class clazz;
    private Object[] oletuslauta = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

    @Before
    public void setUp() {
        clazz = ReflectionUtils.findClass("Pelilauta");
        assertNotNull("Löytyyhän sinulta luokka Pelilauta?", clazz);
        try {
            Constructor konstruktori = ReflectionUtils.requireConstructor(clazz);
            pelilauta = ReflectionUtils.invokeConstructor(konstruktori);
        } catch (Throwable ex) {
            fail("Onhan luokalla Pelilauta parametritön konstruktori");
        }
    }
    @Points("1.3.1")
    @Test
    public void haeLuku() {
        List<Integer> haeLuvut = haeLuvut();

        assertArrayEquals("Toimiihan haeluku oikein", haeLuvut.toArray(), oletuslauta);
    }

    private List<Integer> haeLuvut() {
        Method metodi = haeMetodi(int.class, "haeLuku", int.class, int.class);
        ArrayList<Integer> luvut = new ArrayList<Integer>();
        for (int i = -5; i < 20; i++) {
            Object numero = ajaMetodi(metodi, i / 4, i % 4);
            int luku = Integer.parseInt(numero + "");
            if (luku != -1) {
                luvut.add(luku);
            }
        }
        assertEquals("Lukuja löytyi väärä määrä (mukaan luettuna tyhjä)", 16, luvut.size());
        return luvut;
    }
@Points("1.3.2")
    @Test
    public void toStringi() {
        haeNumerot();
    }
@Points("1.3.2")
    @Test
    public void toStringVastaaHaeLukua() {
        testaaLuvut();
    }

    private List<Integer> testaaLuvut() {
        List<Integer> toString = haeNumerot();
        List<Integer> haeLuku = haeLuvut();
        for (int i = 0; i < toString.size(); i++) {
            assertEquals("Varmista, että varmista että toString vastaa haeLuku metodin palauttamia arvoja, indeksissä [" + (i / 4) + "," + (i % 4), toString.get(i), haeLuku.get(i));
        }
        return toString;
    }

    private List<Integer> haeNumerot() {
        Method metodi = haeMetodi(String.class, "toString");
        String toString = ajaMetodi(metodi).toString();
        if (toString.contains("@")) {
            fail("toString-metodin ei pitäisi palauttaa @ merkkiä, oletahan varmasti korvannut toString metodin");
        }
        oletaMaara(toString, '+', 25);
        oletaMaara(toString, '|', 20);
        oletaMaara(toString, '-', 40);
        List<String> split = Arrays.asList(toString.split("\n"));
        assertEquals("toStringin pitäisi sisältää 9 riviä", 9, split.size());
        for (int i = 0; i < 5; i++) {
            assertEquals("rivi " + (i * 2 + 1) + " ei ole halutun muotoinen", "+--+--+--+--+", split.get(i * 2).trim());
        }
        List<Integer> numerot = new ArrayList<Integer>();
        for (String rivi : split) {
            for (String mjono : rivi.split(Pattern.quote("|"))) {

                if (mjono.equals("  ")) {
                    numerot.add(0);
                } else {
                    try {
                        numerot.add(Integer.parseInt(mjono.trim()));
                    } catch (Exception e) {
                    }
                }
            }
        }
        for (int i = -5; i < 25; i++) {
            if (i < 0 || i > 15) {
                assertFalse("Numeroa " + i + " ei saisi löytyä toStringistä", numerot.contains(i));
            } else {
                assertTrue("Numeron " + i + " pitäisi löytyä toStringistä", numerot.contains(i));
            }
        }
        return numerot;
    }
@Points("1.3.3")
    @Test
    public void ylosAlasOikeaVasen() {
        Method ylos = haeMetodi(void.class, "ylos");
        Method alas = haeMetodi(void.class, "alas");
        Method oikea = haeMetodi(void.class, "oikea");
        Method vasen = haeMetodi(void.class, "vasen");
        List<Integer> haeluvut = haeLuvut();
        assertArrayEquals("Eihän pelilauta muutu, jos ei "
                + "kutsuta mitään liikkumismetodeista tai uusiPeli-metodia", haeluvut.toArray(), haeLuvut().toArray());

        ajaMetodi(ylos);
        assertArrayEquals("laudan ei pitäisi muuttua kun kutsutaan ylos metodia", haeluvut.toArray(), haeLuvut().toArray());





        ajaMetodi(alas);
        List<Integer> ylosJalkeen = haeLuvut();
        assertNotSame("Muuttuuhan Pelilauta alas-metodin jälkeen?", haeluvut, ylosJalkeen);

        List<Integer> oletettu = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 13, 14, 15, 12);
        assertArrayEquals("Toimiihan alas-metodi oikein?", oletettu.toArray(), ylosJalkeen.toArray());

        ajaMetodi(ylos);
        assertArrayEquals("Laudan pitäisi olla palautettu takaisin alkuperäiseen, kun ollaan kutsuttu ensin alas() ja sitten ylos()",
                haeLuvut().toArray(), haeluvut.toArray());


        ajaMetodi(ylos);
        ajaMetodi(vasen);
        ajaMetodi(alas);
        ajaMetodi(alas);
        ajaMetodi(alas);

        ajaMetodi(alas);
        ajaMetodi(vasen);
        ajaMetodi(oikea);
        ajaMetodi(oikea);
        ajaMetodi(oikea);

        ajaMetodi(alas);
        ajaMetodi(oikea);
        ajaMetodi(ylos);
        ajaMetodi(ylos);
        ajaMetodi(ylos);

        ajaMetodi(ylos);
        ajaMetodi(oikea);
        ajaMetodi(vasen);
        ajaMetodi(vasen);
        ajaMetodi(vasen);

        oletettu = Arrays.asList(5, 1, 2, 3, 9, 6, 7, 4, 13, 10, 11, 8, 14, 15, 12, 0);
        assertArrayEquals("Toimiihan peli oikein, kun ollaan kutsuttu y v a a a  a v o o o a o y y y y o v v v", haeLuvut().toArray(), oletettu.toArray());
    }
@Points("1.3.4")
    @Test
    public void satunnaisenLaudanTestit() {
        Method uusiPeli = haeMetodi(void.class, "uusiPeli");
        ArrayList<List<Integer>> laudat = new ArrayList<List<Integer>>();
        for (int i = 0; i < 500; i++) {
            ajaMetodi(uusiPeli);
            assertFalse("Arvothan pelilaudan satunnaiseksi kutsuttaessa uusiPeli()-metodia", Arrays.equals(oletuslauta, haeLuvut().toArray()));
            assertFalse("Eihän uusiPeli tee samanlaista lautaa uudestaan", laudat.contains(haeLuvut()));
            laudat.add(haeLuvut());
            assertTrue("Peli ei ole ratkaistavissa:  \n" + i + pelilauta.toString(), voikoRatkaista());
        }

    }
@Points("1.3.5")
    @Test
    public void haeSiirrot() {
        Method uusipeli = haeMetodi(void.class, "uusiPeli");
        Method haeSiirrot = haeMetodi(int.class, "haeSiirrot");
        Method ylos = haeMetodi(void.class, "ylos");
        Method alas = haeMetodi(void.class, "alas");
        Method oikea = haeMetodi(void.class, "oikea");
        Method vasen = haeMetodi(void.class, "vasen");
        ajaMetodi(0, haeSiirrot);
        ajaMetodi(uusipeli);
        ajaMetodi(0, haeSiirrot);
        int maara = 0;
        Object[] vanha = haeLuvut().toArray();
        for (int i = 0; i < 100; i++) {
            ajaMetodi((Math.random() < 0.5 ? (Math.random() > 0.5 ? ylos : alas) : (Math.random() < 0.5 ? oikea : vasen)));
            Object[] uusi = haeLuvut().toArray();
            if (!Arrays.equals(vanha, uusi)) {
                maara++;
                vanha = uusi;
            }
        }
        assertEquals("Palauttaahan haeSiirrot() oikean määrän siirtoja?", maara, ajaMetodi(haeSiirrot));
    }
@Points("1.3.5")
    @Test
    public void valmis() {
        Method valmis = haeMetodi(boolean.class, "valmis");
        Method uusiPeli = haeMetodi(void.class, "uusiPeli");
        Method ylos = haeMetodi(void.class, "ylos");
        Method alas = haeMetodi(void.class, "alas");
        Method oikea = haeMetodi(void.class, "oikea");
        Method vasen = haeMetodi(void.class, "vasen");
        assertTrue("Kun kutsutaan vain metodia valmis(), pitäisi palauttaa true", (Boolean) ajaMetodi(valmis));
        ajaMetodi(alas);
        assertFalse("Kun kutsutaan ensin alas() ja sitten valmis, valmis() pitäisi olla false", (Boolean) ajaMetodi(valmis));
        ajaMetodi(ylos);
        assertTrue("Kun kutsutaan ensin alas(), sitten ylos(), valmis() pitäisi palauttaa true", (Boolean) ajaMetodi(valmis));
        ajaMetodi(oikea);
        assertFalse("Kun kutsutaan vain oikea(), valmis() pitäisi olla false", (Boolean) ajaMetodi(valmis));
        ajaMetodi(alas);
        ajaMetodi(vasen);
        ajaMetodi(ylos);
        assertFalse("Kun kutsutaan  oikea,alas,vasen,ylos, valmis() pitäisi olla false", (Boolean) ajaMetodi(valmis));
    }

    private boolean voikoRatkaista() {
        return riviPariton() == maaraKahdellaJaollinen();
    }

    private boolean riviPariton() {
        return (haeLuvut().indexOf(0)/4) % 2 != 0;
    }

    private boolean maaraKahdellaJaollinen() {
        List<Integer> luvut = haeLuvut();
        int maara = 0;
        for (int i = 0; i < luvut.size() - 1; i++) {
            for (int j = i + 1; j < luvut.size(); j++) {
                if (luvut.get(i) > luvut.get(j) && luvut.get(i) != 0 && luvut.get(j) != 0) {
                    maara++;
                }
            }
        }

        return maara % 2 == 0;
    }

    //<editor-fold defaultstate="collapsed" desc="apumetodit">
    private Method haeMetodi(Class paluuarvo, String nimi, Class<?>... parametrit) {
        try {
            return ReflectionUtils.requireMethod(false, clazz, paluuarvo, nimi, parametrit);
        } catch (AssertionError e) {
            fail("Onhan luokalla " + clazz.getSimpleName() + (parametrit.length == 0 ? " parametritön" : "")
                    + " metodi " + nimi + (parametrit.length == 0 ? "" : ", joka ottaa vastaan parametrit " + listaa(parametrit) + " ja")
                    + " jonka paluuarvon tyyppinä on " + paluuarvo.getSimpleName());
            return null;
        }
    }

    private Object ajaMetodi(Object oikeaArvo, Method metodi, Object... parametrit) {
        Object paluu = ajaMetodi(metodi, parametrit);
        assertEquals("metodi " + metodi.getName() + "(" + listaa(parametrit) + ") palautti väärän arvon", oikeaArvo, paluu);
        return paluu;
    }

    private Object ajaMetodi(Method metodi, Object... parametrit) {
        try {
            return ReflectionUtils.invokeMethod(metodi.getReturnType(), metodi, pelilauta, parametrit);
        } catch (Throwable ex) {
            return null;
        }
    }

    private String listaa(Object[] parametrit) {
        if (parametrit.length == 0) {
            return "";
        }
        StringBuilder pal = new StringBuilder();
        for (Object parametri : parametrit) {
            if (parametri.getClass().equals(Class.class)) {
                pal.append(((Class) parametri).getSimpleName());
            } else {
                pal.append(parametri.toString());
            }
            pal.append(",");
        }
        pal.deleteCharAt(pal.length() - 1);
        return pal.toString();
    }

    private void oletaMaara(String toString, char c, int haluttuMaara) {
        int maara = 0;
        for (int i = 0; i < toString.length(); i++) {
            if (toString.charAt(i) == c) {
                maara++;
            }
        }
        assertEquals("toStringin palauttamassa merkkijonossa oli väärä määrä \"" + c + "\" merkkejä", haluttuMaara, maara);
    }
    //</editor-fold>
}
