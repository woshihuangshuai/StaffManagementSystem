package com.cmos.service.impl;

import com.cmos.dao.config.MybatisConfig;
import com.cmos.service.impl.config.RedisConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

/**
 * @author HS
 */
@Import(value = {MybatisConfig.class, RedisConfig.class})
@SpringBootApplication
public class ServiceApplication {

    /**
     * 启动一个非web的spring boot应用程序
     * @param args
     */
    public static void main(String[] args) {

        new SpringApplicationBuilder(ServiceApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
