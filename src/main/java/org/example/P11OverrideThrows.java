package org.example;


    import java.io.*;

    class Parent1 {
        void read() throws IOException { /* ... */ }
    }

    // Valid: narrower checked exception
    class ChildOk extends Parent1 {
        @Override void read() throws FileNotFoundException { /* ... */ }
    }

    // Valid: remove throws entirely
    class ChildAlsoOk extends Parent1 {
        @Override void read() { /* ... */ }
    }


    public class P11OverrideThrows { public static void main(String[] a) {} }

