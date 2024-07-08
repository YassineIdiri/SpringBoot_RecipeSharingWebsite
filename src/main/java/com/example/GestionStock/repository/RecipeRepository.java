/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestionStock.repository;

import com.example.GestionStock.model.Recipe;
import com.example.GestionStock.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author kingy
 */
public interface RecipeRepository extends JpaRepository<Recipe,Long>{
    List<Recipe> findByTitleContainingIgnoreCase(String keyword);
    @Query(value = "SELECT r FROM Recipe r WHERE LOWER(CAST(r.description AS text)) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Recipe> searchByDescription(@Param("description") String description);
     List<Recipe> findByUser(User user);
}
