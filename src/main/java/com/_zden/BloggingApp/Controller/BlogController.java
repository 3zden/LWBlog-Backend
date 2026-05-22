package com._zden.BloggingApp.Controller;


import com._zden.BloggingApp.Entities.Blog;
import com._zden.BloggingApp.Service.BlogService;
import com._zden.BloggingApp.blogDTO.Blogresponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogService service;

    @GetMapping("/v1/blogs")
    public List<Blogresponse> getBlogs() {
        return service.getBlogs();
    }

    @GetMapping("/v1/blogs/{id}")
    public Blogresponse getBlog(@PathVariable int id){
        return service.getBlog(id);
    }

    @PostMapping("/v1/blogs")
    public void postBlog(@RequestBody Blog blog){
        service.postBlog(blog);
    }

    @PutMapping("/v1/blogs")
    public void updateBlog(@RequestBody Blog blog){
        service.updateBlog(blog);
    }

    @DeleteMapping("/v1/blogs/{id}")
    public void deleteBlog(@PathVariable int id){
        service.deleteBlog(id);
    }
}



