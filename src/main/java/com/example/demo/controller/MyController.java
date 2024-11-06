package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.service.UserService;

@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";  // templatesフォルダにあるhello.htmlを返す
    }

    @GetMapping("/")
    public String top() {
        return "login";  // templatesフォルダにあるhello.htmlを返す
    }

    // 新規登録画面へのマッピングを追加
    @GetMapping("/user/registerUser")
    public String createUser() {
        return "registerUser";  // templatesフォルダにあるcreateUser.htmlを返す
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String loginPassword, RedirectAttributes redirectAttributes) {
        // ここでログインロジックを実装します。
        boolean loginSuccess = checkLogin(userId, loginPassword);

        if (loginSuccess) {
            return "redirect:/top";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "IDまたはパスワードが間違っています。");
            return "redirect:/";
        }
    }

    private boolean checkLogin(String userId, String loginPassword) {
        // チェックロジック
        return userService.checkIfUserExists(userId,loginPassword);
    }

    @GetMapping("/top")
    public String topPage(Model model) {
        return "top"; // top.htmlを返す
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam("userId") String userId, @RequestParam("loginPassword") String loginPassword, @RequestParam("nickname") String nickname, RedirectAttributes redirectAttributes) {
        // ユーザー作成ロジックはUserControllerのサービスを利用
        User user = new User();
        user.setName(userId);
        user.setPass(loginPassword);
        user.setNickname(nickname);
        // サービスクラスで作成
        userService.createUser(user);
        // registerComplete.htmlを返す
        return "registerComplete";
    }
}
