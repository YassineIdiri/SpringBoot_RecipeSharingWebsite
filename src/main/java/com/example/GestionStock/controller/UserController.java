/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestionStock.controller;

import com.example.GestionStock.model.User;
import com.example.GestionStock.repository.UserRepository;
import com.example.GestionStock.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kingy
 */
@RestController
public class UserController {
    
  @Autowired
  private UserService userService;
  
  @GetMapping("/api/users/profile")
  public User findUserByJwt(@RequestHeader("Authorization")String jwt) throws Exception
  {
      User user = userService.findUserByJwt(jwt);
      return user;
  }
}
