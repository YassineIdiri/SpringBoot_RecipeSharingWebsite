/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.GestionStock.service;

import com.example.GestionStock.model.Recipe;
import com.example.GestionStock.model.User;
import java.util.List;

/**
 *
 * @author kingy
 */
public interface RecipeService {
    
   public Recipe newRecipe(Recipe recipe, User user);
   
   public Recipe findRecipeById(Long id) throws Exception;
   
   public void deleteRecipe(Long id) throws Exception;
   
   public Recipe updateRecipe(Recipe recipe, Long id) throws Exception;
   
   public List<Recipe> findAllRecipe();
   
   public Recipe likeRecipe(Long recipeId, User user) throws Exception;
   
   public List<Recipe> searchRecipesByTitle(String keyword);
   
   public List<Recipe> searchRecipesByCategory(String keyword);
   
   List<Recipe> findRecipesByUser(User user);

   public int getLikesCount(Long recipeId);
}