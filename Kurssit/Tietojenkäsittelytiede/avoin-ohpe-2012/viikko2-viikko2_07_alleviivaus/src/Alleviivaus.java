public class Alleviivaus {
    public static void alleviivaa(String teksti) {
        
    int pituus = teksti.length();
    String VELTSUKATTOO = "";
    for (int i = 0; i < pituus; i++) {
       VELTSUKATTOO += "=";
    }
    System.out.println(teksti);
    System.out.println(VELTSUKATTOO);
    }
    
    public static void main(String[] args) {
        alleviivaa("apina");
        alleviivaa("banaani");
        alleviivaa("cembalo");
    }
}