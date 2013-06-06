import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;

@Points("1.4")
public class MinuutitViikossaTest {

    @Rule
    public MockStdio io = new MockStdio();    
   
 
    @Test
    public void testi() {
        MinuutitViikossa.main(new String[0]);
        String tulostus = io.getSysOut();
        assertTrue("Ohjelman tulos on väärä", tulostus.indexOf("10080") != -1);
    }

}