import java.util.*;

public class LukuvalinSumma {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
       System.out.print("Anna alaraja: ");
       int alaraja = Integer.parseInt(input.nextLine());
       System.out.print("Anna yläraja: ");
       int ylaraja = Integer.parseInt(input.nextLine());
             int sum = 0;
             for (int i = alaraja; i <= ylaraja; i++) {
                 sum += i; 
             }
             System.out.println(sum); 
    }
  }