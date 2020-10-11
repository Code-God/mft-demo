package com.example.demo.redisson;

import java.util.concurrent.TimeUnit;

/**
 * @Classname DistributrdLocker
 * @Description TODO
 * @Date 2020-06-01 14:26
 * @Created by MR. Xb.Wu
 */
public interface DistributedLocker {

    void lock(String lockKey);

    void unlock(String lockKey);

    void lock(String lockKey, long leaseTime);

    void lock(String lockKey, TimeUnit unit, long timeout);
}
