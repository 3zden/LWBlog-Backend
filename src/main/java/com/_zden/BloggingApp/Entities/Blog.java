package com._zden.BloggingApp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;
@Data
@Entity
public class Blog {
    @Id
    private int id;
    private String blogName;
    private String blogContent;
    private int likes;
    private String author;

    //Constructor
    public Blog(int id, String blogName,String blogContent, String author){
        this.author = author;
        this.id = id;
        this.blogName = blogName;
        this.blogContent = blogContent;
        this.likes = 0;
    }
    public Blog(){}
}
