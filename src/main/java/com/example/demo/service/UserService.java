package com.example.demo.service;

import com.example.demo.repository.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    public void createUser(User user) {
        userMapper.insertUser(user);
    }

    public boolean checkIfUserNameExists(String name) {
        User user = userMapper.getUserByName(name);
        return user != null;
    }

    public boolean checkIfUserExists(String name, String pass) {
        User user = userMapper.getUserByNameAndPass(name, pass);
        return user != null;
    }
}