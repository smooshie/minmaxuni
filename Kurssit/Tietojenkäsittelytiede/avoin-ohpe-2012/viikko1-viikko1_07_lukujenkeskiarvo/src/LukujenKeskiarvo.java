import java.util.*;

public class LukujenKeskiarvo {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Anna luku 1: ");
                int luku1 = Integer.parseInt(input.nextLine());
        System.out.print("Anna luku 2: ");
                int luku2 = Integer.parseInt(input.nextLine());
        double keskiarvo = (double)(luku1 + luku2) / 2;
        System.out.print("Keskiarvo on: " + keskiarvo);
    }
}
