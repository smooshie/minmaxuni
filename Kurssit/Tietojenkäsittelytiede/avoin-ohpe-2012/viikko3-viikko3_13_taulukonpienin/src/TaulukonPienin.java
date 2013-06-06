public class TaulukonPienin {
    public static int taulukonPienin(int[] taulukko) {
       int pienin = 1000;
       for (int i = 0; i < taulukko.length; i++) {
           if (taulukko[i] < pienin) {
               pienin = taulukko[i];
           }
       }
       return pienin;
    }
    
    public static void main(String[] args) {
        int[] a = {4, 2, -3, 9, -2};
        int[] b = {1, -1, 1, -1};
        int[] c = {3, 7, 2, 9, 5};
        System.out.println(taulukonPienin(a));
        System.out.println(taulukonPienin(b));
        System.out.println(taulukonPienin(c));
    }
}
