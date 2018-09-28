package com.cmos.service.impl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;


/**
 * @author HS
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate = null;

    /**
     * 定义自定义后的初始化方法
     */
    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    /**
     * 自定义配置RedisTemplate
     */
    private void initRedisTemplate() {
        RedisSerializer stringRedisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
    }
}
