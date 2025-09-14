package org.example;

 class File2
{
    public static void main(String[] args) {
        System.out.println(10/0);     // (1)
        try {
            System.out.println(10/0); // (2)
        } finally {
            System.out.println("finally block ");  // (3)
        }
    }
}


