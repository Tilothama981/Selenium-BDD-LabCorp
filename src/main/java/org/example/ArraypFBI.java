package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ArraypFBI {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("apple");
        list.add("orange");
        list.add("grapes");
        System.out.println("Printing forward direction");
       ListIterator<String> itr1=list.listIterator();
       while(itr1.hasNext()){
           System.out.println(itr1.next());

       }
        System.out.println("Printing backward direction");
       ListIterator<String>itr2=list.listIterator(list.size());
               while(itr2.hasPrevious()){
                   System.out.println(itr2.previous());
               }

    }
}