package com.cmos.service.impl;

import com.cmos.dao.config.MybatisConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@Import(value = {MybatisConfig.class})
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ServiceApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
