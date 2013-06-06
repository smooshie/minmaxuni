import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.22")
public class PalindromiluvutTest {

    @Test
    public void testit() {
        int[] data = {9, 18, 108, 198, 1098};
        int raja = 10;
        for (int i = 0; i < data.length; i++)  {
            assertTrue("Lukuvälin 1..." + raja + " tuloksen tulisi olla " +
                data[i], Palindromiluvut.palindromiluvut(1, raja) == data[i]);
            raja = raja * 10;
        }
        
    }
	
	@Test
	public void rajatesti() {
		assertTrue("Lukuvälin 44...55 tuloksen tulisi olla 2", Palindromiluvut.palindromiluvut(44, 55) == 2);
	}

    
}
