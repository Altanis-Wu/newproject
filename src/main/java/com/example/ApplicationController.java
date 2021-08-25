package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApplicationController {
    @RequestMapping
    public String index(){
        return "index";
    }
}
