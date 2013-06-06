public class VokaalitJaKonsonantit {
    public static boolean vokaali(char merkki) {
       
       String vokaalit = "aeiouyåäö";
        
       int vokut;
       vokut = vokaalit.indexOf(merkki);
       
           if (vokut == -1) {
                return false;
            } else {
                return true;
            }
        }        

    public static boolean konsonantti(char merkki) {
        
        String konsonantit = "bcdfghjklmnpqrstvwxz";
        
        int konsut;
       konsut = konsonantit.indexOf(merkki);
       
           if (konsut == -1) {
                return false;
            } else {
                return true;
            }
        } 

    public static void main(String[] args) {
        System.out.println(vokaali('o'));
        System.out.println(konsonantti('o'));
        System.out.println(vokaali('k'));
        System.out.println(konsonantti('k'));
        System.out.println(vokaali('4'));
        System.out.println(konsonantti('4'));
    }
}
