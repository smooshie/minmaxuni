import org.junit.Test;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("3.19")
public class CollatzinKonjektuuriTest {

    @Test
    public void testi1() {
        int luku = 64;
        int tulos = 7;
        assertTrue("Metodi 'laskeOuto' toimii väärin kun aloitusluku on " + luku, CollatzinKonjektuuri.laskeOuto(luku) == tulos);
    }

    @Test
    public void testi2() {
        int luku = 35;
        int tulos = 14;
        assertTrue("Metodi 'laskeOuto' toimii väärin kun aloitusluku on " + luku, CollatzinKonjektuuri.laskeOuto(luku) == tulos);
    }

    @Test
    public void testi3() {
        int luku = 47;
        int tulos = 105;
        assertTrue("Metodi 'laskeOuto' toimii väärin kun aloitusluku on " + luku, CollatzinKonjektuuri.laskeOuto(luku) == tulos);
    }  
    
    @Test
    public void testi4() {
        int alaraja = 50;
        int ylaraja = 70;
        int tulos = 54;
        assertTrue("Metodi 'pisinOuto' toimii väärin kun alaraja on " + alaraja + " ja yläraja on " + ylaraja, CollatzinKonjektuuri.pisinOuto(alaraja, ylaraja) == tulos);
    }     

    @Test
    public void testi5() {
        int alaraja = 100;
        int ylaraja = 200;
        int tulos = 171;
        assertTrue("Metodi 'pisinOuto' toimii väärin kun alaraja on " + alaraja + " ja yläraja on " + ylaraja, CollatzinKonjektuuri.pisinOuto(alaraja, ylaraja) == tulos);
    }     

    @Test
    public void testi6() {
        int alaraja = 1;
        int ylaraja = 10000;
        int tulos = 6171;
        assertTrue("Metodi 'pisinOuto' toimii väärin kun alaraja on " + alaraja + " ja yläraja on " + ylaraja, CollatzinKonjektuuri.pisinOuto(alaraja, ylaraja) == tulos);
    }     
    
}