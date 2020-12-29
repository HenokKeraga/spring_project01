package com.example.demo5.filter;

import com.example.demo5.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter  implements Filter {

    @Autowired
    AuthenticationManager manager;


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        var req=(HttpServletRequest)servletRequest;

        String authorization=req.getHeader("Authorization");

        var authentication=new CustomAuthentication(authorization,null);

        Authentication result= manager.authenticate(authentication);

        if(result.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(result);
            filterChain.doFilter(servletRequest,servletResponse);
        }






    }
}
