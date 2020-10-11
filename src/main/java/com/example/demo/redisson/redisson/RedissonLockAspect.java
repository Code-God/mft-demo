//package com.example.demo.redisson.redisson;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.redisson.Redisson;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Classname RedissonLockAspect
// * @Description
// * @Date 2020-07-13 11:24
// * @Created by MR. Xb.Wu
// */
//@Slf4j
//@Aspect
//@Component
//@Order(1) //该order必须设置，很关键 //定义个切面，注意把order设置的小一点，让该切面优先于Transactional注解，这样才能优先被捕捉乐观锁异常。
//public class RedissonLockAspect {
//
//    @Autowired
//    private RedissonClient redisson;
//
//    @Around("@annotation(redissonLock)")
//    public Object around(ProceedingJoinPoint joinPoint, RedissonLock redissonLock) throws Throwable {
//        Object obj = null;
//
//        //方法内的所有参数
//        Object[] params = joinPoint.getArgs();
//
//        int lockIndex = redissonLock.lockIndex();
//        //取得方法名
//        String key = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint
//                .getSignature().getName();
//        //-1代表锁整个方法，而非具体锁哪条数据
//        if (lockIndex != -1) {
//            key += params[lockIndex];
//        }
//
//        //多久会自动释放，默认10秒
//        int leaseTime = redissonLock.leaseTime();
//        int waitTime = 5;
//        log.info("被加锁的key值为===========>:{}", key);
//
//        RLock rLock = redisson.getLock(key);
//        boolean res = rLock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
//        if (res) {
//            log.info("取到锁");
//            obj = joinPoint.proceed();
//            rLock.unlock();
//            log.info("释放锁");
//        } else {
//            log.info("----------nono----------");
//            throw new RuntimeException("没有获得锁");
//        }
//
//        return obj;
//    }
//}
