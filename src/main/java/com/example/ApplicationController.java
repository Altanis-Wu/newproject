package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class ApplicationController {
    @RequestMapping("/")
    public String getIndex(){
        return "index";
    }
}
