package com.example.ledexample.controllers;

import com.example.ledexample.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {

    private final Parser parser;

    @Autowired
    public SettingsController(Parser parser) {
        this.parser = parser;
    }

    @GetMapping("/settings")
    public String settingsPage(Model model) {

        model.addAttribute("message", "Балл пробок в данный момент: " + parser.getTrafficJamScore());

        return "settings";
    }
}