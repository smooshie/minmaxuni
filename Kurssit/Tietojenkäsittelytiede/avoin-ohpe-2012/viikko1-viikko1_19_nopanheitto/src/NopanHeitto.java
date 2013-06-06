public class NopanHeitto {
    public static int arvoLuku(int alaraja, int ylaraja) {
        return alaraja + (int) (Math.random() * (ylaraja - alaraja + 1));
    }

    public static int heitaNoppaa() { 
        return arvoLuku(1, 6);
    }

    public static void main(String[] args) {
        System.out.println(heitaNoppaa());
        System.out.println(heitaNoppaa());
        System.out.println(heitaNoppaa());
        System.out.println(heitaNoppaa());
    }
}
