package org.example;

public class P6_Unchecked {
    static void badInput() { throw new IllegalArgumentException("Negative size"); }
    public static void main(String[] args) {
        try {
            badInput();
        } catch (RuntimeException e) {
            System.out.println(e.getClass().getSimpleName());
        }
    }
}
