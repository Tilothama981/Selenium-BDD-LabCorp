package org.example;

public class ClassLambda {
    public static void main(String[] args) {
BMW bmwcar=new BMW();
bmwcar.model();
    }
}
class BMW implements car{
    @Override
    public void model(){
        System.out.println("This is BMW");
    }
}
interface car{
    public void model();
}

