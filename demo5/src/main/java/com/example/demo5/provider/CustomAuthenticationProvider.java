package com.example.demo5.provider;

import com.example.demo5.authentication.CustomAuthentication;
import com.example.demo5.filter.CustomAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String test=authentication.getName();

        System.out.println("####### "+ test);
        System.out.println("####### "+ test.equals("abc"));

        if(test.equals("abc")){

            var result = new CustomAuthentication(null,null,null);
            return  result;
        }
        throw  new BadCredentialsException(" bad cre");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomAuthentication.class.equals(aClass);
    }
}
