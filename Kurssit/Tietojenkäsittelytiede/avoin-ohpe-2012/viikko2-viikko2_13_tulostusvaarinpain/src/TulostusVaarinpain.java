public class TulostusVaarinpain {
    public static void tulostaVaarinpain(String merkkijono) {
        
int pituus = merkkijono.length();
 
for (int i = pituus - 1; i >= 0; i--) {
    System.out.print(merkkijono.charAt(i));
}
System.out.println();
    }
    public static void main(String[] args) {
        tulostaVaarinpain("apina");
        tulostaVaarinpain("banaani");
        tulostaVaarinpain("cembalo");
    }
}
