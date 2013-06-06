public class HymionTulostus {
    public static void tulostaHymio(int valinta) {
        switch(valinta) {
            case 1:
                System.out.println("(^_^)");
                break;
            case 2:
                System.out.println("(O_o)");
                break;
            case 3:
                System.out.println("(;_;)");
                break;
        }
    }
    
    public static void main(String[] args) {
        tulostaHymio(1);
        tulostaHymio(3);
        tulostaHymio(1);
        tulostaHymio(2);   
    }
}
