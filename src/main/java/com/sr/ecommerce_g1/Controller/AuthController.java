package com.sr.ecommerce_g1.Controller;
import com.sr.ecommerce_g1.Entity.Users;
import com.sr.ecommerce_g1.Service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService service;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.saveUser(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return service.verifyUser(user);

    }

    @GetMapping("/demo")
    public String demo(){
        return "Hello World";
    }
}
