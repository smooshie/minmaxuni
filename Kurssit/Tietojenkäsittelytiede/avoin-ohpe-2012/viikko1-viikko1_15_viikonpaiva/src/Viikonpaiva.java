public class Viikonpaiva {
    public static String viikonpaiva(int numero) {

        String paiva = "";
        
        switch(numero) {
        case 1:
            paiva = "maanantai";
            break;
        case 2:
            paiva = "tiistai";
            break;
        case 3:
            paiva = "keskiviikko";
            break;
        case 4:
            paiva = "torstai";
            break;
        case 5:
            paiva = "perjantai";
            break;
        case 6:
            paiva = "lauantai";
            break;
        case 7:
            paiva = "sunnuntai";
            break;
        }
        
       return paiva;
    }
    
    public static void main(String[] args) {
        System.out.println(viikonpaiva(2));
        System.out.println(viikonpaiva(4));
        System.out.println(viikonpaiva(7));
    }
}
