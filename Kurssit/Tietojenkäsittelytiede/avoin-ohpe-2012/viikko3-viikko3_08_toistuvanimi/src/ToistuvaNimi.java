import java.util.*;

public class ToistuvaNimi {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        String[] nimet = new String[100];
        
        for (int i = 0; i < 100; i++) {
        System.out.print("Anna nimi: ");
        nimet[i] = input.nextLine(); 
            for (int j = i - 1; j >= 0; j--) {
                if (nimet[i].equals(nimet[j])) {
                    System.out.println("Annoit saman nimen uudestaan!");
                    return;
                }
            }
        }
    }
}