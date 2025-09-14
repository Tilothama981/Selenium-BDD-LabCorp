package org.example;

public class P15ExitSkipFinally {

        public static void main(String[] args) {
            try {
                System.out.println("A");
                System.exit(0);
            } finally {
                System.out.println("B");
            }
        }
    }

