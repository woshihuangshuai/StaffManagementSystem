package com.cmos.rest;

import com.cmos.rocketmq.RocketMQProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @FileName TestController.java
 * @Description:TODO
 * @author JackHisen(gu.weidong)
 * @version V1.0
 * @createtime 2018年3月22日 下午7:07:24
 * 修改历史：
 * 时间           作者          版本        描述
 *====================================================
 *
 */
@RestController
public class TestRocketMQController {
    @Autowired
    RocketMQProvider rocketMQProvider;
    @RequestMapping(value = "/testRocketMQ", method = RequestMethod.GET)
    public String testMq() {
        rocketMQProvider.defaultMQProducer();
        return null;
    }
}

