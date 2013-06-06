public class KorkeaKuusi {
    
    public static void kuusi(int korkeus) {
        
        String tahti = "";
    
    
    for (int i = 1; i <= korkeus; i += 2) {
        tahti += "*";
        System.out.println(i + tahti);
    }
      
            

    }
    
    public static void main(String[] args) {
        System.out.println("Koko 3:");
        kuusi(3);
        System.out.println("Koko 5:");
        kuusi(5);
    }
}
