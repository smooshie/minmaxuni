public class Lottorivi {
    public static int tarkistaRivi(int[] oikea, int[] veikkaus) {
        
        int lotto = 0;
        
        for (int i = 0; i < oikea.length; i++)
        {
        for (int j = 0; j < veikkaus.length; j++)
         {
             if (oikea[i] == veikkaus[j])
             {  
                 lotto++;
             }
    }
        }
        return lotto;
    }
    public static void main(String[] args) {
        int[] oikea = {2, 7, 12, 14, 23, 30, 37};
        int[] veikkaus = {1, 6, 7, 10, 13, 14, 33};
        System.out.println(tarkistaRivi(oikea, veikkaus));
    }
}
