/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */
import java.util.*;

public class Koodari implements Tyontekija {
    private String nimi;
    private int palkka;
    private ArrayList<String> kielet = new ArrayList<String>();
    
    public Koodari(String nimi, int palkka) {
        this.nimi = nimi;
        this.palkka = palkka;
    }
    
    @Override
    public String haeNimi() {
        return nimi;
    }
    
    @Override
    public int haePalkka() {
        return palkka;
    }

    @Override
    public int laskeAlaiset() {
        return 0;
    }    

    @Override
    public void lisaaKieli(String kieli) {
        kielet.add(kieli);
    }
    
    @Override
    public boolean onkoTaitoa(String kieli) {
        return kielet.contains(kieli);
    }    
}
