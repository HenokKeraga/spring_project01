package com.example.demo2.service;

import com.example.demo2.model.User;
import com.example.demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JPAUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("sssss ::: "+s);
       Optional<User> u= userRepository.findById(1);
        System.out.println(u.isPresent());
       Optional<User> user= userRepository.findUserByUsername(s);

        System.out.println("user name " + user.isPresent());

       User user1=user.orElseThrow(()-> new UsernameNotFoundException("error !!"));

       SecurityUser securityUser= new SecurityUser(user1);

        return securityUser;
    }
}
