public class OutoAlgoritmi {
    public static void tulostaOuto(int luku) {
        while (true) { 
            System.out.print(luku + " ");
            if (luku == 1) {
                break; 
            }
            else if (luku % 2 == 0) {
                luku = luku / 2;
            }
            else {
                luku = luku * 3 + 1;
            }
            
            }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        tulostaOuto(3);
        tulostaOuto(7);
        tulostaOuto(23);  
    }
}
