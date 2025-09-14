package org.example;

public class P7_MultiCatch {
    //public class P7_MultiCatch {
        public static void main(String[] args) {
            for (String s : new String[]{"10", "x"}) {
                try {
                    int n = Integer.parseInt(s);
                    int r = 10 / n;
                    System.out.println("Result: " + r);
                } catch (NumberFormatException | ArithmeticException e) {
                    System.out.println("Caught: " + e.getClass().getSimpleName());
                }
            }
        }
    }

