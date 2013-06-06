public class Palindromi {
    
    public static boolean palindromi(String merkkijono) {
        
       int pituus = merkkijono.length();
        String takaperin = "";
        
        for (int i = pituus - 1; i >= 0; i--) {
        takaperin += merkkijono.charAt(i);      
        }
            if (takaperin.equals(merkkijono)) {
                return true;
            } else {
                return false;
            }
    }
    
    public static void main(String[] args) {
        System.out.println(palindromi("enne"));
        System.out.println(palindromi("esimerkki"));
        System.out.println(palindromi("saippuakauppias"));   
    }
}