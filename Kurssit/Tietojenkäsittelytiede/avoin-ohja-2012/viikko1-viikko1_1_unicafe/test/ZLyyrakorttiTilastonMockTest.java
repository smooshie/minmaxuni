/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author jarmo
 */
public class ZLyyrakorttiTilastonMockTest {

    Class lyyraClass;
    Class unicafeClass;
    Constructor<Object> lyyraYksConst;
//    @Rule
//    public MockStdio io = new MockStdio();

    @Before
    public void setUp() {
        try {
            unicafeClass = ReflectionUtils.findClass("Unicafe");
        } catch (Throwable t) {
        }
    }

    private Object getNewLyyra(String nimi, int rahaa) {
        Object lyraKortti = null;
        try {
            lyraKortti = ReflectionUtils.invokeConstructor(ReflectionUtils.requireConstructor(lyyraClass, String.class, int.class), nimi, rahaa);
        } catch (Throwable ex) {
            fail("Lyyra-kortin kaksiparametrisen konstruktorin käyttö ei onnistu.");
        }
        return lyraKortti;
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
            fail("Oltethan luonut methodin \"public void asiakasTulee()\" luokkaan \"Uncafe\"?");
        }
    }

    private void initAsiakasPoistuu(Object unicafe) {
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeMethodAsiakasPoistuu(), unicafe);
        } catch (Throwable ex) {
            fail("Oltethan luonut methodin \"public void asiakasPoistuu()\" luokkaan \"Uncafe\"?");
        }
    }

    private int initLaskeAsiakkaat(Object unicafe) {
        try {
            return ReflectionUtils.invokeMethod(int.class, getUnicafeMethodLaskeAsiakkaat(), unicafe);
        } catch (Throwable ex) {
            fail("Oltethan luonut methodin \"public void LaskeAsiakkaat()\" luokkaan \"Uncafe\"?");
        }
        return -1337;
    }

    private String liikutaDataa(Object unicafe) {


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

    private Object getnewUnicafe() {
        Object unicafe = null;
        try {
            unicafe = ReflectionUtils.invokeConstructor(ReflectionUtils.requireConstructor(unicafeClass));
        } catch (Throwable ex) {
            fail("Olethan luonut luokan \"Unicafe\"");
        }
        return unicafe;

    }

    private Method getUnicafeMethodTilasto() {
        Method m = null;
        try {
            m = ReflectionUtils.requireMethod(unicafeClass, "naytaTilasto");
            assertNotNull(m);
        } catch (Throwable t) {
            fail("Olethan toteuttanut methodin \"public void naytaTilasto()\" luokkaan \"Unicafe");
        }
        return m;
    }

    @Test
    @Points("1.1.10")
    public void Testi() {
        OutputStream outs = System.out;
        PrintStream dos = new PrintStream(outs);
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        System.setOut(new PrintStream(ba));
        Object unicafe = getnewUnicafe();
        String syote = liikutaDataa(unicafe);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeMethodTilasto(), unicafe);
        } catch (Throwable ex) {
            fail("Olethan toteuttanut methodin \"public void naytaTilasto()\" luokkaan \"Unicafe" + ex);
        }
        System.setOut(dos);
        String[] ar = ba.toString().trim().replaceAll("\r\n", "\n").split("\n");
        if (ar.length != 23) {
            fail("Tulostuksessa pitäisi olla 23 riviä, nyt siinä oli " + ar.length);
        }

        if (!"*".equals(ar[0])) {
            fail("Ensimmäisellä rivillä pitäisi olla \"*\"");
        }
        if (!"***".equals(ar[2])) {
            fail("3. rivillä pitäisi olla \"***\"");
        }
        if (!"************".equals(ar[11])) {
            fail("12. rivillä pitäisi olla \"************\", mutta siellä oli: " + ar[11].length());
        }
        ArrayList<String> array = new ArrayList<String>() {

            {
                add("*");
                add("**");
                add("***");
                add("****");
                add("*****");
                add("******");
                add("*******");
                add("********");
                add("*********");
                add("**********");
                add("***********");
                add("************");
                add("***********");
                add("**********");
                add("*********");
                add("********");
                add("*******");
                add("******");
                add("*****");
                add("****");
                add("***");
                add("**");
                add("*");
            }
        };

        for (int i = 0; i < array.size(); i++) {
//            System.out.println(ar[i]+" --- "+ array.get(i));
            if (ar[i] == null ? array.get(i) != null : !ar[i].equals(array.get(i))) {
                fail("Rivillä " + i + " olisi pitänyt olla \"" + array.get(i) + "\". Nyt siinä oli: \"" + ar[i] + "\".");
            }

        }

    }

    @Test
    @Points("1.1.10")
    public void Testi2() {
        OutputStream outs = System.out;
        PrintStream dos = new PrintStream(outs);
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        System.setOut(new PrintStream(ba));
        Object unicafe = getnewUnicafe();
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasTulee(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        initAsiakasPoistuu(unicafe);
        try {
            ReflectionUtils.invokeMethod(Void.TYPE, getUnicafeMethodTilasto(), unicafe);
            initAsiakasTulee(unicafe);
        } catch (Throwable ex) {
            fail("Olethan toteuttanut metodin \"public void naytaTilasto()\" luokkaan \"Unicafe" + ex);
        }
        System.setOut(dos);
        String[] ar = ba.toString().trim().replaceAll("\r\n", "\n").split("\n");
//        for (String string : ar) {
//            System.out.println("add(\"" + string + "\");");
//        }
        ArrayList<String> array = new ArrayList<String>() {

            {
                add("*");
                add("**");
                add("***");
                add("****");
                add("*****");
                add("****");
                add("***");
                add("****");
                add("*****");
                add("******");
                add("*******");
                add("********");
                add("*********");
                add("********");
                add("*******");
                add("******");
                add("*****");
                add("****");
                add("***");
                add("**");
                add("***");
                add("****");
                add("*****");
                add("******");
                add("*******");
                add("********");
                add("*********");
                add("**********");
                add("***********");
                add("************");
                add("*************");
                add("**************");
                add("***************");
                add("****************");
                add("***************");
                add("**************");
                add("*************");
                add("**************");
                add("***************");
                add("****************");
                add("*****************");
                add("******************");
                add("*******************");
                add("********************");
                add("*******************");
                add("******************");
                add("*****************");
                add("****************");
            }
        };

        for (int i = 0; i < array.size(); i++) {
//            System.out.println(ar[i]+" --- "+ array.get(i));
            if (ar[i] == null ? array.get(i) != null : !ar[i].equals(array.get(i))) {
                fail("Rivillä " + i + " olisi pitänyt olla \"" + array.get(i) + "\". Nyt siinä oli: \"" + ar[i] + "\".");
            }

        }

    }
}
