import java.util.*;

public class LukujenSumma {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Anna luku 1: ");
                int luku1 = Integer.parseInt(input.nextLine());
        System.out.print("Anna luku 2: ");
                int luku2 = Integer.parseInt(input.nextLine());
        System.out.print("Summa: " + (luku1 + luku2));
    }
}
