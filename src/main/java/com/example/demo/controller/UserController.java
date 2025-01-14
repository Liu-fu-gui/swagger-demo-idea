package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // 定义日志记录器
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // 添加用户
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        logger.info("Request to /api/users/add endpoint with user: {}", user); // 记录日志
        return userService.addUser(user);
    }

    // 根据用户名查询用户
    @PostMapping("/query")
    public User findUserByUsername(@RequestParam String username) {
        logger.info("Request to /api/users/query endpoint with username: {}", username); // 记录日志
        return userService.getUserByUsername(username);
    }
}