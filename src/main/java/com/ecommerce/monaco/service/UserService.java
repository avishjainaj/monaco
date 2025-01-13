package com.ecommerce.monaco.service;

import com.ecommerce.monaco.entities.User;
import com.ecommerce.monaco.exceptions.BadRequestException;
import com.ecommerce.monaco.exceptions.InvalidUserException;
import com.ecommerce.monaco.repositories.UserRepository;
import com.ecommerce.monaco.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Create a new user
     * @param user
     * @return
     */
    public User createUser(User user){
        if(Objects.isNull(user.getUsername()) || Objects.isNull(user.getPassword()) || Objects.isNull(user.getEmail())){
            throw new BadRequestException("Username, password and email are required");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Login a user
     * @param user
     * @return
     */
    public String loginUser(User user){
        if(Objects.isNull(user.getUsername()) || Objects.isNull(user.getPassword())){
             throw new BadRequestException("Username and password are required");
        }
        Optional<User> userDataOptional = userRepository.findByUsername(user.getUsername());
        if(userDataOptional.isPresent()) {
            User userData = userDataOptional.get();
            if (passwordEncoder.matches(user.getPassword(), userData.getPassword())) {
                return jwtUtil.generateToken(userData.getUsername());
            }
        }
        throw new InvalidUserException("Invalid username or password");
    }
}
