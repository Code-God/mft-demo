package com.example.demo.ratelimit;

import org.springframework.stereotype.Service;

/**
 * @Classname RateLimitTestService
 * @Description TODO
 * @Date 2020-06-05 15:42
 * @Created by MR. Xb.Wu
 */
@Service
public class RateLimitTestService {

    @RateLimit(limintNum = 10)
    public String rateLimit(String num) {
        System.out.println(num);
        return num;
    }
}
