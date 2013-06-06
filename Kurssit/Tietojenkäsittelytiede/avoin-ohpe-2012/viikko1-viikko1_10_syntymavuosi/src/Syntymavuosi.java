import java.util.*;

public class Syntymavuosi {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Anna syntymävuosi: ");
            int vuosi = Integer.parseInt(input.nextLine());
        if( vuosi < 1900 || vuosi > 2010) {
            System.out.println("Taidat narrata!"); 
        }
        else {
            System.out.println("Täytät tänä vuonna " + (2012 - vuosi) + "vuotta!"); 
                    }
        }
    }
