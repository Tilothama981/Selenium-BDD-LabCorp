package org.example;


    public class Exampl5 {
        static {
            if (true) throw new RuntimeException("Static block failed!");
        }

        public static void main(String[] args) {
            System.out.println("Hello");
        }
    }
