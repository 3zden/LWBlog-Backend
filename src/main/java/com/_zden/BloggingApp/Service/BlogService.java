package com._zden.BloggingApp.Service;


import com._zden.BloggingApp.Entities.Blog;
import com._zden.BloggingApp.Mapper.userMapper;
import com._zden.BloggingApp.Repo.BlogRepo;
import com._zden.BloggingApp.blogDTO.Blogresponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepo repo;
    userMapper mapper  = new userMapper();

    // Get all blogs
    public List<Blogresponse> getBlogs(){
        List<Blog> temp = repo.findAll();
        List<Blogresponse> blgs = temp.stream().
                map(blog -> mapper.toDto(blog)).toList();
        return blgs;
    }

    // Get Blog By id
    public Blogresponse getBlog(int id){
        return mapper.toDto(repo.findById(id).orElse(null));
    }

    // Post Blog
    public void postBlog(Blog blog){
        repo.save(blog);
    }
    // update a blog
    public void updateBlog(Blog blog) {
        repo.save(blog);
    }

    // delete a blog
    public void deleteBlog(int id){
        repo.deleteById(id);
    }
}
