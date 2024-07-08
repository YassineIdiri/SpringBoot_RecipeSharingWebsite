/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestionStock.service;

import com.example.GestionStock.model.User;
import com.example.GestionStock.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author kingy
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(username);
        
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found wuth email: "+ username); 
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities); 
    } 
}
