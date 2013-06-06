import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.15")
public class ViikonpaivaTest {

    private void testi(int parametri, String tulos) {
        boolean oikein = Viikonpaiva.viikonpaiva(parametri).toLowerCase().equals(tulos);
        assertTrue("Metodi 'viikonpaiva' toimii väärin, kun parametri on " + parametri, oikein);        
    }
    
    @Test
    public void maanantai() {
        testi(1, "maanantai");
    }

    @Test
    public void tiistai() {
        testi(2, "tiistai");
    }

    @Test
    public void keskiviikko() {
        testi(3, "keskiviikko");
    }    

    @Test
    public void torstai() {
        testi(4, "torstai");
    }        

    @Test
    public void perjantai() {
        testi(5, "perjantai");
    }       

    @Test
    public void lauantai() {
        testi(6, "lauantai");
    }        

    @Test
    public void sunnuntai() {
        testi(7, "sunnuntai");
    }    

}
