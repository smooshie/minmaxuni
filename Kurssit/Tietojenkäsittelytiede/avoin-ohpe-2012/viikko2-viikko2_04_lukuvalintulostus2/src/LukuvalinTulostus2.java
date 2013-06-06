import java.util.*;

public class LukuvalinTulostus2 {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Anna luku 1: ");
        int luku1 = Integer.parseInt(input.nextLine());
        System.out.print("Anna luku 2: ");
        int luku2 = Integer.parseInt(input.nextLine());
        
        if (luku1 > luku2) {
            for (int i = luku1; i >= luku2; i--) {
                System.out.println(i); }
        } else {
            for (int i = luku1; i <= luku2; i++) {
                System.out.println(i); }
            }
        }
}