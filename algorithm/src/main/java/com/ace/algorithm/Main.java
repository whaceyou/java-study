package com.ace.algorithm;

public class Main {


    public static void main(String[] args) {


        int a = 0b0000_0000_0000_0000_0000_0000_0000_0001;
        System.out.println(a);
        int b = 0b1000_0000_0000_0000_0000_0000_0000_0000;
        System.out.println(Integer.toBinaryString(a << 100));
        System.out.println((a << 100));
        System.out.println((-5 >> 3));
    }
}
