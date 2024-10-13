package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

/**
 * @author Y7993
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
        // 返回的视图名称对应 login.html
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        // 这里可以添加用户验证逻辑
        model.addAttribute("message", "欢迎，" + username + "!");
        return "welcome";
        // 假设您会有一个 welcome.html 模板来显示欢迎信息
    }
}
