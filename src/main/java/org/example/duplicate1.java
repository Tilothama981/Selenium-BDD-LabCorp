package org.example;

import java.util.HashSet;
import java.util.Set;

public class duplicate1 {
    public static void main(String[] args) {
        int[]arr={4,4,4,5,6,6,8,9,2,2,2};
        Set<Integer> arryNumber=new HashSet<>();
        Set<Integer> Duplicates=new HashSet<>();
        for (int number:arr){
            if (arryNumber.add(number)==false) Duplicates.add(number);
        }
        System.out.println("using Set");
        System.out.println(Duplicates);

        System.out.println("==================='");
        int[]arr2={4,4,4,5,6,6,8,9,2,2,2};
        boolean visited[] = new boolean[arr.length];

        System.out.print("Duplicates: ");

        for (int i = 0; i < arr.length; i++) {
            if (visited[i])
                continue;  // skip if already visited

            boolean isDuplicate = false;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    isDuplicate = true;
                    visited[j] = true; // mark duplicate as visited
                }
            }

            if (isDuplicate) {
                System.out.print(arr[i] + " ");
            }
        }

        }
    }

