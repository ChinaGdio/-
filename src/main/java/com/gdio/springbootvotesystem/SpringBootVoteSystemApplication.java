package com.gdio.springbootvotesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.gdio.springbootvotesystem.mapper")
@SpringBootApplication
public class SpringBootVoteSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVoteSystemApplication.class, args);
    }

}
