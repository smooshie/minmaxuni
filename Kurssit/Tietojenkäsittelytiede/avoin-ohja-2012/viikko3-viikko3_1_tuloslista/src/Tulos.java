/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */

public class Tulos implements Comparable<Tulos> {
    public String nimi;
    public int pisteet;

    
    public Tulos(String nimi, int pisteet) {
         this.nimi = nimi;
         this.pisteet = pisteet;
    }
    
    public String haeNimi() {
        return nimi;
    }
    
    public int haePisteet() {
        return pisteet;
    }
    
    public String toString() {
        return nimi + " " + pisteet;
    }
    
    public int compareTo(Tulos toinen) {
        if (this.pisteet != toinen.pisteet) {
            return toinen.pisteet - this.pisteet;
        }
        return this.nimi.compareTo(toinen.nimi);
    }
}
