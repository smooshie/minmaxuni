public class MerkkienTulostus {
    public static void tulostaMerkkeja(int maara, char merkki) {
                 String tyhja = "";
                    for (int i = 0; i < maara; i++) {
                    tyhja += merkki;
                    }
            System.out.println(tyhja);
            }
    
    public static void main(String[] args) {
        tulostaMerkkeja(5, '*');
        tulostaMerkkeja(10, '!');
        tulostaMerkkeja(8, 'q');
    }
}