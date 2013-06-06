public class Suorakulmio {
    public static void suorakulmio(int leveys, int korkeus) {
        
        String tahti = "";
        
        for (int i = 1; i <= leveys; i++) {
            tahti += "*";
        }
            for (int i = 0; i < korkeus; i++) {
                System.out.println(tahti);
            }
            
    }
    
    public static void main(String[] args) {
        System.out.println("Koko 8x3:");
        suorakulmio(8, 3);
        System.out.println("Koko 4x4:");
        suorakulmio(4, 4);
        System.out.println("Koko 10x2:");
        suorakulmio(10, 2);
    }
}
