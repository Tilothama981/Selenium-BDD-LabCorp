package org.example;
class Parent {

    int x = 10;



    void display() {

        System.out.println("Parent: " + x);

    }

}



class Child extends Parent {
    int x = 20;
    void display() {
        System.out.println("Child: " + x);
    }
}
public class Test1 {
    public static void main(String[] args) {

        Parent obj = new Child();

        obj.display();

        System.out.println(obj.x);

    }

}