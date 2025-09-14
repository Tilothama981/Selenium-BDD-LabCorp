package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckedExp {

    public static void main(String[] args) {
            try {
                // This file probably doesn't exist, so it will throw FileNotFoundException
                FileReader file = new FileReader("this_file_will_never_exist_12345.txt");

                System.out.println("File opened successfully!");
            }
            catch (FileNotFoundException e) {
                System.out.println("Caught Checked Exception: " + e);
            }
        }
    }

