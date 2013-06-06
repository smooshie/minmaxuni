/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */
import java.util.*;

public class Tekoaly {
    private Tarkistaja tarkistaja;
    
    public Tekoaly(Tarkistaja tarkistaja) {
        this.tarkistaja = tarkistaja;
    }
    
    public String valitseSana() {
        ArrayList<String> seuraavat = tarkistaja.haeSeuraavat();
        if (seuraavat.isEmpty()) {
            return "";
        } else {
            Collections.shuffle(seuraavat);     
            return seuraavat.get(0);
        }
    }
}
