package com.cmos;


import com.cmos.dao.DaoApplication;
import com.cmos.service.impl.config.SVImplConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(value = {DaoApplication.class, SVImplConfig.class})
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
