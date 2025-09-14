public class Airthex
 {
    public static void main(String[] args) {
        try {
            int x = 10 / 0;
            System.out.println("Won't print");
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        }
        System.out.println("After try-catch");
    }
}

