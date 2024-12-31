package com.ecommerce.monaco.service;

import com.ecommerce.monaco.entities.User;
import com.ecommerce.monaco.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> user = userRepository.findByUsername(username);
         if(user.isPresent()){
             return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                     user.get().getPassword(), new ArrayList<>());
         }else{
             throw new UsernameNotFoundException("Username not found");
         }
    }

}

