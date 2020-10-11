package com.example.demo.service;

import com.example.demo.entity.MallUser;
import com.example.demo.redisson.redislock.RedisLock;
import com.example.demo.redisson.redisson.RedissonLock;
import org.springframework.stereotype.Service;

/**
 * @Classname RedisLockTestService
 * @Description TODO
 * @Date 2020-06-01 15:54
 * @Created by MR. Xb.Wu
 */
@Service
public class RedisLockTestService {

    @RedisLock(key = "id")
    public String test(String id, String name) {
        return id+name;
    }


    @RedisLock(key = "id")
    public String test2(MallUser user) {
        return user.toString();
    }

    private static int i = 0;

    @RedisLock(key = "id")
    public String test3(int number, int id , MallUser user) {
        i = number;
        return number+id+user.toString();
    }

    @RedisLock(key = "id")
    public String test4() {
        return "";
    }

    @RedissonLock(lockIndex = 0)
    public String test5(MallUser user) {
        return user.toString();
    }
}
