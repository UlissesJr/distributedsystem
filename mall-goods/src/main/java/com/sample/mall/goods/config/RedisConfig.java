package com.sample.mall.goods.config;

import io.rebloom.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by LuoboGan
 * Date:2023/3/10
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.bloom-filter.name}")
    private String name;

    @Value("${spring.redis.bloom-filter.init-capacity}")
    private long initCapacity;

    @Value("${spring.redis.bloom-filter.error-rate}")
    private double errorRate;

    @Bean
    public <T> RedisTemplate<String,T> redisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate<String,T> redisTemplate = new RedisTemplate<>();
        // 默认采用JDK的序列化方式，会将数据保存为二进制，不方便查看
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public Client redisBloomClient(){
        Client redisBloomClient = new Client(host, port);
        redisBloomClient.createFilter(name,initCapacity,errorRate);
        return redisBloomClient;
    }

}
