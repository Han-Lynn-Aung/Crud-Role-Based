package com.example.springcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/welcome")
    private String ViewUserPage(Model model){
        return "welcome";
    }
}
