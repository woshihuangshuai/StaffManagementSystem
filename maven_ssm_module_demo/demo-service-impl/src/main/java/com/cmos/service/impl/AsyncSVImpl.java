package com.cmos.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cmos.iservice.AsyncSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

/**
 * @author HS
 */
@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class AsyncSVImpl implements AsyncSV {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncSVImpl.class);

    @Override
    @Async
    public void testAsyncSV() {
        LOGGER.info("异步线程测试服务运行，当前线程：" + Thread.currentThread().getName());
    }
}
