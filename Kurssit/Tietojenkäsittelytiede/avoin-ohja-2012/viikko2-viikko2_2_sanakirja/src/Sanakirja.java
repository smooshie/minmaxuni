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
    

public class Sanakirja {
    
 
    TreeMap<String, String> sanakirja = new TreeMap<String, String>();
 
    public Sanakirja() {
        
    }
    
    public void lisaaSana(String sana, String kaannos) {
        sanakirja.put(sana, kaannos);

    }
    
    public String kaannaSana(String sana) {
      if (sanakirja.containsKey(sana)){
      return sanakirja.get(sana);
    } else {
          return "";
      }
    }
    
    public int sanojenMaara() {
    return sanakirja.size();
    }
    
    public void poistaSana(String sana) {
        sanakirja.remove(sana);
    }
    
    public void tyhjenna() {
        sanakirja.clear();
    }
    
    public void tulostaSisalto() {
        for (String sana : sanakirja.keySet()) {
            System.out.println(sana + "=" + sanakirja.get(sana));
        }
    }
    
    public void tallenna (String tiedostonimi) {
        try {
            PrintWriter kirjoittaja = new PrintWriter(new File(tiedostonimi), "UTF-8");
            for(String sana : sanakirja.keySet()) {
                kirjoittaja.println(sana + " " + sanakirja.get(sana));
            }
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedoston kirjoittamisessa!");
        }
    }
    
    public void lataa(String tiedostonimi) {
        try {
            Scanner lukija = new Scanner(new File(tiedostonimi, "UTF-8"));
            while(lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                System.out.println(rivi);
            }
        } catch(Exception e) {
                System.out.println("Kauhea virhe sanojen lukemisessa!");
            
        }
    }
    
    public String kaannaLause(String lause) {
        String kaannettyLause = "";
        String[] ekaLause = lause.split(" ");
            for (String sana : ekaLause) {
                sana = sana.toLowerCase();
                sana = sana.replaceAll("[^abcdefghijklmnopqrstuvwxyzåäö]", "");
                
                kaannettyLause += kaannaSana(sana) + " ";
            }
    return kaannettyLause.trim();
    }
}
