public class SuomestaEnglanniksi {
    public static String englanniksi(String suomeksi) {

        String englanniksi = "";
        
        if(suomeksi.equals("apina")) {
            englanniksi = "monkey";
        } else if(suomeksi.equals("cembalo")) {
            englanniksi = "harpsichord";
        } else if(suomeksi.equals("banaani")) {
            englanniksi = "banana";
        } else {
            englanniksi = "Error 37";
        }
        
        return englanniksi;
    }
    
    public static void main(String[] args) {
        System.out.println(englanniksi("apina"));
        System.out.println(englanniksi("cembalo"));
        System.out.println(englanniksi("apina"));
        System.out.println(englanniksi("banaani"));
    }
}
