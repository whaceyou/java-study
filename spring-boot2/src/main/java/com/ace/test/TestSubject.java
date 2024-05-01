package com.ace.test;

public class TestSubject {

    public void methodA(){
        System.out.println("methodA");
    }

    public void methodB(int i){
        //根据参数i进行复杂运算，得出结果赋值给key
        switch(i){
            case 0:
                methodA();
            case 1:
                methodC();
            case 2:
                methodD();
        }
    }
    public void methodC(){
        System.out.println("methodC");
    }

    public void methodD(){
        System.out.println("methodD");
    }



}
