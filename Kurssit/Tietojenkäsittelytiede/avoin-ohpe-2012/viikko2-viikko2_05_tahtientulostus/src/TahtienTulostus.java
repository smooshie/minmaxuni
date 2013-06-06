public class TahtienTulostus {
    public static void tulostaTahtia(int maara) {
        String tyhja = "";
        for (int i = 0; i < maara; i++) {
           tyhja += "*";
       }
        System.out.println(tyhja);
    }
    
    public static void main(String[] args) {
        tulostaTahtia(5);
        tulostaTahtia(10);
        tulostaTahtia(8);
    }
}
