package org.example;

public class P2_Finallt {

        public static void main(String[] args) {
            try {
                int[] a = new int[2];
                a[2] = 5;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Caught AIOOBE");
            } finally {
                System.out.println("Finally executed");
            }
        }
    }

