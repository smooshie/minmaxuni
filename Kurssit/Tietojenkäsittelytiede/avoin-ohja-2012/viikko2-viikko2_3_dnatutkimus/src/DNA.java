
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
import java.util.regex.*;

public class DNA {
    
    public String dna = "";
    
    public DNA(String tiedostonimi) {
        try {
            Scanner lukija = new Scanner(new File(tiedostonimi));
            while(lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                dna = rivi;
            }
        } catch(Exception e) {
                System.out.println("Kauhea virhe sanojen lukemisessa!");
            
        }
    }
     public int haePituus() {
         return dna.length();
     }
     
     public ArrayList<Integer> haeOsanKohdat(String osa) {
         ArrayList<Integer> paikat = new ArrayList<Integer>();
         
             for (int i = -1; (i = dna.indexOf(osa, i + 1)) != -1;) {
                 paikat.add(i);
             }
         return paikat;
     }
    
     public TreeSet<String> haeOsat (int pituus){
         
      TreeSet<String> osat = new TreeSet<String>();
        for (int i = 0; i < dna.length() - pituus + 1; i++) {
            String osa = dna.substring(i, i + pituus);
            osat.add(osa);
        }
        return osat;
    }
     
      public TreeMap<String, Integer> haeOsienMaarat(int pituus) {
        TreeMap<String, Integer> osat = new TreeMap<String, Integer>();
        for (int i = 0; i < dna.length() - pituus + 1; i++) {
            String sub = dna.substring(i, i + pituus);
            if (osat.containsKey(sub)) {
                osat.put(sub, osat.get(sub) + 1);
            } else {
                osat.put(sub, 1);
            }
        }
        return osat;
    }
    
    public TreeMap<Integer, String> haeLausekkeella(String lauseke) {
        TreeMap<Integer, String> tulos = new TreeMap<Integer, String>();
        Pattern pohja = Pattern.compile(lauseke);
        Matcher hakija = pohja.matcher(dna);
        while (hakija.find()) {
            tulos.put(hakija.start(), hakija.group());
        }
        return tulos;
    }
}