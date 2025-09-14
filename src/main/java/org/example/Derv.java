package org.example;


import java.util.Scanner;

class Dev {
    public static void main (String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("enter a num ");
            int num = s.nextInt();
            System.out.println(10/0);
            System.out.println("ratan".charAt(12));
        } catch(ArithmeticException | NumberFormatException e) {
            System.out.println("exception accoured " + e);
        } catch(StringIndexOutOfBoundsException | ClassCastException | NullPointerException a) {
            // no need to present in the try
            System.out.println("exception accoured first " + a);
        }
        System.out.println("rest of the app");
    }
}
