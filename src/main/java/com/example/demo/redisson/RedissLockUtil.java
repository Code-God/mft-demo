package com.example.demo.redisson;

import java.util.concurrent.TimeUnit;

/**
 * @Classname RedissonLockUtil
 * @Description redis分布式锁帮助类
 * @Date 2020-06-01 14:32
 * @Created by MR. Xb.Wu
 */
public class RedissLockUtil {

    private static DistributedLocker redissLock;

    public static void setLocker(DistributedLocker locker) {
        redissLock = locker;
    }

    public static void lock(String lockKey) {
        redissLock.lock(lockKey);
    }

    public static void unlock(String lockKey) {
        redissLock.unlock(lockKey);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public static void lock(String lockKey, long timeout) {
        redissLock.lock(lockKey, timeout);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    public static void lock(String lockKey, TimeUnit unit , long timeout) {
        redissLock.lock(lockKey, unit, timeout);
    }

}
