package com._zden.BloggingApp.Mapper;

import com._zden.BloggingApp.Entities.Blog;
import com._zden.BloggingApp.blogDTO.Blogresponse;

public class userMapper {
    public Blogresponse toDto(Blog blog){
        return new Blogresponse(
                blog.getTitle(),
                blog.getContent(),
                blog.getAuthor(),
                blog.getLikes()
        );
    }
}
