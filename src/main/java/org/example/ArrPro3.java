package org.example;
import java.util.ArrayList;

public class ArrPro3 {
    public static void main(String[] args) {
        ArrayList<String> ar = new ArrayList<>();

        ar.add("Apple");    // ✅ correct
        ar.add("Banana");   // ✅ correct
        ar.add("Grapes");   // ✅ correct
ar.add(2,"Mango");
        System.out.println(ar);
    }
}
