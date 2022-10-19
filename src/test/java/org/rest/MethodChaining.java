package org.rest;

public class MethodChaining {

    public static void main(String[] args){
//        MethodChaining methodChaining = new MethodChaining();
//        methodChaining.a1();
//        methodChaining.a2();
//        methodChaining.a3();
        // Got rid of above code using method chaining. To do it, each function's return type should be class type and it should return 'this'.
        // Even if you want to call the function without object initialization, make one function static but 'return this' would not work in static function.
        // That's why 'return new MethodChaining();' for this specific function.

        a1().
        a2().
        a3();
    }

    public static MethodChaining a1(){
        System.out.println("this is a1 method");
        return new MethodChaining();
    }

    public MethodChaining a2(){
        System.out.println("this is a2 method");
        return this;
    }

    public MethodChaining a3(){
        System.out.println("this is a3 method");
        return this;
    }
}
