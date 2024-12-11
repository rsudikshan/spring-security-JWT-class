package com.sr.ecommerce_g1.Filter;

import com.sr.ecommerce_g1.Service.G1UserDetailsService;
import com.sr.ecommerce_g1.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JWTService service;

    @Autowired
    G1UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if( authHeader != null && authHeader.startsWith("BEARER ")){
            token = authHeader.substring(7);
            username = service.getUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(service.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken token1
                        = new UsernamePasswordAuthenticationToken
                        (
                                userDetails,null,userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(token1);
            }



        }
        filterChain.doFilter(request,response);
    }
}
