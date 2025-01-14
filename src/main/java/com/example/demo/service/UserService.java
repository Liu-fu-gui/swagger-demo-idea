package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 添加用户
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // 根据用户名查询用户
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}