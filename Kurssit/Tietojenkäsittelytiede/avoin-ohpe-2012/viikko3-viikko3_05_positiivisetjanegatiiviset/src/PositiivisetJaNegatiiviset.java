import java.util.*;

public class PositiivisetJaNegatiiviset {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        int counter = 0;
        int pos = 0;
        int neg = 0;
        while (true) {
            System.out.print("Anna luku: ");
            int luku = Integer.parseInt(input.nextLine());
            
            if (luku != 0) {
                if (luku < 0) {
                   neg++;
                }
                else {
                    pos++;
                }
                counter += luku;
            }
            else {
            System.out.println("Positiivisia: " + pos);
            System.out.println("Negatiivisia: " + neg);
            break;
            }
        }
    }
}
