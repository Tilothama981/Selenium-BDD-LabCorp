package org.example;

import java.io.IOException;

class P5_Cheked {
     static void ioTask() throws IOException {
         throw new IOException("Disk not reachable");
     }
     public static void main(String[] args) {
         try {
             ioTask();
         } catch (IOException e) {
             System.out.println("Handled: " + e.getMessage());
         }
     }
 }
//IllegalArgumentException
