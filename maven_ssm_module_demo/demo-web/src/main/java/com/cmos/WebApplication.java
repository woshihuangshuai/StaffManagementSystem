package com.cmos;

import com.cmos.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


/**
 * @author HS
 */
@Import(value = {WebConfig.class})
@SpringBootApplication(scanBasePackages = {"com.cmos"})
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
