import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.23")
public class PythagoraanKolmikotTest {

    @Test
    public void testit() {
        int[] data = {4, 12, 22, 32, 40, 52, 66, 78, 94, 104, 118, 132};
        for (int i = 0; i < data.length; i++)  {
            assertTrue("Ylärajan " + (10 + i * 10) + " tuloksen tulisi olla " +
                data[i], PythagoraanKolmikot.pythagoras(10 + i * 10) == data[i]);
        }
        
    }

    
}