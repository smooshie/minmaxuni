public class KaksiSamaa {
    public static boolean kaksiSamaa(int[] taulukko) {
        
        boolean sama = false;
        
        for (int i = 0; i < taulukko.length; i++)
        {
         for (int j = 0; j < taulukko.length; j++)
         {
            if (taulukko[i] == taulukko[j] && i != j) {
               sama = true;
            }
         }
       }
        return sama;
    }

    
    public static void main(String[] args) {
        int[] a = {5, 2, 9, 2, 3};
        int[] b = {3, 5, 1, 9};
        int[] c = {1, 5, 2, 9, 2, 1};
        System.out.println(kaksiSamaa(a));
        System.out.println(kaksiSamaa(b));
        System.out.println(kaksiSamaa(c));
    }
}
