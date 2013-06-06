import java.util.*;

public class IanKysyminen {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
            while (true) {
            System.out.print("Anna ikä: ");
            int ika = Integer.parseInt(input.nextLine());
            if (ika >= 1 && ika <= 120) {
                System.out.println("Kiitos!");
                break;       
            }
                System.out.println("Älä huijaa!");

            }
    }
}
