import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.21")
public class ParillisuusTest {

    @Test
    public void perustapaukset() {
        assertTrue("Luvun 10 tulisi olla parillinen luku", Parillisuus.parillinen(10));
        assertTrue("Luvun 11 ei tulisi olla parillinen luku", !Parillisuus.parillinen(11));
    }
    
    @Test
    public void nolla() {
        assertTrue("Luvun 0 tulisi olla parillinen luku", Parillisuus.parillinen(0));
    }

    @Test
    public void negatiivinen() {
        assertTrue("Luvun -4 tulisi olla parillinen luku", Parillisuus.parillinen(-4));
        assertTrue("Luvun -5 ei tulisi olla parillinen luku", !Parillisuus.parillinen(-5));
    }

    
}