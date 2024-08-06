package com.appgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppGymApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppGymApplication.class, args);
    }

}
