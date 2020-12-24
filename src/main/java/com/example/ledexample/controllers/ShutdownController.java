package com.example.ledexample.controllers;

import com.example.ledexample.context.MyAppContext;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class ShutdownController {

    private final MyAppContext myAppContext;


    @PostMapping("/shutdown")
    public void shutdownContext() {
        myAppContext.close();
    }

}