package com.sr.ecommerce_g1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {

    @GetMapping("/demo")
    public String demo(){
        return "Hello";
    }
}
