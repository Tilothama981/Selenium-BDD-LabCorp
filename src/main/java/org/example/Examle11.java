package org.example;


    public class Examle11 { public static void main(String[] args) {
        System.out.println(test());
    }
    static int test()
    {
        try { return 10;
    }
        catch (Exception e)
    {
        return 20; }
    finally {
        return 30;
    } } }

