package com.cmos.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.cmos.iservice.IRedisSV;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 操作redis数据库
 * @author HS
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private IRedisSV redisSV = null;

    @ApiOperation(value = "测试Redis服务", response = Map.class)
    @RequestMapping(value = "/testRedis", method = RequestMethod.GET)
    public Map testRedis() {
        return redisSV.testStringAndHash();
    }


}
