/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestionStock.repository;

import com.example.GestionStock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kingy
 */
public interface UserRepository extends JpaRepository<User,Long>
{
    public User findByEmail(String email);
    
}
