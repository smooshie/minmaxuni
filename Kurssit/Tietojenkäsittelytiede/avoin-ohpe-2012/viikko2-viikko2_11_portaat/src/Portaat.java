public class Portaat {
    public static void portaat(int korkeus) {

        String tahti = "";
        for (int i = 1; i <= korkeus; i++) {
            tahti += "*";
            System.out.println(tahti);
}
        
    }
    public static void main(String[] args) {
        System.out.println("Koko 3:");
        portaat(3);
        System.out.println("Koko 5:");
        portaat(5);
    }
}