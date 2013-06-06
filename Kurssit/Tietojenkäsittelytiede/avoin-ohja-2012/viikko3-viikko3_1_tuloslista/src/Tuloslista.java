/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */
import java.util.*;
import java.io.*;

public class Tuloslista {
    private int koko;
    
    public ArrayList<Tulos> tulokset = new ArrayList<Tulos>();
    
    public Tuloslista(int koko) {
        this.koko = koko;
        
        for (int i = 0; i < koko; i++) {
            tulokset.add(new Tulos("Uolevi", 0));
        }
       
    }
    
    public void lisaaTulos(String nimi, int pisteet) {
            if (paaseeListalle(pisteet)) {
                tulokset.set(koko-1, new Tulos(nimi, pisteet));
                Collections.sort(tulokset);
            }
    }
    
    public boolean paaseeListalle(int pisteet) {
            if (pisteet > tulokset.get(this.koko-1).haePisteet()) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        String print = "";
        
        for (int i = 0; i < koko; i++) {
            print += tulokset.get(i).toString() + "\n"; }
        return print;
    }
    
    public void tallenna(String tiedostonimi) {
        try {
            PrintWriter kirjoittaja = new PrintWriter(new File(tiedostonimi));
            kirjoittaja.println(koko);
             for(Tulos i : this.tulokset) {
                kirjoittaja.println(i.haeNimi());    
                kirjoittaja.println(i.haePisteet());
                   
            }
             kirjoittaja.close();
             
        } catch (Exception e) {
            System.out.println("Virhe tiedoston kirjoittamisessa!");
        }
    }
    
    public void lataa(String tiedostonimi) {
         try {
            File tiedosto = new File(tiedostonimi);
            if (!tiedosto.exists()) {
                return;
            }
            tulokset.clear();
            Scanner lukija = new Scanner(tiedosto);
            this.koko = Integer.parseInt(lukija.nextLine());
            for (int i = 0; i < koko; i++) {
                String nimi = lukija.nextLine();
                int pisteet = Integer.parseInt(lukija.nextLine());
                tulokset.add(new Tulos(nimi, pisteet));
            }
        } catch (Exception e) {
            System.out.println("Virhe lataamisessa!");
        }
    }
    }

