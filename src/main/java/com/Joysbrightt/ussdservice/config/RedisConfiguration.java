//package com.Joysbrightt.ussdservice.config;
//
//import lombok.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//
//public class RedisConfiguration extends CachingConfigurerSupport {
//    @Value("${decoded.cache.host}")
//    private String host;
//
//    @Value("${decoded.cache.port}")
//    private String port;
//
//    @Value("${decoded.cache.password}")
//    private String password;
//
//    @Value("${decoded.cache.default-ttl}")
//    private  String defualtTTL;
//
//    @Bean
//    public LettuceConnectionFactory redisconnectionFactory(){
//        RedisStandaloneConfiguration redisStandalone = new RedisStandaloneConfiguration();
//        redisStandalone.setHostName(host);
//        redisStandalone.setPort(port);
//
//    }
//
//}
