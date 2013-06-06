import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.16")
public class SuomestaEnglanniksiTest {

    private void testi(String suomeksi, String englanniksi) {
        boolean oikein = SuomestaEnglanniksi.englanniksi(suomeksi).toLowerCase().equals(englanniksi);
        assertTrue("Metodi 'englanniksi' toimii väärin, kun sana on '" + suomeksi + "'", oikein);        
    }
    
    @Test
    public void apina() {
        testi("apina", "monkey");
    }

    @Test
    public void banaani() {
        testi("banaani", "banana");
    }

    @Test
    public void cembalo() {
        testi("cembalo", "harpsichord");
    }

}