public class Merkkikehys {
    
    public static void kehysta(String teksti, char merkki) {
        int pituus = teksti.length();
        String rivi = "";
      
        for (int i = 0; i < pituus + 4; i++) {
        rivi += merkki;
        }
        
        // System.out.print(merkki + "" + merkki + "" + merkki + "" + merkki);
        System.out.println(rivi);
        System.out.println(merkki + " " + teksti + " " + merkki);
        // System.out.print(merkki + "" + merkki + "" + merkki + "" + merkki);
        System.out.println(rivi);
    
    }
    
    
    public static void main(String[] args) {
        kehysta("apina", '*');
        kehysta("banaani", '#');
        kehysta("cembalo", 'X');
    }
}