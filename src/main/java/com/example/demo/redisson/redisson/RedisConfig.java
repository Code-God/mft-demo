//package com.example.demo.redisson.redisson;
//
//import com.example.demo.redisson.RedissonProperties;
//import com.example.demo.redisson.redislock.RedisLockService;
//import org.apache.commons.lang3.StringUtils;
//import org.redisson.Redisson;
//import org.redisson.config.Config;
//import org.redisson.config.SingleServerConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
///**
// * @Classname RedisConfig
// * @Description
// * @Date 2020-07-13 11:27
// * @Created by MR. Xb.Wu
// */
//@Configuration
//public class RedisConfig {
//
//    @Autowired
//    private RedissonProperties redssionProperties;
//
//    public static String host;
//
//    @Value("${spring.redis.host}")
//    private void setHost(String host) {
//        RedisConfig.host = host;
//    }
//
//    private static String password;
//
//    @Value("${spring.redis.password}")
//    private void setPassword(String password) {
//        RedisConfig.password = password;
//    }
//
//    private static Integer port;
//
//    @Value("${spring.redis.port}")
//    private void setPort(Integer port) {
//        RedisConfig.port = port;
//    }
//
//    @Bean(name = {"redisTemplate", "stringRedisTemplate"})
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate redisTemplate = new StringRedisTemplate();
//        redisTemplate.setConnectionFactory(factory);
//        return redisTemplate;
//    }
//
//    @Bean
//    public Redisson redisson() {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
//
//        return Redisson.create(config);
//    }
//}
