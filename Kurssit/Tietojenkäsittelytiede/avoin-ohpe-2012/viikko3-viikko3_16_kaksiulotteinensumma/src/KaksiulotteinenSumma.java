public class KaksiulotteinenSumma {
    public static int laskeSumma(int[][] taulukko) {
        int summa = 0;
        for (int i = 0; i < taulukko.length; i++) {
            for (int j = 0; j < taulukko[i].length; j++) {
            summa += taulukko[i][j];
        }}
        return summa;
    }
    
    public static void main(String[] args) {
        int[][] luvut = {{1, 2, 3},
                         {4, 5, 6},
                         {7, 8, 9}};
        System.out.println(laskeSumma(luvut));
    }
}
