package com._zden.BloggingApp.Controller;


import com._zden.BloggingApp.Entities.Blog;
import com._zden.BloggingApp.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogService service;

    @GetMapping("/blogs")
    public List<Blog> getBlogs() {
        return service.getBlogs();
    }

    @GetMapping("/blogs/{id}")
    public Blog getBlog(@PathVariable int id){
        return service.getBlog(id);
    }

    @PostMapping("/blogs")
    public void postBlog(@RequestBody Blog blog){
        service.postBlog(blog);
    }

    @PutMapping("/blogs")
    public void updateBlog(@RequestBody Blog blog){
        service.updateBlog(blog);
    }

    @DeleteMapping("/blogs/{id}")
    public void deleteBlog(@PathVariable int id){
        service.deleteBlog(id);
    }
}



