import java.util.*;

public class Koearvosanat {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        
int[] arvosanat = new int[6];

        while (true) {
            System.out.print("Anna arvosana: ");
            int luku = Integer.parseInt(input.nextLine());
            
            if (luku != 0) {
                arvosanat[luku]++; }
            else {
                System.out.println("Tilasto: ");
                for (int i = 5; i >=1; i--) {
                    System.out.println(i + ": " + arvosanat[i] + " kpl");
                }
                    
            break;
            }
        }
    }
}