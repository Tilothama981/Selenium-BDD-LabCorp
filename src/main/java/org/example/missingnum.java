package org.example;

import java.util.HashSet;
import java.util.Set;

public class missingnum {
    public static void main(String[] args) {
        int[] arran1={1,2,4,6,8,9,10};
        Set<Integer> missingNumber=new HashSet<>();
        for(int ele:arran1){
            missingNumber.add(ele);
        }
        System.out.println("Missing Numbers");
        for(int x=1;x<=10;x++){
           if(!(missingNumber.contains(x)))
               System.out.println(x);

            int[] arr1 = {11,20,33,40,59,9};
            System.out.println("//print everything - normal for loop ");

            for(int number:arr1){
                System.out.println(number);
            }
            }
        }
    }

