public class Tervehdys {
    public static void tervehdi(String nimi) {
        for(int x = 0; x < 3; x++) {
            System.out.println("Hei, " + nimi + "!");
        }
    }
    
    public static void main(String[] args) {
        tervehdi("Aapeli");
        tervehdi("Maija");
    }
}
