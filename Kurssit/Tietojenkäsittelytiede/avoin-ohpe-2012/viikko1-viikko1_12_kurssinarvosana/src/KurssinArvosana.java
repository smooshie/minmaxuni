import java.util.*;

public class KurssinArvosana {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
      int arvosana;
      System.out.print("Anna pistemäärä: ");
      int pisteet = Integer.parseInt(input.nextLine());
      
      if(pisteet > 49) {
          System.out.println("Kurssin arvosana: 5");
      } else if(pisteet > 44) {
          System.out.println("Kurssin arvosana: 4");
      } else if(pisteet > 39) {
          System.out.println("Kurssin arvosana: 3");
      } else if(pisteet > 34) {
          System.out.println("Kurssin arvosana: 2");         
      } else if(pisteet > 29) {
          System.out.println("Kurssin arvosana: 1");
      } else {
          System.out.println("Kurssin arvosana: 0");          
      }
    }
}
