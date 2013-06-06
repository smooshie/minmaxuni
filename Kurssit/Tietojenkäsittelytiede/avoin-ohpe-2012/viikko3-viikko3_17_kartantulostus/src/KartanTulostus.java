public class KartanTulostus {
    
    public static void tulostaKartta(int[][] kartta) {

       for (int i = 0; i < kartta.length; i++) {
           for (int j = 0; j < kartta[i].length; j++) {
               if (kartta[i][j] == 0) {
                  System.out.print(".");
               }
               if (kartta[i][j] == 1) {
                  System.out.print("#");
               }
               if (kartta[i][j] == 2) {
                  System.out.print("@");
               }
         }   
          System.out.println(""); 
       }
       
    }
    
    public static void main(String[] args) {
        int[][] kartta = {{1, 1, 1, 1, 1},
                          {1, 0, 2, 0, 1},
                          {1, 0, 1, 0, 1},
                          {1, 1, 1, 0, 1},
                          {1, 0, 0, 0, 1},
                          {1, 1, 1, 1, 1}};
        tulostaKartta(kartta);
    }
}
