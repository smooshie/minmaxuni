public class KielenTunnistaminen {
    
        public static boolean vokaali(char merkki) {
        return "aeiouyåäö".indexOf(merkki) >= 0;
    }
 
    public static boolean konsonantti(char merkki) {
        return "bcdfghjklmnpqrstvwxz".indexOf(merkki) >= 0;
    }    
     
    public static int vokaalienMaara(String merkkijono) {
        merkkijono = merkkijono.toLowerCase();
        int maara = 0;
        for (int i = 0; i < merkkijono.length(); i++) {
            if (vokaali(merkkijono.charAt(i))) {
                maara++;
            }
        }
        return maara;
    }
     
    public static int konsonanttienMaara(String merkkijono) {
        merkkijono = merkkijono.toLowerCase();
        int maara = 0;
        for (int i = 0; i < merkkijono.length(); i++) {
            if (konsonantti(merkkijono.charAt(i))) {
                maara++;
            }
        }
        return maara;
    }    
     
    public static double vokaalisuhde(String merkkijono) {
        int vokaalit = vokaalienMaara(merkkijono);
        int konsonantit = konsonanttienMaara(merkkijono);
        double suhde =  (double)vokaalit / (double)(vokaalit + konsonantit);
        return suhde;
    }
    
    public static String kieli(String merkkijono) {
           double suhde = vokaalisuhde(merkkijono);       
           if (suhde  >= 0.44) {
               return "suomi";
           } else {
               return "englanti";
           
        }
    }
    
    public static void main(String[] args) {
        String lause1 = "Alussa olivat suo, kuokka - ja Java.";
        String lause2 = "The quick brown fox jumps over a lazy dog.";
        System.out.println(kieli(lause1));
        System.out.println(kieli(lause2));
    }    
}
