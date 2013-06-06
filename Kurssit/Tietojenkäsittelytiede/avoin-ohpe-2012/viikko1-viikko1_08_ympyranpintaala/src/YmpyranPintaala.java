import java.util.*;

public class YmpyranPintaala {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Anna ympyrän säde: ");
        int radius = Integer.parseInt(input.nextLine());
        double pintaala = ((radius * radius) * Math.PI);
        System.out.print("Pinta-ala: " + pintaala);
    }
}
