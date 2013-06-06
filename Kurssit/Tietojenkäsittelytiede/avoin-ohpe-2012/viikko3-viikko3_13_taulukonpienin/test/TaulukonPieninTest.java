import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;

@Points("3.13")
public class TaulukonPieninTest {
    
    @Test
    public void testi1() {
        int a[] = {3, 1, 2};
        String t = "[3, 1, 2]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + t, TaulukonPienin.taulukonPienin(a) == 1);
        tarkistaTaulukonMuokkaamattomuus(a, new int[]{3, 1, 2});
    }

    @Test
    public void testi2() {
        int a[] = {5, 6, 7, 8, 9};
        String t = "[5, 6, 7, 8, 9]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + t, TaulukonPienin.taulukonPienin(a) == 5);
        tarkistaTaulukonMuokkaamattomuus(a, new int[]{5, 6, 7, 8, 9});
    }    

    @Test
    public void testi3() {
        int a[] = {-1, -5, 5};
        String t = "[-1, -5, 5]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + t, TaulukonPienin.taulukonPienin(a) == -5);
        tarkistaTaulukonMuokkaamattomuus(a, new int[]{-1, -5, 5});
    }    

    @Test
    public void testi4() {
        int a[] = {2, 7, 5, -10};
        String t = "[2, 7, 5, -10]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + t, TaulukonPienin.taulukonPienin(a) == -10);
        tarkistaTaulukonMuokkaamattomuus(a, new int[]{2, 7, 5, -10});
    }
    
    public void tarkistaTaulukonMuokkaamattomuus(int[] actual, int[] expected) {
        assertTrue("Metodisi muokkaa taulukon sisältöä. Toteuta metodi niin että taulukon arvot pysyvät "
                + "muuttumattomina.", Arrays.equals(actual, expected));
    }
    
    
}
