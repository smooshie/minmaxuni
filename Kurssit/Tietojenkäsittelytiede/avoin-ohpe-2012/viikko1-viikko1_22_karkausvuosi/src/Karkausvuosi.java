public class Karkausvuosi {
    public static boolean karkausvuosi(int vuosi) {
        if(vuosi % 4 == 0) {
            if(vuosi % 100 != 0) {
                return true;
            } else if ((vuosi % 100 == 0) && (vuosi % 400 == 0)) {
                return true;
            } else {
                return false;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(karkausvuosi(2012));
        System.out.println(karkausvuosi(2013));
        System.out.println(karkausvuosi(1900));
        System.out.println(karkausvuosi(2000));
    }
}
