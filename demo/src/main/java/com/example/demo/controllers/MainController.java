package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/","/main"})
    public String getMainScreen(Model model) {
        return "main";
    }

}