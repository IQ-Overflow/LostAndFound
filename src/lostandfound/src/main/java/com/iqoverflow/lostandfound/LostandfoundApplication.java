package com.iqoverflow.lostandfound;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.iqoverflow.lostandfound.dao")
@ServletComponentScan
public class LostandfoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostandfoundApplication.class, args);
    }

}
