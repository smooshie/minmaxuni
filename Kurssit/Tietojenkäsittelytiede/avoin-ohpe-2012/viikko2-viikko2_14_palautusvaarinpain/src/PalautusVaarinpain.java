public class PalautusVaarinpain {
    public static String vaarinpain(String merkkijono) {
        
        int pituus = merkkijono.length();
        String takaperin = "";
        
        for (int i = pituus - 1; i >= 0; i--) {
        takaperin += merkkijono.charAt(i);      
        }
   return takaperin; }
        
public static void main(String[] args) {
        System.out.println(vaarinpain("apina"));
        System.out.println(vaarinpain("banaani"));
        System.out.println(vaarinpain("cembalo"));       
    }
}
