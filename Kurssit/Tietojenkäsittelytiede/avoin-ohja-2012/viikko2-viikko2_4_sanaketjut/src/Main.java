import java.util.*;

public class Main {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        aloitaPeli();
    }
    
    public static void aloitaPeli() {
        Tarkistaja tarkistaja = new Tarkistaja("sanalista.txt");
        Tekoaly tekoaly = new Tekoaly(tarkistaja);
        while (true) {
            System.out.print("Anna sana: ");
            String sana = input.nextLine();
            if (sana.equals("")) {
                System.out.println("Hävisit pelin!");
                break;
            }
            int tulos = tarkistaja.lisaaSana(sana);
            if (tulos == 1) {
                System.out.println("Sanaa ei ole sanastossa!");
                continue;
            }
            if (tulos == 2) {
                System.out.println("Sana on jo esiintynyt ketjussa!");
                continue;
            }
            if (tulos == 3) {
                System.out.println("Sana ei sovi ketjuun!");
                continue;
            }
            sana = tekoaly.valitseSana();
            if (sana.equals("")) {
                System.out.println("Voitit pelin!");
                break;
            } else {
                tarkistaja.lisaaSana(sana);
                System.out.println("Tekoälyn valinta: " + sana);
            }
        }
        System.out.println("Ketjun pituus: " + tarkistaja.ketjunPituus());
        System.out.println("Ketju: " + tarkistaja.haeKetju());        
    }
}
