import java.util.*;

public class NimetVaarinpain {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Montako nimeä? ");
        int maara = Integer.parseInt(input.nextLine());
        
        String[] nimet = new String[maara];
        
        for (int i = 0; i < maara; i++) {
            System.out.print("Anna nimi: ");
            nimet[i] = input.nextLine();  
        }
        
        System.out.println("Nimet väärinpäin: ");
        
        for (int j = (maara - 1); j >= 0; j--) {
            System.out.println(nimet[j]);
        }
     } 
}

