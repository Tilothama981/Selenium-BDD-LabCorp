package org.example;

public class Example4 { public static void main(String[] args)
{ try
{
    int arr[] = new int[5];
    arr[10] = 7;
} catch (ArithmeticException | ArrayIndexOutOfBoundsException e)
{
    System.out.println("Multi-catch block caught: " + e.getClass().getSimpleName());
} }}