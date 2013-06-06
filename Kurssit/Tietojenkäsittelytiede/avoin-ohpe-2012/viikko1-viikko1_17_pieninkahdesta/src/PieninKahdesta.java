public class PieninKahdesta {
    public static int pienin2(int a, int b) {
        if(a < b) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {
        System.out.println(pienin2(2, 5));
        System.out.println(pienin2(5, 2));
        System.out.println(pienin2(3, 3));
    }
}
