package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        users.forEach(user -> System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Password: " + user.getPass()));
        return users;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/checkUserId")
    public ResponseEntity<Map<String, Boolean>> checkUserId(@RequestBody Map<String, String> request) {
        //入力値「userId」(ID欄入力値)を取得
        String userName = request.get("userId");
        boolean exists = userService.checkIfUserNameExists(userName);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

}
