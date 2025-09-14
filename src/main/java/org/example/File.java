package org.example;


import java.io.*;
public class File {
    public static void main(String[] args)
    {
        try{
        FileInputStream fis = new FileInputStream("abc.txt");
    }
        catch(IOException e)
    {
        System.out.println("exception raised " + e); }
        System.out.println("rest of the app");
    }}

