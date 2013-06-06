
import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;

@Points("3.14")
public class KaksiSamaaTest {

    @Test
    public void testi1() {
        int a[] = {1, 2, 3, 4};
        String t = "[1, 2, 3, 4]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + t, !KaksiSamaa.kaksiSamaa(a));
        tarkistaTaulukonMuokkaamattomuus(a, new int[] {1, 2, 3, 4});
    }

    @Test
    public void testi2() {
        int a[] = {1, 2, 1, 2};
        String t = "[1, 2, 1, 2]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + t, KaksiSamaa.kaksiSamaa(a));
        tarkistaTaulukonMuokkaamattomuus(a, new int[] {1, 2, 1, 2});
    }

    @Test
    public void testi3() {
        int a[] = {1, 2, 3, 1};
        String t = "[1, 2, 3, 1]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + t, KaksiSamaa.kaksiSamaa(a));
        tarkistaTaulukonMuokkaamattomuus(a, new int[] {1, 2, 3, 1});
    }

    @Test
    public void testi4() {
        int a[] = {1, 2, 3, 2};
        String t = "[1, 2, 3, 2]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + t, KaksiSamaa.kaksiSamaa(a));
        tarkistaTaulukonMuokkaamattomuus(a, new int[] {1, 2, 3, 2});
    }

    @Test
    public void testi5() {
        int a[] = {5, 1, 4, 2, 3};
        String t = "[5, 1, 4, 2, 3]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + t, !KaksiSamaa.kaksiSamaa(a));
        tarkistaTaulukonMuokkaamattomuus(a, new int[] {5, 1, 4, 2, 3});
    }

    public void tarkistaTaulukonMuokkaamattomuus(int[] actual, int[] expected) {
        assertTrue("Metodisi muokkaa taulukon sisältöä. Toteuta metodi niin että taulukon arvot pysyvät "
                + "muuttumattomina.", Arrays.equals(actual, expected));
    }
}