/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */
import java.util.*;

public class Muutoshistoria {
    private ArrayList<Integer> historia = new ArrayList<Integer>();
    
    public void lisaaMerkinta(int maara) {
        historia.add(maara);
    }
    
    public void tyhjenna() {
        historia.clear();
    }
    
    public String toString() {
        return historia.toString();
    }
    
    public int pienin() {
        return Collections.min(historia);
    }
    
    public int suurin() {
        return Collections.max(historia);
    }
    
    public double keskiarvo() {
        int summa = 0;
        for (int luku : historia) {
            summa += luku;
        }
        return (double)summa / historia.size();
    }
    
    public double varianssi() {
        double summa = 0;
        double keskiarvo = keskiarvo();
        for (int luku : historia) {
            summa += Math.pow(luku - keskiarvo, 2);
        }
        return summa / historia.size();
    }
    
    public void tulostaTilasto() {
        int maara = historia.size();
        int suurin = suurin();
        for (int i = 0; i < suurin; i++) {
            for (int j = 0; j < maara; j++) {
                if (historia.get(j) >= suurin - i) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }                
            }
            System.out.println();
        }
    }
    
}
