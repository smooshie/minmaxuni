/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */
public class LyyraKortti {
     
    public int rahamaara = 0;
    public String omistaja = "";
    
    public LyyraKortti(String omistaja) {
        this.omistaja = omistaja;
    }
    
    public LyyraKortti(String omistaja, int rahamaara) {
        this.omistaja = omistaja;
        if(rahamaara < 0) {
            this.rahamaara = 0;
        } else {
            this.rahamaara = rahamaara;
        }
    }
    
    public void lisaaRahaa(int rahamaara) {
        if(rahamaara > 0) {
            this.rahamaara += rahamaara;
        }
    }
    
    public void vahennaRahaa(int rahamaara) {
        if(rahamaara > 0 ) {
            this.rahamaara -= rahamaara;
        }
    }
    
    public String haeOmistaja() {
        return this.omistaja;
    }
    
    public int haeRahamaara() {
        return this.rahamaara;
    }
    
    public void syoEdullisesti() {
        if(this.rahamaara >= 260) {
            this.rahamaara -= 260;
        } 
    }
    
    public void syoMaukkaasti() {
        if(this.rahamaara >= 420) {
            this.rahamaara -= 420;
        }
    }
    
    public String toString() {
        
        String euroja = "";
        float eurot = this.rahamaara;
        eurot = eurot / 100;
        
        euroja += this.omistaja + " (" +  eurot + " euroa)"; 
        
        return euroja;
}
}