package com.rchenhyy.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qunar.web.spring.annotation.JsonBody;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/8/30
 */
@RestController
@EnableAutoConfiguration
@ComponentScan
public class Example {

    @RequestMapping("/")
    @JsonBody
    String home() {
        throw new RuntimeException("hahahahaahah");
        //return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}
