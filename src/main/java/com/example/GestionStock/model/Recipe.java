package com.example.GestionStock.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * Author: kingy
 */
@Getter
@Setter
@Entity
@Table(name = "Recipe")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
    
    private String image;
        
    @Column(columnDefinition = "text")
    private String description;
    
    private LocalDateTime date;
    
    @ElementCollection
    @CollectionTable(name = "recipe_likes", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "user_id")
    private Set<Long> likes = new HashSet<>();
}
