package com.study.example.springboot.config;

import com.study.example.springboot.cache.JsonRedisTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfigtion {
    @Bean
    public JsonRedisTemplate cacheManager(RedisConnectionFactory redisConnectionFactory,
                                          ResourceLoader resourceLoader) {
        JsonRedisTemplate redisTemplate = new JsonRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置键的序列化为字符串
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置值得序列化为JSON
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return redisTemplate;
    }
}
