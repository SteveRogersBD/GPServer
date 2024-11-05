package com.example.SocialMedia.controllers;

import com.example.SocialMedia.Message;
import com.example.SocialMedia.models.Post;
import com.example.SocialMedia.responses.Response;
import com.example.SocialMedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/get/{id}")
    public Response<Post> getById(@PathVariable Long id){
        Optional<Post> oPost = postService.findById(id);
        List<Post>data = new ArrayList<>();
        if(oPost.isPresent())
        {
            //found
            Post post = oPost.get();
            data.add(post);
            return new Response<Post>(Message.CREATED, HttpStatus.CREATED,data);
        }
        return new Response<Post>(Message.FAILED,HttpStatus.NOT_FOUND,data);
    }

    @PostMapping("/create")
    public Response<Post> createPost(@RequestBody Post post)
    {
        List<Post>data = new ArrayList<>();
        postService.savePost(post);
        data.add(post);

        return new Response<Post>(Message.CREATED,HttpStatus.CREATED,data);
    }

    @PutMapping("/update/{id}")
    public Response<Post> updatePost(@PathVariable Long id,
            @RequestBody Post post)
    {
        List<Post>data = new ArrayList<>();
        Optional<Post>oPost = postService.findById(id);
        if(oPost.isPresent())
        {
            //found the post
            Post oldPost = oPost.get();
            oldPost.setCaption(post.getCaption());
            oldPost.setDescription(post.getDescription());
            oldPost.setImageUrl(post.getImageUrl());
            data.add(oldPost);
            postService.savePost(oldPost);
            return new Response<Post>(Message.UPDATED,HttpStatus.OK,data);
        }
        return new Response<Post>(Message.FAILED,HttpStatus.NOT_FOUND,data);
    }

    @PutMapping("/delete/{id}")
    public Response<Post> deletePost(@PathVariable Long id)
    {
        List<Post>data = new ArrayList<>();
        Optional<Post>oPost = postService.findById(id);
        if(oPost.isPresent())
        {
            //found the post
            Post oldPost = oPost.get();
            data.add(oldPost);
            postService.deletePost(oldPost);
            return new Response<Post>(Message.UPDATED,HttpStatus.OK,data);
        }
        return new Response<Post>(Message.FAILED,HttpStatus.NOT_FOUND,data);
    }
}
