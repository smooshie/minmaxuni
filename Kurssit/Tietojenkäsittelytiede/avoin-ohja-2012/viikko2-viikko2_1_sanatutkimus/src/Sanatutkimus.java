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

public class Sanatutkimus {

    public ArrayList<String> lista = new ArrayList<String>();
    
    public Sanatutkimus(String tiedostonimi) {
        try {
            Scanner lukija = new Scanner(new File(tiedostonimi));
            while(lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                this.lista.add(rivi);
            }
        } catch(Exception e) {
                System.out.println("Kauhea virhe sanojen lukemisessa!");
            
        }
    }
    
    public int sanojenMaara() {
        return this.lista.size();
    }
    
    public boolean onkoSanaa(String sana) {
        return this.lista.contains(sana);
    }
    
    public int laskeSanat(int pituus) {
        int counter = 0;
        for(String sana : this.lista) {
            if(sana.length() == pituus) {
                counter++;
            }
        }
        return counter;
    }
    
    public void pituustilasto() {
        int[] tilasto = new int[31];
        
        for(String sana : this.lista) {
            if(sana.length() <= 30) {
                tilasto[sana.length()]++;
            }
        }
        
        for(int i = 1; i < 31; i++) {
            System.out.println(i + " " + tilasto[i]);
        }
        
    }
    
    public int laskeKirjaimet(char kirjain) {
        int counter = 0;
        
        for(String sana : this.lista) {
            int currentIndex = 0;

            while(true) {
                if(sana.indexOf(kirjain, currentIndex) == -1) {
                    break;
                } else {
                    currentIndex = sana.indexOf(kirjain, currentIndex) + 1;
                    counter++;
                }
            }
        }
        
        return counter;
    }
    
    public void kirjaintilasto() {
        TreeMap<Character, Integer> tilasto = new TreeMap<Character, Integer>();
        for (String sana : this.lista) {
            for (int i = 0; i < sana.length(); i++) {
                if (tilasto.containsKey(sana.charAt(i))) {
                    int temp = tilasto.get(sana.charAt(i)) + 1;
                    tilasto.put(sana.charAt(i), temp);
                } else {
                    tilasto.put(sana.charAt(i), 1);
                }
        }   
            
            }
        String aakkoset = "abcdefghijklmnopqrstuvwxyzåäö";
        for (int i = 0; i < aakkoset.length(); i++) {
                System.out.println(aakkoset.charAt(i) + " " + tilasto.get(aakkoset.charAt(i)));
        }
    }
    
     public ArrayList<String> haePituudella(int pituus) {
        ArrayList<String> sanat = new ArrayList<String>();
        
        for(String sana : this.lista) {
            if(sana.length() == pituus) {               
                sanat.add(sana);
            }
        }
        
        return sanat;
    }
     public ArrayList<String> haeOsalla(String osa) {
       ArrayList<String> sanat = new ArrayList<String>();
        
        for(String sana : this.lista) {
            if(sana.contains(osa)) {               
                sanat.add(sana);
            }
        }
        
        return sanat;       
     }
     public ArrayList<String> haePalindromit() {
         ArrayList<String> sanat = new ArrayList<String>();
          for(String sana : this.lista) {
            boolean onPalindromi = true;
            
            for(int i = 0; i < sana.length(); i++) {
                if(sana.charAt(i) != sana.charAt(sana.length() - 1 - i)) {
                   onPalindromi = false;
                }
            }
            
            if(onPalindromi) {
                sanat.add(sana);
            }
        }
         return sanat;    
     }
     public ArrayList<String> haeRistikkoon(String pohja) {
             ArrayList<String> sanat = new ArrayList<String>();
             for(String sana : this.lista) {
                boolean veltsu = true;
                if (sana.length() == pohja.length()) {
                for (int i = 0; i < pohja.length(); i++) {
                     if (pohja.charAt(i) != sana.charAt(i) && pohja.charAt(i) != '?') {
                        veltsu = false;
                        break;
                    }
                }
                if (veltsu == true) {
                    sanat.add(sana);
                }
                
                }
            }       
             return sanat;
     }
     
public ArrayList<String> haeAnagrammit(String sana) {
         ArrayList<String> sanat = new ArrayList<String>();
         char[] sanatChars = sana.toCharArray();
         Arrays.sort(sanatChars);
         
         for(String vertausSana : this.lista) {
             char [] vertausSanatChars = vertausSana.toCharArray();
             Arrays.sort(vertausSanatChars);
             
             if(Arrays.equals(sanatChars, vertausSanatChars)) {
                 sanat.add(vertausSana);
             }
         }
         
         return sanat;
    }
}