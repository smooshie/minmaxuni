public class Tahtikehys {
    
    public static void kehysta(String teksti) {

        int pituus = teksti.length();
        String rivi = "****";

        for (int i = 0; i < pituus; i++) {
        rivi += "*";
        }
        
        System.out.println(rivi);
        System.out.println("* " + teksti + " *");
        System.out.println(rivi);
    
    }
    
    public static void main(String[] args) {
        kehysta("apina");
        kehysta("banaani");
        kehysta("cembalo");
    }
}
