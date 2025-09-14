package org.example;

public class P14_Nested {

        public static void main(String[] args) {
            try {
                try {
                    int[] a = new int[1];
                    a[1] = 10; // inner handles
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Inner handled: " + e.getClass().getSimpleName());
                }
                int x = 10 / 0;   // outer handles
            } catch (ArithmeticException e) {
                System.out.println("Outer handled: " + e.getClass().getSimpleName());
            }
        }
    }

