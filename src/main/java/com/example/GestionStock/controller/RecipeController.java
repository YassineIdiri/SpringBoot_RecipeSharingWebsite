/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestionStock.controller;

import com.example.GestionStock.model.Recipe;
import com.example.GestionStock.model.User;
import com.example.GestionStock.service.RecipeService;
import com.example.GestionStock.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kingy
 */
@RestController
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    
    @PostMapping("/api/recipe")
    public Recipe newRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user = userService.findUserByJwt(jwt);
        Recipe newRecipe = recipeService.newRecipe(recipe, user);
        return newRecipe;
    }
    
    @GetMapping("/api/recipe")
    public List<Recipe> getAllRecipe()
    {
        List<Recipe> recipes = recipeService.findAllRecipe();
        return recipes;
    }
    
    @DeleteMapping("/api/recipe/delete/{recipeId}")
    public Map<String, String> deleteRecipe(@PathVariable Long recipeId) throws Exception
    {
        recipeService.deleteRecipe(recipeId);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Recipe deleted successfully");
        return response;
    }
    
    @PutMapping("/api/recipe/update/{recipeId}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long recipeId) throws Exception
    {        
        Recipe updatedRecipe = recipeService.updateRecipe(recipe, recipeId);
        return updatedRecipe;
    }
    
    @PutMapping("/api/recipe/{id}/like")
    public Recipe likeRecipe(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception
    {        
        User user = userService.findUserByJwt(jwt);
        
        Recipe likedRecipe = recipeService.likeRecipe(id, user);
        return likedRecipe;
    }
    
    @GetMapping("/api/recipe/{id}/like/count")
    public Map<String, Integer> getLikesCount(@PathVariable Long id) {
        int likesCount = recipeService.getLikesCount(id);
        
        Map<String, Integer> response = new HashMap<>();
        response.put("likesCount", likesCount);
        return response;
    }
    
     @GetMapping("/api/recipe/search/{word}")
    public List<Recipe> searchRecipes(@PathVariable String word) {
        return recipeService.searchRecipesByTitle(word);
    }
    
      @GetMapping("/api/recipe/category/{word}")
    public List<Recipe> searchRecipesCategory(@PathVariable String word) {
        return recipeService.searchRecipesByCategory(word);
    }
    
    @GetMapping("/api/recipe/user")
    public List<Recipe> getRecipesByCurrentUser(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return recipeService.findRecipesByUser(user);
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipeById(@PathVariable Long id) throws Exception {
        return recipeService.findRecipeById(id);
    }
}
