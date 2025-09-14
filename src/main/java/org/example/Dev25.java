package org.example;


//class Dev25 {
//    public static void main(String[] args)
//    { System.out.println(10/0);
//        try { System.out.println(10/0);
//        } finally{ System.out.println("finally block ");
//        } } }

class Dev25 {
    public static void main(String[] args) {
        try {
            System.out.println(10/0); // (1) ArithmeticException
        }
        catch(ArithmeticException ae) {
            System.out.println("ratan".charAt(10)); // (2) StringIndexOutOfBoundsException
        }
        finally {
            int[] a= {10,20,30};
            System.out.println((a[7])); // (3) ArrayIndexOutOfBoundsException
        }
    }
}



