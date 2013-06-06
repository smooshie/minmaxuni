/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */
public class Murtoluku {
    public int osoittaja;
    public int nimittaja;
    
    public Murtoluku(int osoittaja, int nimittaja) {
        this.osoittaja = osoittaja;
        this.nimittaja = nimittaja;
        
        int a = this.osoittaja;
        int b = this.nimittaja;
        int c;
        
        while(b != 0) {
            c = a;
            a = b;
            
            b = c % b;
        }
        
        if(a != 0) {
            this.osoittaja = this.osoittaja / a;
            this.nimittaja = this.nimittaja / a;
        }
        
        if(this.osoittaja < 0 && this.nimittaja < 0) {
            this.osoittaja = -this.osoittaja;
            this.nimittaja = -this.nimittaja;
        } else if(this.nimittaja < 0 && this.osoittaja > 0) {
            this.osoittaja = -this.osoittaja;
            this.nimittaja = -this.nimittaja;
        }
    }
    
    public Murtoluku(int osoittaja) {
        this.osoittaja = osoittaja;
        this.nimittaja = 1;
    }
    
    public int haeOsoittaja() {
        return this.osoittaja;
    }
    
    public int haeNimittaja() {
        return this.nimittaja;
    }
    
    public String toString() {
        String murtoluku;
        
        murtoluku = this.osoittaja + "/" + this.nimittaja;
        
        return murtoluku;
    }
    
    public double desimaalina() {
        double osoittajaDbl = this.osoittaja;
        double nimittajaDbl = this.nimittaja;
        
        return osoittajaDbl / nimittajaDbl;
    }
    
    public Murtoluku lisaa(Murtoluku toinen) {
        return new Murtoluku((toinen.nimittaja * this.osoittaja + this.nimittaja * toinen.osoittaja), (toinen.nimittaja * this.nimittaja));        
    }

    public Murtoluku vahenna(Murtoluku toinen) {
        return new Murtoluku((toinen.nimittaja * this.osoittaja - this.nimittaja * toinen.osoittaja), (toinen.nimittaja * this.nimittaja));        
    }    
    
    public Murtoluku kerro(Murtoluku toinen) {
        return new Murtoluku((toinen.osoittaja*this.osoittaja), (toinen.nimittaja*this.nimittaja));
    }
    
    public Murtoluku jaa(Murtoluku toinen) {
        return new Murtoluku((this.osoittaja*toinen.nimittaja), (this.nimittaja*toinen.osoittaja));
    }
    
    
    
}

