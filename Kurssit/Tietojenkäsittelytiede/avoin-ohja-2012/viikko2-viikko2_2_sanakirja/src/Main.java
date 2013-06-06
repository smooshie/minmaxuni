public class Main {
    public static void main(String[] args) {
        


Sanakirja englannistaSuomeksi = new Sanakirja();
englannistaSuomeksi.lisaaSana("this", "tämä");
englannistaSuomeksi.lisaaSana("program", "ohjelma");
englannistaSuomeksi.lisaaSana("works", "toimii");
englannistaSuomeksi.lisaaSana("very", "erittäin");
englannistaSuomeksi.lisaaSana("well", "hyvin");
System.out.println(englannistaSuomeksi.kaannaLause("This program works very well."));
    }
}
