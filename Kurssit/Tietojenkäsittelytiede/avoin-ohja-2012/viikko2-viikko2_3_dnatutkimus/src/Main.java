public class Main {
    public static void main(String[] args) {
        // tässä voit testailla luokkaa DNA
        // BEGIN SOLUTION
        lausekkeet(new DNA("dna.txt"));
        //END SOLUTION
    }  
   
    // BEGIN SOLUTION
    public static void lausekkeet(DNA dna) {      
        System.out.println(dna.haeLausekkeella("CACTGAAAAG|ACTGCGCTAA"));
        System.out.println(dna.haeLausekkeella("A.CC.GGG.TTTT"));
        System.out.println(dna.haeLausekkeella("[AC]{20}"));
        System.out.println(dna.haeLausekkeella("(A[CGT]){8}"));
        System.out.println(dna.haeLausekkeella("A{5}(C{5}|G{5}|T{5})|C{5}(A{5}|G{5}|T{5})|G{5}(A{5}|C{5}|T{5})|T{5}(A{5}|C{5}|G{5})"));
    }
    // END SOLUTION
 
}