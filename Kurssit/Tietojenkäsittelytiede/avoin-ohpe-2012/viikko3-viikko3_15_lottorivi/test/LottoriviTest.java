import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;

@Points("3.15")
public class LottoriviTest {

    @Test
    public void testi1() {
        int a[] = {1, 2, 3, 4, 5, 6, 7};
        int b[] = {8, 9, 10, 11, 12, 13, 14};
        String x = "[1, 2, 3, 4, 5, 6, 7]";
        String y = "[8, 9, 10, 11, 12, 13, 14]";
        assertTrue("Metodi toimii väärin, kun oikea lottorivi on " + x + " ja veikattu lottorivi on " + y, Lottorivi.tarkistaRivi(a, b) == 0);
        tarkistaTaulukonMuokkaamattomuus(a, new int[]{1, 2, 3, 4, 5, 6, 7});
        tarkistaTaulukonMuokkaamattomuus(b, new int[]{8, 9, 10, 11, 12, 13, 14});
    }

    @Test
    public void testi2() {
        int a[] = {1, 2, 3, 4, 5, 6, 9};
        int b[] = {1, 9, 10, 11, 12, 13, 14};
        String x = "[1, 2, 3, 4, 5, 6, 9]";
        String y = "[1, 9, 10, 11, 12, 13, 14]";
        assertTrue("Metodi toimii väärin, kun oikea lottorivi on " + x + " ja veikattu lottorivi on " + y, Lottorivi.tarkistaRivi(a, b) == 2);
        tarkistaTaulukonMuokkaamattomuus(a, new int[]{1, 2, 3, 4, 5, 6, 9});
        tarkistaTaulukonMuokkaamattomuus(b, new int[]{1, 9, 10, 11, 12, 13, 14});
    }
    
    
    @Test
    public void testi3() {
        int a[] = {1, 2, 3, 4, 5, 10, 20};
        int b[] = {5, 10, 15, 20, 25, 30, 35};
        String x = "[1, 2, 3, 4, 5, 10, 20]";
        String y = "[5, 10, 15, 20, 25, 30, 35]";
        assertTrue("Metodi toimii väärin, kun oikea lottorivi on " + x + " ja veikattu lottorivi on " + y, Lottorivi.tarkistaRivi(a, b) == 3);
        tarkistaTaulukonMuokkaamattomuus(a, new int[]{1, 2, 3, 4, 5, 10, 20});
        tarkistaTaulukonMuokkaamattomuus(b, new int[]{5, 10, 15, 20, 25, 30, 35});
    }

    @Test
    public void testi4() {
        int a[] = {1, 2, 3, 4, 5, 6, 7};
        int b[] = {1, 2, 3, 4, 5, 6, 7};
        String x = "[1, 2, 3, 4, 5, 6, 7]";
        String y = "[1, 2, 3, 4, 5, 6, 7]";
        assertTrue("Metodi toimii väärin, kun oikea lottorivi on " + x + " ja veikattu lottorivi on " + y, Lottorivi.tarkistaRivi(a, b) == 7);
        tarkistaTaulukonMuokkaamattomuus(a, new int[]{1, 2, 3, 4, 5, 6, 7});
        tarkistaTaulukonMuokkaamattomuus(b, new int[]{1, 2, 3, 4, 5, 6, 7});
    }

     public void tarkistaTaulukonMuokkaamattomuus(int[] actual, int[] expected) {
        assertTrue("Metodisi muokkaa taulukon sisältöä. Toteuta metodi niin että taulukon arvot pysyvät "
                + "muuttumattomina.", Arrays.equals(actual, expected));
    }
}
