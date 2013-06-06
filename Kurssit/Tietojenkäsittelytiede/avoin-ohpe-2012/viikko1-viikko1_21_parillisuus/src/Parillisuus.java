public class Parillisuus {
    public static boolean parillinen(int luku) {
        if(luku % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(parillinen(16));
        System.out.println(parillinen(17));
        System.out.println(parillinen(0));
        System.out.println(parillinen(-3));
    }
}
