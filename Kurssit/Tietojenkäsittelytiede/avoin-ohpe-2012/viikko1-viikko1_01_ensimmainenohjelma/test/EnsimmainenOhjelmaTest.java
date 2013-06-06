import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.1")
public class EnsimmainenOhjelmaTest {

    @Rule
    public MockStdio io = new MockStdio();    
   
 
    @Test
    public void testi() {
        EnsimmainenOhjelma.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelma ei tulosta oikeaa tekstiä", tulostus.indexOf("eipparallaa") != -1);
    }

}