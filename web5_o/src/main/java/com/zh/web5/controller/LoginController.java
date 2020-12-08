package com.zh.web5.controller;

import com.zh.web5.Data.LoginInfor;
import com.zh.web5.Service.CheckLogin;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    // 请求登录界面
    @RequestMapping("/login")
    public String login(LoginInfor user, Model model) {
        model.addAttribute("user", user);
        return "login";
    }


    @SneakyThrows
    @PostMapping("/checklogin")
    public String checkLogin(LoginInfor user, Model model, HttpServletRequest request) {        // 该参数是从view模型中提取出来的
        if (true == CheckLogin.Check(user)) {                        // 通过校验
            user.setMessage("OK");
            request.getSession().setAttribute("login", "OK");   // 设置标识
            return "redirect:/main";                                // 重定向到main界面
        } else {                                                       // 没通过校验
            user.setMessage("用户名或密码错误");                       // 向模型中添加预装属性，然后返回该模型
            user.setPassword("");                                   // 缺点：地址并没有真正的重定向
            return login(user, model);                              // 优点：便于传递参数 （如果使用redirect:/login 则可以真正的重定向地址，但是传递参数比较麻烦）
        }
    }


    @SneakyThrows
    @GetMapping("/checklogin")
    public String redirectLogin(HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login"))  // 没登录重定向到登录界面
            return "redirect:/login";
        else                                                       // 登录了重定向到主界面
            return "redirect:/main";
    }



}
