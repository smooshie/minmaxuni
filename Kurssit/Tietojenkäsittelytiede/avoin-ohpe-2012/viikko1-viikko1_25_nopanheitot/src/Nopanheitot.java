public class Nopanheitot {
    public static int nopat(int n) {
        if(n == 1) {
            return 1;
        } else if(n == 2) {
            return 2;
        } else if(n == 3) {
            return 4;
        } else if(n == 4) {
            return 8;
        } else if(n == 5) {
            return 16; 
        } else if(n == 6) {
            return 32;
        } else {
            return nopat(n - 6) + nopat(n - 5) + nopat(n - 4) + nopat(n - 3) + nopat(n - 2) + nopat(n - 1);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(nopat(4));
        System.out.println(nopat(8));
        System.out.println(nopat(11));
        System.out.println(nopat(14));
    }
}