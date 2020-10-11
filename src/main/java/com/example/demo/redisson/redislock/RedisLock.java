package com.example.demo.redisson.redislock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname RedisLock
 * @Description TODO
 * @Date 2020-06-01 15:48
 * @Created by MR. Xb.Wu
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {

    String key() default "";

    long expireTime() default 10L;

    boolean repeat() default false;

}
