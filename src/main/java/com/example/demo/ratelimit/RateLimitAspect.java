package com.example.demo.ratelimit;

import com.example.demo.utils.MallResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Classname RateLimitAspect
 * @Description 限流 切面枚举实现
 * @Date 2020-06-05 15:21
 * @Created by MR. Xb.Wu
 */
@Component
@Aspect
@Slf4j
public class RateLimitAspect {

    /**
     * //用来存放不同接口的RateLimiter(key为接口名称，value为RateLimiter)
     */
    private ConcurrentHashMap<String, RateLimiter> map = new ConcurrentHashMap<>();

//    private static final String POINT = "execution (* com.example..*.controller..*.*(..))";

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private HttpServletResponse response;

    @Pointcut("@annotation(rateLimit)")
    public void serviceLimit(RateLimit rateLimit) {
    }

    @Around(value = "serviceLimit(rateLimit)", argNames = "joinPoint,rateLimit")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws NoSuchMethodException {
        Object obj = null;
        //获取拦截的方法名
        Signature sig = joinPoint.getSignature();
        //获取拦截的方法名
        MethodSignature msig = (MethodSignature) sig;
        //返回被织入增加处理目标对象
        Object target = joinPoint.getTarget();
        //为了获取注解信息
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        //获取注解信息
        RateLimit annotation = currentMethod.getAnnotation(RateLimit.class);
        //获取注解每秒加入桶中的token
        int limitNum = annotation.limintNum();
        // 注解所在方法名区分不同的限流策略
        String functionName = msig.getName();

        //令牌桶
        RateLimiter rateLimiter;
        //获取rateLimiter
        if(map.containsKey(functionName)) {
            rateLimiter = map.get(functionName);
        }else {
            map.put(functionName, RateLimiter.create(limitNum));
            rateLimiter = map.get(functionName);
        }
        try {
            //通过limiter.acquire(i);来以阻塞的方式获取令牌，当然也可以通过tryAcquire(int permits, long timeout, TimeUnit unit)
            // 来设置等待超时时间的方式获取令牌，如果超timeout为0，则代表非阻塞，获取不到立即返回。
            if (rateLimiter.tryAcquire(1, TimeUnit.SECONDS)) { //如果没有获取到令牌，则在1秒的超时时间之内还没有拿到令牌则直接返回。
                //执行方法
                obj = joinPoint.proceed();
            } else {
                MallResponse<String> response = new MallResponse<>();
                response.setData("系统繁忙，请稍后再试！");
                //拒绝了请求（服务降级）
                String result = objectMapper.writeValueAsString(response);
                log.info("拒绝了请求：" + result);
                outErrorResult(result);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }

    //将结果返回
    public void outErrorResult(String result) {
//        response.setContentType("application/json;charset=UTF-8");
//        try (ServletOutputStream outputStream = response.getOutputStream()) {
//            outputStream.write(result.getBytes("utf-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
