public class Kuningattaret {
    
    public static int kuningattaret(int koko) {

        int places = 0;
        
        
        for(int i = 0; i < Math.ceil(koko / 2); i++) {
            double kokosquared = Math.pow(koko, 2);
            places += (4 * (koko - i) - 4) * (kokosquared - (2 * koko - 1) - (koko - 1 + (2 * i)));
            
        }
        return places/2;
    }
    
    public static void main(String[] args) {
        System.out.println(kuningattaret(5));
        System.out.println(kuningattaret(10));
        System.out.println(kuningattaret(50));
    }
}
