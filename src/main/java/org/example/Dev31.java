package org.example;

 class Dev31 {

        public static void main(String[] args) {
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException | ArithmeticException e) {
                System.out.println("exception raised " + e);
            }
            System.out.println("rest of the app");
        }
    }


