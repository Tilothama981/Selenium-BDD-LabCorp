package org.example;

public class Thisconstr{
    Thisconstr() {
        this("Calling another constructor");
        System.out.println("Default Constructor");
    }

    Thisconstr(String message) {
        System.out.println("Parameterized Constructor: " + message);
    }

    public static void main(String[] args) {
        Thisconstr obj = new Thisconstr();
    }
}

