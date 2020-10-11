package com.example.demo.service;

public class Test {

    public static void main(String[] args) {

        System.out.println("刘雨涵");


//        System.out.println(f(2));
    }


    public static int f(int x) {
        if (x == 0) {
            return 0;
        } else {
            return 2 * f(x - 1) + x * x;
        }
    }
}
