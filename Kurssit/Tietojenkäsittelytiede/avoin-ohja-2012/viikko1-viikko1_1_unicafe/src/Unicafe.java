/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */

    public class Unicafe {

    public int rahamaara;
    public int asiakkaat;
    public int asiakasennatys;
    public String tilasto = "";
    
    
    public void lataaKateisella(LyyraKortti kortti, int rahamaara) {
        kortti.lisaaRahaa(rahamaara);
        this.rahamaara += rahamaara;
    }
    
    public void lataaKortilla(LyyraKortti kortti, int rahamaara) {
        kortti.lisaaRahaa(rahamaara);
    }
    
    public int haeRahamaara() {
        return this.rahamaara;
    }
    
    public void syoEdullisesti(LyyraKortti kortti) {
        kortti.syoEdullisesti();
    }
    
    public void syoEdullisesti() {
        this.rahamaara += 260;
    }
    
    public void syoMaukkaasti(LyyraKortti kortti) {
        kortti.syoMaukkaasti();
    }

    public void syoMaukkaasti() {
        this.rahamaara += 420;
    }
    
    public void luokkaretki(LyyraKortti kortti, int edulliset, int maukkaat) {
        if(kortti.rahamaara >= edulliset*260 + maukkaat*420) {
            kortti.vahennaRahaa(edulliset*260 + maukkaat*420);
        }
    }
    
    public void asiakasTulee() {
        this.asiakkaat++;
        if(this.asiakkaat > this.asiakasennatys) {
            this.asiakasennatys = this.asiakkaat;
        }
        
        this.lisaaTilastoon();
    }
    
    public void asiakasPoistuu() {
        this.asiakkaat--;
    
        this.lisaaTilastoon();
    }

    public int laskeAsiakkaat() {
        return this.asiakkaat;
    }
    
    public int asiakasennatys() {
        return this.asiakasennatys;
    }
    
    public void lisaaTilastoon() {

        String tilastorivi = "";
        
        for(int i = 0; i < this.asiakkaat; i++) {
            tilastorivi += "*";
        }
        
        tilastorivi += "\n";
        
        this.tilasto += tilastorivi;
    }
    
    public void naytaTilasto() {
        System.out.println(this.tilasto);
    }
}
