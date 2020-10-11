package com.example.demo.ratelimit;

import java.lang.annotation.*;

/**
 * @Classname RateLimit
 * @Description TODO
 * @Date 2020-06-05 15:23
 * @Created by MR. Xb.Wu
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    /**
     * 默认每秒支持100个
     * @return
     */
    int limintNum() default 100;
}
