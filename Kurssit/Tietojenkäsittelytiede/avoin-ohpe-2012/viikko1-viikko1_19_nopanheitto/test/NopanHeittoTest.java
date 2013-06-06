import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.19")
public class NopanHeittoTest {

    @Test
    public void oikeitaArvoja() {
        for (int i = 0; i < 60000; i++) {
            int heitto = NopanHeitto.heitaNoppaa();
            assertTrue("Noppa antoi liian pienen arvon: " + heitto, heitto >= 1);
            assertTrue("Noppa antoi liian suuren arvon: " + heitto, heitto <= 6);
        }
    }
    
    @Test
    public void jarkevaJakauma() {
        int[] tilasto = new int[6+1];
        int poikkeama = 300;
        for (int i = 0; i < 60000; i++) {
            int heitto = NopanHeitto.heitaNoppaa();
            assertTrue("Olet tehnyt tosi oudon nopan", heitto >= 1 && heitto <= 6);
            tilasto[heitto]++;
        }
        for (int i = 1; i <= 6; i++) {
            if (tilasto[i] < 10000 - poikkeama) {
                assertTrue("Noppa tuntuu antavan liian harvoin silmälukua " + i, false);
            }
            if (tilasto[i] > 10000 + poikkeama) {
                assertTrue("Noppa tuntuu antavan liian usein silmälukua " + i, false);
            }
        }
    }

    
}
