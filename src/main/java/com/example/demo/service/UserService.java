// UserService.java
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Y7993
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void saveUser(User user) {
        userMapper.insert(user);
    }

    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }

    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    public String getUserInfo() {
        // 返回一些用户信息
        return "用户信息";
    }
    public void deleteAllUsers() {
        userMapper.deleteAll();
        userMapper.resetAutoIncrement();
    }
}