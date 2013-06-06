public class FibonaccinLuvut {
    public static int fibonacci(int n) {
        if(n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 2) + fibonacci(n- 1);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(9));
        System.out.println(fibonacci(11));
    }
}
