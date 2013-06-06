import java.util.*;

public class Main {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        // ota tästä kommentti pois, niin pääset pelaamaan :)
        aloitaPeli();
    }
    
    public static void aloitaPeli() {
        Pelilauta pelilauta = new Pelilauta();
        pelilauta.uusiPeli();
        System.out.println("Tervetuloa 15-peliin!");
        System.out.println("Valinnat:");
        System.out.println("y = ylös, a = alas, v = vasen, o = oikea");
        System.out.println("l = lopetus");
        System.out.println();
        while (true) {
            System.out.print(pelilauta);
            System.out.println("Siirrot: " + pelilauta.haeSiirrot());
            if (pelilauta.valmis()) {
                System.out.println("Onnittelut, ratkaisit pelin!");
                break;
            }
            System.out.print("Anna valinta: ");
            String valinta = input.nextLine();
            if (valinta.equals("y")) {
                pelilauta.ylos();
            }
            if (valinta.equals("a")) {
                pelilauta.alas();
            }
            if (valinta.equals("v")) {
                pelilauta.vasen();
            }
            if (valinta.equals("o")) {
                pelilauta.oikea();
            }
            if (valinta.equals("l")) {
                break;
            }
        }        
    }
}
