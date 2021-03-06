package com.example.demo.redisson.redislock;


import com.example.demo.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@Aspect
@Component
public class RedisLockAopNew {

    private ThreadLocal<Long> beginTime = new ThreadLocal<>();
    private ThreadLocal<String> objectId = new ThreadLocal<>();
    private ThreadLocal<String> lockValue = new ThreadLocal<>();

    @Autowired
    private RedisLockService redisLockService;

    private static final String[] types = {"java.lang.Integer",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.Byte",
            "java.lang.Boolean",
            "java.lang.Character",
            "java.lang.String",
            "int", "double", "long", "short", "byte", "boolean", "char", "float"};

    private static List<String> typesList = Arrays.asList(types);


    public RedisLockAopNew() {
    }

    @Pointcut("@annotation(redisLock)")
    public void serviceStatistics(RedisLock redisLock) {
    }

    @Around(value = "serviceStatistics(redisLock)", argNames = "joinPoint,redisLock")
    public Object redisLock(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
        Map<String, String> map = null;
        try {
            map = lock(joinPoint, redisLock);
            return joinPoint.proceed();
        } finally {
            unlock(joinPoint, map);
        }
    }


    private Map<String, String> lock(ProceedingJoinPoint joinPoint, RedisLock redisLock) {
        this.beginTime.set(System.currentTimeMillis());

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        String key = redisLock.key();
        long expireTime = redisLock.expireTime();
        boolean repeat = redisLock.repeat();

        if (ObjectUtils.isNullOrEmpty(args) || ObjectUtils.isNullOrEmpty(key)) {
            key = methodName + UUID.randomUUID().toString();
        }

        if (ObjectUtils.isNotNullAndEmpty(args)) {
            Object value = "";
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
            for (int i = 0; i < argNames.length; i++) {
                //如果字段名称和key相等则直接取值
                if (key.equals(argNames[i])) {
                    if (args[i] != null) {
                        value = args[i];
                        break;
                    }
                } else {
                    Class userCla = args[i].getClass();
                    //如果不是基本类型和包装类型，则遍历对象字段直到找出与key相同的字段的值
                    if (!typesList.contains(userCla.getName())) {
                        Field[] fs = userCla.getDeclaredFields();
                        for (Field f : fs) {
                            f.setAccessible(true);
                            if (key.equals(f.getName())) {
                                if (args[i] != null) {
                                    try {
                                        value = f.get(args[i]);
                                        break;
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            key = className + "." + methodName + "-" + value.toString();
        }
        log.info("被加锁的key值是：{}", key);
        this.objectId.set(key);
        String requestId = redisLockService.lock(key, expireTime, repeat);
        Map<String, String> map = new HashMap<>();
        map.put("lockKey", key);
        map.put("requestId", requestId);
        log.info("线程：" + Thread.currentThread().getName() + "，已进入方法：" + className + "." + methodName);
        return map;
    }

    private void unlock(ProceedingJoinPoint joinPoint, Map<String, String> map) {
        if (map == null) {
            return;
        }
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        boolean unlock = redisLockService.unlock(map.get("lockKey"), map.get("requestId"));
        if (unlock) {
            log.info("objectId:" + this.objectId.get() + ",lockValue:" + this.lockValue.get() + ",已经解锁！");
        }
        log.info("线程：" + Thread.currentThread().getName() + "， 已退出方法：" + className + "." + methodName + ",耗时：" + (System.currentTimeMillis() - this.beginTime.get()) + " 毫秒！");
    }


}
