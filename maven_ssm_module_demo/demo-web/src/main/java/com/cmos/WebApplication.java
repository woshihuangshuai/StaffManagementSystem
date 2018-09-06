package com.cmos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.cmos"})
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
