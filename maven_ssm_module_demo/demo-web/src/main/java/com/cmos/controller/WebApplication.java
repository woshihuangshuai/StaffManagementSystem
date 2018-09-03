package com.cmos.controller;


import com.cmos.dao.DaoApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;


@Import(DaoApplication.class)
@ImportResource(value = "classpath:ApplicationContext.xml")
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
