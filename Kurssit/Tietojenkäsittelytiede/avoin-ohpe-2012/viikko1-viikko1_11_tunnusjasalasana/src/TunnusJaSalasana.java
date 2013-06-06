import java.util.*;

public class TunnusJaSalasana {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Anna tunnus: ");
        String tunnus = input.nextLine();
        System.out.print("Anna salasana: ");
        String salasana = input.nextLine();
        
        if (tunnus.equals("maija") && salasana.equals("kana")) { 
        System.out.println("Tervetuloa!"); }
        else if (tunnus.equals("aapeli") && salasana.equals("kissa")) {
            System.out.println("Tervetuloa!"); }
        else {
            System.out.println("Virhe!");
    
        }
    }
}
