package org.example;

public class BlockExam {
//    static {
//        System.out.println("Static Block");
//    }
//    {
//        System.out.println("Instance  Block");
//    }
//    BlockExam(){
//        System.out.println("Constructor");
//    }
//
//    public static void main(String[] args) {
//        BlockExam obj1=new BlockExam();
//        BlockExam obj2=new BlockExam();
//
//    }
static {
    System.out.println("Static Block 1");
}

    static {
        System.out.println("Static Block 2");
    }

    public static void main(String[] args) {
        System.out.println("Main Method");
    }

}
