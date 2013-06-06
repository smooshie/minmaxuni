import java.util.*;

public class Valikko {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {

        while (true) {
            
            System.out.print("Anna komento: ");
            int komento = Integer.parseInt(input.nextLine());
            
            if (komento == 1) 
            {
            System.out.println("Morjens!");
            continue;
            }
                
            else if (komento == 2) 
            {
            System.out.println("Hei vain!");
            }  
            
            if (komento == 3)
            {
            break; 
            }
        }
    }
}