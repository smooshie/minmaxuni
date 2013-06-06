public class PythagoraanKolmikot {
    public static int pythagoras(int ylaraja) {
        
        int counter = 0;
        
        
        for (int i=1; i<=ylaraja; i++) {
            for (int j=1; j<=ylaraja; j++) {
                double pythagoras = Math.pow(i, 2) + Math.pow(j, 2);
                double square = Math.sqrt(pythagoras);
                if (square <= ylaraja && Math.floor(square) == square) {
                    counter++;
                }
                
            }
        }
        return counter;
    }
    
    public static void main(String[] args) {
        System.out.println(pythagoras(10));
        System.out.println(pythagoras(50));
        System.out.println(pythagoras(100));
    }
}
