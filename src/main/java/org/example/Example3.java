package org.example;

public class Example3
{
    public static void main(String[] args)
{
    try { int a = 5 / 0; } catch (ArithmeticException e)
{
    throw new NullPointerException("New Exception");
} }
}
