/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestionStock.service.serviceImpl;

import com.example.GestionStock.model.Recipe;
import com.example.GestionStock.model.User;
import com.example.GestionStock.repository.RecipeRepository;
import com.example.GestionStock.service.RecipeService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kingy
 */
@Service
public class RecipeServiceImpl implements RecipeService{
    
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe newRecipe(Recipe recipe, User user) {
        Recipe newRecipe = new Recipe();
        newRecipe.setTitle(recipe.getTitle());
        newRecipe.setImage(recipe.getImage());
        newRecipe.setDescription(recipe.getDescription());
        newRecipe.setUser(user);  
        newRecipe.setDate(LocalDateTime.now()); 
        
        return recipeRepository.save(newRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {
        Optional<Recipe> opt = recipeRepository.findById(id);
        
        if(opt.isPresent())
        {
            return opt.get();
        }

        throw new Exception("Recipe not found with id: "+ id);

    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
        findRecipeById(id);
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe oldRecipe = findRecipeById(id);  
        
        if(recipe.getTitle()!=null){
            oldRecipe.setTitle(recipe.getTitle());
        }
        if(recipe.getImage()!=null){
            oldRecipe.setImage(recipe.getImage());
        }
        if(recipe.getDescription()!=null){
            oldRecipe.setDescription(recipe.getDescription());
        }
        
        return recipeRepository.save(oldRecipe);
        
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
        }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        Recipe recipe = findRecipeById(recipeId);
        
        if(recipe.getLikes().contains(user.getId()))
        {
            recipe.getLikes().remove(user.getId());
        }
        else
        {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }
    
    @Override
    public List<Recipe> searchRecipesByTitle(String keyword) {
        return recipeRepository.findByTitleContainingIgnoreCase(keyword);
    }   
    @Override
     public List<Recipe> searchRecipesByCategory(String keyword){
        return recipeRepository.searchByDescription(keyword);
    }  
     
      @Override
    public List<Recipe> findRecipesByUser(User user) {
        return recipeRepository.findByUser(user);
    }
    @Override
    public int getLikesCount(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + recipeId));
        
        Set<Long> likes = recipe.getLikes();
        return likes.size();
    }
    
}
