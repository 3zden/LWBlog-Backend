package com._zden.BloggingApp.Service;


import com._zden.BloggingApp.Entities.Blog;
import com._zden.BloggingApp.Repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepo repo;

    // Get all blogs
    public List<Blog> getBlogs(){
        return repo.findAll();
    }

    // Get Blog By id
    public Blog getBlog(int id){
        return repo.findById(id).orElse(null);
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
