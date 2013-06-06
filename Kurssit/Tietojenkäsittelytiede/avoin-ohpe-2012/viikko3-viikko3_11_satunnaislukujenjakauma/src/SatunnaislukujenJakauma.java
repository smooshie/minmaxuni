import java.util.*;
        
public class SatunnaislukujenJakauma {
    
    public static void main(String[] args) {
        int[] nopat = new int[7];
        Random generator = new Random();
        
    for (int i = 0; i < 1000000; i++) {
            int roll = generator.nextInt(6) + 1;
            nopat[roll]++;
             
        }
       for (int i = 1; i <= 6; i++) {
       System.out.println(i + ": " + nopat[i] + " kpl");
    }
}
}