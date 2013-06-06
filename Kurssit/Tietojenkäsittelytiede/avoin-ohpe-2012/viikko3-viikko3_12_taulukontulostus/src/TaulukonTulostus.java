public class TaulukonTulostus {
    public static void tulostaTaulukko(int[] taulukko) {
        
        System.out.print("[");
        for (int i = 0; i < taulukko.length; i++) {
            System.out.print(taulukko[i]);
            if (i != taulukko.length - 1) {
            System.out.print(", ");
            }
        }
        System.out.println("]");
}    
    public static void main(String[] args) {
        int[] a = {2, 8, 1, 9, 7, 6};
        int[] b = {1, 5, 9, 4, 5, 2, 3};
        tulostaTaulukko(a);
        tulostaTaulukko(b);
    }
}
