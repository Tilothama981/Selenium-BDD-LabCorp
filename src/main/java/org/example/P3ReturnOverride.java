package org.example;

public class P3ReturnOverride {

        static int f() {
            try { return 1; }
            finally { return 2; } // overrides
        }
        public static void main(String[] args) {
            System.out.println(f());
        }
    }

