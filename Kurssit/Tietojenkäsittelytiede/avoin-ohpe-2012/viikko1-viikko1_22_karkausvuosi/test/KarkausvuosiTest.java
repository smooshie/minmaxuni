import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.22")
public class KarkausvuosiTest {

    @Test
    public void perustapaukset() {
        assertTrue("Vuoden 2004 tulisi olla karkausvuosi", Karkausvuosi.karkausvuosi(2004));
        assertTrue("Vuoden 2007 ei tulisi olla karkausvuosi", !Karkausvuosi.karkausvuosi(2007));
    }
    
    @Test
    public void jaollinen100() {
        assertTrue("Vuoden 2100 ei tulisi olla karkausvuosi", !Karkausvuosi.karkausvuosi(2100));
    }

    @Test
    public void jaollinen400() {
        assertTrue("Vuoden 2000 tulisi olla karkausvuosi", Karkausvuosi.karkausvuosi(2000));
    }

    
}