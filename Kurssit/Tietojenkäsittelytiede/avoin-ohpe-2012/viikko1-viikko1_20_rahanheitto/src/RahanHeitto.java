public class RahanHeitto {
    public static int arvoLuku(int alaraja, int ylaraja) {
        return alaraja + (int)(Math.random() * (ylaraja - alaraja + 1));
    }
    
    public static String heitaRahaa() {
        int rand = arvoLuku(0, 1);
        
        if(rand == 0) {
            return "kruuna";
        } else {
           return "klaava";
        }
      
    }
    
    public static void main(String[] args) {
        System.out.println(heitaRahaa());
        System.out.println(heitaRahaa());
        System.out.println(heitaRahaa());
        System.out.println(heitaRahaa());
    }
}
