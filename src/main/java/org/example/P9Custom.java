package org.example;


    class InvalidAgeException extends Exception {
        public InvalidAgeException(String msg) { super(msg); }
    }

    public class P9Custom {
        static void validate(int age) throws InvalidAgeException {
            if (age < 18) throw new InvalidAgeException("Invalid age: " + age);
        }
        public static void main(String[] args) {
            try {
                validate(25);
            } catch (InvalidAgeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Continue...");
        }
    }

