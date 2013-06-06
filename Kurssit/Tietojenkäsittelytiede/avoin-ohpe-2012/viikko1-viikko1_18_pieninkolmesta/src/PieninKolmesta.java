public class PieninKolmesta {
    
    public static int pienin3(int a, int b, int c) {

        int ab = pienin2(a, b);
        int cb = pienin2(c, b);
        
        return pienin2(ab, cb);
    }
    public static int pienin2(int a, int b) {
        if(a < b) {
            return a;
        } else {
            return b;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(pienin3(3, 2, 8));
        System.out.println(pienin3(5, 7, 9));
        System.out.println(pienin3(5, 2, 2));
        System.out.println(pienin3(8, 8, 1));
    }
}
