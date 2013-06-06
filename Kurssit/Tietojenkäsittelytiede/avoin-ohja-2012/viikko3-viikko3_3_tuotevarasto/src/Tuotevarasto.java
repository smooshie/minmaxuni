/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */
public class Tuotevarasto {
    private int maara = 0;
    
    public void lisaaYksi() {
        maara++;
    }
    
    public void lisaaMonta(int maara) {
        this.maara += maara;
    }
    
    public void poistaYksi() {
        if (maara > 0) {
            maara--;
        }
    }
    
    public void poistaMonta(int maara) {
        this.maara -= maara;
        if (maara < 0) {
            maara = 0;
        }
    }
    
    public int haeMaara() {
        return maara;
    }
}