package com.example.ledexample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String settingsPage(Model model) {

        model.addAttribute("message", "Сейчас пробки на " + " баллов.");

        return "settings";
    }
}