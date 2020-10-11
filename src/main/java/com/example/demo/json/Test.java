package com.example.demo.json;

import java.time.LocalDateTime;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020-02-19 16:59
 * @Created by MR. Xb.Wu
 */
public class Test {

    public static void main(String[] args) {
//        System.out.println(21/2);

//        System.out.println(LocalDateTime.now().isAfter(LocalDateTime.of(2020,2,20,5,18,0,0)));
        e(1,500);
    }


    public static void e(int s, int n) {
        double r = 0;
        for (int i = 1; i <= n; i++) {
            r = r + 1/Math.pow(i, s);
        }
        System.out.println(r);
    }
}
