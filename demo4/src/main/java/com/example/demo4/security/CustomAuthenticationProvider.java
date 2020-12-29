package com.example.demo4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username =authentication.getName();
        String password=String.valueOf(authentication.getCredentials());

        UserDetails user=userDetailsService.loadUserByUsername(username);
        System.out.println(" cheek !!!!");
        if(user!=null){

            if(user.getPassword().equals(password)){
                return new UsernamePasswordAuthenticationToken(username,password,user.getAuthorities());
            }
        }




        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
