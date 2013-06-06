import java.util.*;

public class Lahtolaskenta {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Anna luku: ");
        int luku = Integer.parseInt(input.nextLine());
        
        if (luku >= 21) {
                System.out.println("En jaksa laskea!");
        } else {
            for (int i = luku; i >= 0; i--) {
                System.out.print(i + " ");
            }
        }
    }
}