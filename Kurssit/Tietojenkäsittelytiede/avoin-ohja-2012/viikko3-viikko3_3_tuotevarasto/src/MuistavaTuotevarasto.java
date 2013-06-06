/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */
import java.util.*;

public class MuistavaTuotevarasto extends Tuotevarasto {
    private Muutoshistoria historia = new Muutoshistoria();
    
    public void lisaaYksi() {
        super.lisaaYksi();
        historia.lisaaMerkinta(super.haeMaara());
    }
    
    public void lisaaMonta(int maara) {
        super.lisaaMonta(maara);
        historia.lisaaMerkinta(super.haeMaara());        
    }

    public void poistaYksi() {
        super.poistaYksi();
        historia.lisaaMerkinta(super.haeMaara());
    }
    
    public void poistaMonta(int maara) {
        super.poistaMonta(maara);
        historia.lisaaMerkinta(super.haeMaara());        
    }
    
    public int haeMaara() {
        return super.haeMaara();
    }    
    
    public Muutoshistoria haeHistoria() {
        return historia;
    }
    
}
