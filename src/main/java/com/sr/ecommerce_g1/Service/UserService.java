package com.sr.ecommerce_g1.Service;

import com.sr.ecommerce_g1.Entity.Users;
import com.sr.ecommerce_g1.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo repo;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JWTService jwtService;



    public Users saveUser(Users user){
        CharSequence password = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder(12).encode(password));
        repo.save(user);
        return user;

    }

    public String verifyUser(Users user){
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        if(authentication.isAuthenticated())
            return jwtService.getToken(user.getUsername());
        else
            return "failed";
    }


}
