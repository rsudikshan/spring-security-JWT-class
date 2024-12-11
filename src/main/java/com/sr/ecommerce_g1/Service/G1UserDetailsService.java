package com.sr.ecommerce_g1.Service;

import com.sr.ecommerce_g1.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class G1UserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = repo.findByUsername(username);
        if(userDetails == null)
        throw new UsernameNotFoundException("Username not found");
        else
            return userDetails;
    }
}
