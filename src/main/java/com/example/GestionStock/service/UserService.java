/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestionStock.service;

import com.example.GestionStock.model.User;

/**
 *
 * @author kingy
 */
public interface UserService {
    public User findUserById(Long userId) throws Exception;
    
    public User findUserByJwt(String jwt)throws Exception;
}
