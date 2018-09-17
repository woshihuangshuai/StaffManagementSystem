package com.cmos.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cmos.iservice.IRedisSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class RedisSVImpl implements IRedisSV {

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @Override
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("First Name", "Shuai");
        redisTemplate.opsForValue().set("Last Name", "Huang");

        stringRedisTemplate.opsForValue().set("int", "1");
        stringRedisTemplate.opsForValue().increment("int", 1);

        BoundListOperations boundListOperations = redisTemplate.boundListOps("sms_list");
        boundListOperations.rightPush("Huang");
        boundListOperations.rightPush("Shuai");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", "Success");
        return resultMap;
    }
}
