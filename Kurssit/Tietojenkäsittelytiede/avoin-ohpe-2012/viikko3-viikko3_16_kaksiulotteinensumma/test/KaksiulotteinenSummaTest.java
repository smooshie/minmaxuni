import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("3.16")
public class KaksiulotteinenSummaTest {

    @Test
    public void testi1() {
        int a[][] = {{1, 1}, {1, 1}};
        String x = "[[1, 1], [1, 1]]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + x, KaksiulotteinenSumma.laskeSumma(a) == 4);
    }

    @Test
    public void testi2() {
        int a[][] = {{1, 2}, {3, 4}, {5, 6}};
        String x = "[[1, 2], [3, 4], [5, 6]]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + x, KaksiulotteinenSumma.laskeSumma(a) == 21);
    }
    
    @Test
    public void testi3() {
        int a[][] = {{5, -5, 3}, {1, 1, 1}};
        String x = "[[5, -5, 3], [1, 1, 1]]";
        assertTrue("Metodi toimii väärin, kun taulukko on " + x, KaksiulotteinenSumma.laskeSumma(a) == 6);
    }

    
    
}