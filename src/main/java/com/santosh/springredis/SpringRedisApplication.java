package com.santosh.springredis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringRedisApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringRedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
