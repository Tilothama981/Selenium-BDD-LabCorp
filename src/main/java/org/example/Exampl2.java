package org.example;
public class Exampl2
{ public static void main(String[] args)
{
    try { throw new RuntimeException("from try");
}
    finally { throw new RuntimeException("from finally");
    } } }