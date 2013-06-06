import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.20")
public class RahanHeittoTest {

    @Test
    public void oikeitaArvoja() {
        for (int i = 0; i < 60000; i++) {
            String tulos = RahanHeitto.heitaRahaa().toLowerCase();
            boolean oikein = tulos.equals("kruuna") || tulos.equals("klaava");
            assertTrue("Metodi 'heitaRahaa' palautti väärän arvon: " + tulos, oikein);
        }
    }
    
    @Test
    public void jarkevaJakauma() {
        int kruunat = 0;
        int klaavat = 0;
        int poikkeama = 500;
        for (int i = 0; i < 100000; i++) {
            String tulos = RahanHeitto.heitaRahaa().toLowerCase();
            boolean oikein = tulos.equals("kruuna") || tulos.equals("klaava");
            assertTrue("Olet tehnyt tosi oudon metodin", oikein);
            if (tulos.equals("kruuna")) kruunat++;
            else klaavat++;
        }
        if (kruunat < 50000 - poikkeama) {
            assertTrue("Klaavaa tuntuu tulevan liian usein", false);
        }
        if (kruunat > 50000 + poikkeama) {
            assertTrue("Kruunaa tuntuu tulevan liian usein", false);
        }
    }

    
}
