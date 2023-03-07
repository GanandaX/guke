package com.example.guke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.guke.dao")
public class GukeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GukeApplication.class, args);
    }

}
