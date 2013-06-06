/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author smoosh
 */

import java.util.*;

public class Pelilauta {
    
    int[][] taulukko;
    
    int tyhjaX;
    int tyhjaY;
    int siirrot;
    
    
    public Pelilauta() {
        taulukko = new int[4][4];
        int luku = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (luku == 16) {
                    luku = 0;
                }
                taulukko[i][j] = luku;
                luku++;
            }
        }
        
        tyhjaX = 3;
        tyhjaY = 3;
    }
    
    public int haeLuku(int y, int x) {
        if(y > 3 || x > 3 || x < 0 || y < 0) {
            return -1;
        } else {
            return taulukko[y][x];        
        }
    }
    
    public String toString() {
        
        String tayteRivi = "+--+--+--+--+\n";
        String lauta = "";
        
        for(int y = 0; y < 4; y++) {
            lauta += tayteRivi;
            for(int x = 0; x < 4; x++) {
                lauta += "|";
                
                if(this.taulukko[y][x] < 10) {
                    lauta += " ";
                }
                
                if(taulukko[y][x] != 0) {
                    lauta += taulukko[y][x];
                } else {
                    lauta += " ";
                }
            }
            lauta += "|\n";
        }
        
        lauta += tayteRivi;
        
        return lauta;
    }
    
    public void ylos() {
        this.siirto(1,0);
    }
    public void alas() {
        this.siirto(-1,0);
    }
    public void vasen() {
        this.siirto(0,1);
    }
    public void oikea() {
        this.siirto(0,-1);
    }
    
    public void siirto(int kohdeY, int kohdeX) {
        int uusiX = tyhjaX + kohdeX;
        int uusiY = tyhjaY + kohdeY;
        
        if (haeLuku(uusiY, uusiX) != -1) {
            int siirto = haeLuku(uusiY, uusiX);
            taulukko[tyhjaY][tyhjaX] = siirto;
            taulukko[uusiY][uusiX] = 0;
            tyhjaX = uusiX;
            tyhjaY = uusiY;
            siirrot++;
        }
    }
    
    public boolean valmis() {
        int luku = 1;
        for (int i = 0; i <4; i++) {
            for (int j = 0; j < 4; j++) {
                if (luku == 16) {
                    luku = 0;
                }
                if (taulukko[i][j] != luku) {
                    return false;
                }
                luku++;
            }
        }
        return true;
    }
    
    public int haeSiirrot() {
        return this.siirrot;
    }
    
    public void uusiPeli() {
        Random generator = new Random();
        
        for(int x = 0; x < 10000; x++) {
            
            int rnd = generator.nextInt(4);
            switch(rnd) {
                case 0: this.vasen();
                    break;
                case 1: this.oikea();
                    break;
                case 2: this.ylos();
                    break;
                case 3: this.alas();
                    break;
            }
        }
        
        siirrot = 0;
    }
}
