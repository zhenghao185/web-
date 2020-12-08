package com.zh.web5.controller;

import com.zh.web5.Data.ContactInfor;
import com.zh.web5.Dao.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AlterController {

    @Autowired
    private UserJpaRepository userJpaRepository;


    @PostMapping("/alter")
    public String showAlter(HttpServletRequest request, @ModelAttribute(name = "name") String name, Model model) {
        model.addAttribute("cont", userJpaRepository.findByContactname(name).get(0));
        return "alter";
    }


    @GetMapping("/alter")
    public String redirectAlter() {
        return "redirect:/main";
    }


    @GetMapping("/checkalter")
    public String redirectCheckAlter() {
        return "redirect:/main";
    }


    @PostMapping("/checkalter")
    public String checkAlter(ContactInfor infor, HttpServletRequest request) {
        userJpaRepository.save(infor);
        return "redirect:/main";
    }


    @GetMapping("/del")
    public String redirectDel() {
        return "redirect:/main";
    }


    @PostMapping("/del")    // ModelAttribute 可以理解成随请求一起发过来的参数Param
    public String DeleteContact(@ModelAttribute(value = "name") String name, HttpServletRequest request) {
        userJpaRepository.deleteByContactname(name);
        return "redirect:/main";
    }
}
