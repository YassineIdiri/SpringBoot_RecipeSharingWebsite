/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestionStock.controller;

import com.example.GestionStock.config.JwtProvider;
import com.example.GestionStock.model.User;
import com.example.GestionStock.repository.UserRepository;
import com.example.GestionStock.request.LoginRequest;
import com.example.GestionStock.response.AuthResponse;
import com.example.GestionStock.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kingy
 */
@RestController
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomerUserDetailsService customerUserDetails;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/signup")
    public AuthResponse newUser(@RequestBody User user) throws Exception
    {
        String email =user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        
        User isExistEmail = userRepository.findByEmail(email);
        
        if(isExistEmail != null)
        {
            throw new Exception("Email is already use with another account");
        }
        
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFullName(fullName);
        
        User savedUser = userRepository.save(newUser);
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtProvider.generateToken(authentication);
        
        AuthResponse res = new AuthResponse();
        
        res.setJwt(token);
        res.setMessage("Signup success");
        return res;
    }
    
    @PostMapping("/signin")
    public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest)
    {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        
        AuthResponse res = new AuthResponse();
        
        res.setJwt(token);
        res.setMessage("Signin success");
        return res;
        
        
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetails.loadUserByUsername(username);
        
        if(userDetails == null)
        {
            throw new BadCredentialsException("User not found");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword()))
        {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
    }
    
}
