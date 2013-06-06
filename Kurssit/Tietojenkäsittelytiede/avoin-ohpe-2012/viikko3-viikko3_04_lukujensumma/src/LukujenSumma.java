import java.util.*;

public class LukujenSumma {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        int counter = 0;
        while (true) {
            System.out.print("Anna luku: ");
            int luku = Integer.parseInt(input.nextLine());
            
            if (luku != 0) {
                counter += luku;
            }
            else {
            System.out.println("Summa: " + counter);
            break;
            }
        }
    }
}