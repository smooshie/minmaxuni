import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("3.20")
public class PisinToistoTest {

    @Test
    public void testi1() {
        String ketju = "AAA";
        int tulos = 3;
        assertTrue("Metodi toimii väärin kun DNA-ketju on " + ketju, PisinToisto.pisinToisto(ketju) == tulos);
    }

    @Test
    public void testi2() {
        String ketju = "ACGT";
        int tulos = 1;
        assertTrue("Metodi toimii väärin kun DNA-ketju on " + ketju, PisinToisto.pisinToisto(ketju) == tulos);
    }

    @Test
    public void testi3() {
        String ketju = "CAACAACAAC";
        int tulos = 2;
        assertTrue("Metodi toimii väärin kun DNA-ketju on " + ketju, PisinToisto.pisinToisto(ketju) == tulos);
    }

    @Test
    public void testi4() {
        String ketju = "CAACAAACAAC";
        int tulos = 3;
        assertTrue("Metodi toimii väärin kun DNA-ketju on " + ketju, PisinToisto.pisinToisto(ketju) == tulos);
    }
    
    @Test
    public void testi5() {
        String ketju = "ACCGGGTTTT";
        int tulos = 4;
        assertTrue("Metodi toimii väärin kun DNA-ketju on " + ketju, PisinToisto.pisinToisto(ketju) == tulos);
    }

    
    
}