package org.example;


    class Dev12 {
        public static void main(String[] args) {
            try {
                System.out.println(10/0); // ArithmeticException here
            } catch(NullPointerException ae) {
                System.out.println(10/0);
            } finally {
                System.out.println("finally block ");
            }
        }
    }


