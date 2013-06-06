public class Kirjainmaarat {
    
    public static int vokaalienMaara(String merkkijono) {
        
        String uusi = merkkijono.toLowerCase();
        int laskuri = 0;
        int pituus = uusi.length();
        for (int i = 0; i < pituus; i++) { 
            
            if ( uusi.charAt(i) == 'a' || uusi.charAt(i) == 'e' || uusi.charAt(i) == 'i' || uusi.charAt(i) == 'o' || uusi.charAt(i) == 'u' || uusi.charAt(i) == 'y' || uusi.charAt(i) == 'ä' || uusi.charAt(i) == 'ö')
            laskuri++; 
            }
            return laskuri;
        }
    
    public static int konsonanttienMaara(String merkkijono) {
        
        String uusi = merkkijono.toLowerCase();
        int laskuri = 0;
        int pituus = uusi.length();
        for (int i = 0; i < pituus; i++) { 
            
            if ( uusi.charAt(i) == 'b' || uusi.charAt(i) == 'c' || uusi.charAt(i) == 'd' || uusi.charAt(i) == 'f' || uusi.charAt(i) == 'g' || uusi.charAt(i) == 'h' || uusi.charAt(i) == 'j' || uusi.charAt(i) == 'k' || uusi.charAt(i) == 'l' || uusi.charAt(i) == 'm' || uusi.charAt(i) == 'n' || uusi.charAt(i) == 'p' || uusi.charAt(i) == 'q' || uusi.charAt(i) == 'r' || uusi.charAt(i) == 's' || uusi.charAt(i) == 't' || uusi.charAt(i) == 'v' || uusi.charAt(i) == 'w' || uusi.charAt(i) == 'x' || uusi.charAt(i) == 'z')
            laskuri++; 
            }
            return laskuri;
        }
    
    public static void main(String[] args) {   
        String lause = "Alussa olivat suo, kuokka - ja Java.";
        System.out.println(vokaalienMaara(lause));
        System.out.println(konsonanttienMaara(lause));
    }
    
}
