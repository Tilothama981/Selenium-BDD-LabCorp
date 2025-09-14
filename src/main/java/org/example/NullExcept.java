package org.example;

public class NullExcept
{

public static void main(String[] args){
try{
    String str=null;
    System.out.println(str.length());

}
catch(NullPointerException e){
    System.out.println("Caught Null Pointer Exception");
    System.out.println("Exception message" +e.getMessage());

}
System.out.println("Program continues" );
}
}


