import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.24")
public class KuningattaretTest {

    @Test
    public void testit() {
        for (int n = 1; n <= 50; n++) {
            int o = (n*(n-1)*(n-2)*(3*n-1))/6;
            assertTrue("Kun koko on " + n + ", tuloksen tulisi olla " + o, o == Kuningattaret.kuningattaret(n));
        }
    }
    
}