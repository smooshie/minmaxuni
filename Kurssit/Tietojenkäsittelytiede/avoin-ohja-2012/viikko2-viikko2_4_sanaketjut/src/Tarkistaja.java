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

public class Tarkistaja {
    private ArrayList<String> sanat = new ArrayList<String>();
    private ArrayList<String> ketju = new ArrayList<String>();
    private String vanhaSana = "";
    
    public Tarkistaja(String tiedostonimi) {
        try {
            Scanner lukija = new Scanner(new File(tiedostonimi));
            while (lukija.hasNextLine()) {
                String sana = lukija.nextLine();
                sanat.add(sana);
            }
        } catch (Exception e) {
            System.out.println("Virhe lataamisessa!");
        }        
    }
      
    public int lisaaSana(String uusiSana) {
        if (!sanat.contains(uusiSana)) {
            return 1;
        }
        if (ketju.contains(uusiSana)) {
            return 2;
        }
        if (!vanhaSana.equals("") && !ketjunAskel(vanhaSana, uusiSana)) {
            return 3;
        }
        ketju.add(uusiSana);
        vanhaSana = uusiSana;
        return 0;
    }
    
    public ArrayList<String> haeSeuraavat() {
        ArrayList<String> tulos = new ArrayList<String>();
        for (String uusiSana : sanat) {
            if (ketjunAskel(vanhaSana, uusiSana) && !ketju.contains(uusiSana)) {
                tulos.add(uusiSana);
            }
        }
        return tulos;
    }

    public int ketjunPituus() {
        return ketju.size();
    }
    
    public ArrayList<String> haeKetju() {
        return ketju;
    }
  
    private boolean onkoMuutos(String vanha, String uusi) {
        if (vanha.length() != uusi.length()) {
            return false;
        }
        int muutokset = 0;
        for (int i = 0; i < vanha.length(); i++) {
            if (vanha.charAt(i) != uusi.charAt(i)) {
                muutokset++;
            }
        }
        return muutokset == 1;
    }
    
    private boolean onkoLisays(String vanha, String uusi) {
        if (vanha.length() != uusi.length() - 1) {
            return false;
        }
        int j = 0;
        for (int i = 0; i < uusi.length(); i++) {
            if (j < vanha.length() && uusi.charAt(i) == vanha.charAt(j)) {
                j++;
            }
        }
        return j == vanha.length();
    }
    
    private boolean onkoPoisto(String vanha, String uusi) {
        return onkoLisays(uusi, vanha);
    }
    
    private boolean ketjunAskel(String vanha, String uusi) {
        return onkoMuutos(vanha, uusi) ||
               onkoLisays(vanha, uusi) ||
               onkoPoisto(vanha, uusi);
    }
}
