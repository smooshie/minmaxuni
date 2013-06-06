import java.util.*;

public class LukuvalinTulostus {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Anna alaraja: ");
        int alaraja = Integer.parseInt(input.nextLine());
        System.out.print("Anna yläraja: ");
        int ylaraja = Integer.parseInt(input.nextLine());
        
        for (int i = alaraja; i <= ylaraja; i++) {
            System.out.println(i);
        }
        
    }
}
