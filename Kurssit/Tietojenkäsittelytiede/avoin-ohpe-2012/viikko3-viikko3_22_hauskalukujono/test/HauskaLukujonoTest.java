import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("3.22")
public class HauskaLukujonoTest {

    @Test
    public void testi1() {
        int kohta = 1;
        String tulos = "1";
        assertTrue("Metodi toimii väärin kun kohta on " + kohta, HauskaLukujono.hauska(kohta).equals(tulos));
    }

    @Test
    public void testi2() {
        int kohta = 2;
        String tulos = "11";
        assertTrue("Metodi toimii väärin kun kohta on " + kohta, HauskaLukujono.hauska(kohta).equals(tulos));
    }

    @Test
    public void testi3() {
        int kohta = 5;
        String tulos = "111221";
        assertTrue("Metodi toimii väärin kun kohta on " + kohta, HauskaLukujono.hauska(kohta).equals(tulos));
    }
    
    @Test
    public void testi4() {
        int kohta = 8;
        String tulos = "1113213211";
        assertTrue("Metodi toimii väärin kun kohta on " + kohta, HauskaLukujono.hauska(kohta).equals(tulos));
    }

    @Test
    public void testi5() {
        int kohta = 11;
        String tulos = "11131221133112132113212221";
        assertTrue("Metodi toimii väärin kun kohta on " + kohta, HauskaLukujono.hauska(kohta).equals(tulos));
    }    
    
}