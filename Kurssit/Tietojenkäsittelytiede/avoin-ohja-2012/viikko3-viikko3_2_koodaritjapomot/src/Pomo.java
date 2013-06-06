/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */
import java.util.*;

public class Pomo implements Tyontekija {
    private String nimi;
    private int palkka;
    private ArrayList<String> kielet = new ArrayList<String>();
    private ArrayList<Tyontekija> alaiset = new ArrayList<Tyontekija>();
    
    public Pomo(String nimi, int palkka) {
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
    
    public void lisaaAlainen(Tyontekija alainen) {
        alaiset.add(alainen);
    }
    
    @Override
    public int laskeAlaiset() {
        int summa = 0;
        for (Tyontekija alainen : alaiset) {
            summa += 1;
            summa += alainen.laskeAlaiset();
        }
        return summa;
    }
 
    @Override
    public void lisaaKieli(String kieli) {
        kielet.add(kieli);
    }
         
    @Override
    public boolean onkoTaitoa(String kieli) {
        if (kielet.contains(kieli)) {
            return true;
        }
        for (Tyontekija alainen : alaiset) {
            if (alainen.onkoTaitoa(kieli)) {
                return true;
            }
        }
        return false;
    }    
}
