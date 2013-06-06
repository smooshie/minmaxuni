public class Palindromiluvut {
    
    public static int palindromiluvut(int alaraja, int ylaraja) {
        int palindromi = 0;

        for (int i = alaraja; i <= ylaraja; i++) {
            int num = i;
            int rev = 0;
            int r = 0;
 
            for (int j=0; j<=num; j++){
                r = num%10;
                num = num/10;
                rev = rev*10+r;
                j = 0; 
            }
            
            if (i == rev) {
                palindromi++;
            }
        
        }
        
        return palindromi;
    
    }
    
    public static void main(String[] args) {
        System.out.println(palindromiluvut(50, 70));
        System.out.println(palindromiluvut(100, 200));
        System.out.println(palindromiluvut(12345, 54321));
    }
}
