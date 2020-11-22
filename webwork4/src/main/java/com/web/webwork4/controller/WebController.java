package com.web.webwork4.controller;

import com.web.webwork4.Data.ContactData;
import com.web.webwork4.Data.LoginData;
import com.web.webwork4.Data.Table;
import com.web.webwork4.models.CheckLogin;
import com.web.webwork4.models.TableAlters;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @SneakyThrows
    @GetMapping("/main")
    public String showMain(HttpServletRequest request) {
        Object flag = request.getSession().getAttribute("login");
        if (null != flag) {
            HttpSession session = request.getSession();
            if (null == session.getAttribute("table")) {
                Table table = new Table();
                session.setAttribute("table", table);
            }
            return "main";
        }
        else
            return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(LoginData user, Model model) {
        model.addAttribute("user", user);

        return "login";
    }


    @SneakyThrows
    @PostMapping("/checklogin")
    public String checkLogin(LoginData user, Model model, HttpServletRequest request) {
        if (true == CheckLogin.Check(user)) {
            user.setMessage("OK");
            request.getSession().setAttribute("login", "OK");
            return "redirect:/main";
        }
        else{
            user.setMessage("用户名或密码错误");
            user.setPassword("");
            return login(user, model);
        }
    }

    @SneakyThrows
    @GetMapping("/checklogin")
    public String redirectLogin(HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login"))
            return "redirect:/login";
        else
            return "redirect:/main";
    }




    @RequestMapping("/add")
    public String showAdd(ContactData cont, Model model, HttpServletRequest request) {
        Object flag = request.getSession().getAttribute("login");
        if (null != flag) {
            HttpSession session = request.getSession();
            if (null == session.getAttribute("table")) {
                Table table = new Table();
                session.setAttribute("table", table);
            }
            model.addAttribute("cont", cont);
            return "add";
        }
        else
            return "redirect:/login";


    }


    @PostMapping("/alter")
    public String showAlter(HttpServletRequest request, @ModelAttribute(value="row")Integer row, ContactData m, Model model) {
        Table t = (Table)request.getSession().getAttribute("table");
        ContactData infor = t.getTableinfo().elementAt(row);
        model.addAttribute("cont", infor);
        return "change";
    }



    @PostMapping("/checkchange")
    public String checkAlter(ContactData infor, HttpServletRequest request) {
        Table t = (Table)request.getSession().getAttribute("table");
        TableAlters.alterElem(t, infor);
        return "redirect:/main";
    }

    @PostMapping("del")
    public String DeleteContact(@ModelAttribute(value="row")Integer row, HttpServletRequest request) {
        Table t = (Table)request.getSession().getAttribute("table");
        TableAlters.deleteElem(t, row);
        return "redirect:/main";
    }


    @PostMapping("/checkadd")
    public String checkAdd(ContactData cont, HttpServletRequest request, Model model) {
        Table t = (Table)request.getSession().getAttribute("table");
        boolean is_valid = TableAlters.checkValidAdd(t, cont);
        if (true == is_valid) {
            t.getTableinfo().addElement(cont);
            return "redirect:/main";
        }
        else {
            cont.setMessage("联系人已存在");
            cont.setContactname("");
            return showAdd(cont, model,request);
        }
    }
}
