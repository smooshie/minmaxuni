import java.util.*;

public class SalasananKysyminen {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        
        while (true) {
            System.out.print("Anna salasana: ");
            String salasana = input.nextLine();
            if (salasana.equals("kissa")) {
                System.out.println("Tervetuloa!");
                break;
            }
            System.out.println("Väärin");  
            }
    }
      }

