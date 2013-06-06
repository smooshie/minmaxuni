public class PisinToisto {
    public static int pisinToisto(String dna) {
        
        int pisin = 0;
        int counter = 0;
        
        for (int i = 0; i < dna.length(); i++) {
            int j = i;
            while (dna.charAt(i) == dna.charAt(j)) {
                    j++;
                    counter++;
                     if (counter > pisin) {
                       pisin = counter;
                    } if (j == dna.length()) {
                        break;
                    }
            }
            
            counter = 0;
        }
        return pisin;
    }
    
    public static void main(String[] args) {
        System.out.println(pisinToisto("ACGGAT"));
        System.out.println(pisinToisto("AATCGAAATTCAGG"));
        System.out.println(pisinToisto("GGGGAGGGG"));  
    }
}
