package org.example;

public class P10Wrapcase {
    public static void main(String[] args) {
        try {
            parse();
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName()
                    + ", cause=" + e.getCause().getClass().getSimpleName());
        }
    }
    static void parse() {
        try {
            Integer.parseInt("NaN");
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Parsing failed", e);
        }
    }
}

