package org.example;


import java.util.*;

public class Arraytypess {
    public static void main(String[] args) {
        int[] arr1 = {10, 4, 19, 99, 2, 2};

        // Convert array to a Set (removes duplicates) and sort
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : arr1) {
            set.add(num);
        }

        // Convert back to array if needed
        int[] result = set.stream().mapToInt(Integer::intValue).toArray();

        // Print result
        System.out.println(Arrays.toString(result));
    }
}
