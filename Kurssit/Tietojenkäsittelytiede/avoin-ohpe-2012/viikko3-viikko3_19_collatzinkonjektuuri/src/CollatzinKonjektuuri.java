public class CollatzinKonjektuuri {
    public static int laskeOuto(int luku) {
        
        int counter = 0;
         while (true) { 
            if (luku == 1) {
                counter++;
                break; 
            }
            else if (luku % 2 == 0) {
                luku = luku / 2;
                counter++;
                
            }
            else {
                luku = luku * 3 + 1;
                counter++;
            }
            }
       return counter;
    }
    
    public static int pisinOuto(int alaraja, int ylaraja) {
        
        int[] pisin = new int[2];
        
        for (int i = alaraja; i <= ylaraja; i++) {
            
            int temp = laskeOuto(i);
            
            if (temp > pisin[0]) {
                pisin[1] = i;
                pisin[0] = temp;
            }
        }
        
        return pisin[1];
    }
    
    public static void main(String[] args) {
        System.out.println(laskeOuto(3));
        System.out.println(laskeOuto(7));
        System.out.println(laskeOuto(23));
        System.out.println(pisinOuto(1, 100));
        System.out.println(laskeOuto(pisinOuto(1, 100)));
    }
}
