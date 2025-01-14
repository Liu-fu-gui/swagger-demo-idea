package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String sayHello() {
        logger.info("Request to /hello endpoint"); // 记录日志
        return "Hello, 你在干嘛!";
    }

    @GetMapping("/one")
    public String one() {
        logger.info("Request to /one endpoint"); // 记录日志
        return "Hello, 我在玩!";
    }
}