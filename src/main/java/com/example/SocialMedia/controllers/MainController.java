package com.example.SocialMedia.controllers;

import com.example.SocialMedia.Message;
import com.example.SocialMedia.models.Post;
import com.example.SocialMedia.models.User;
import com.example.SocialMedia.responses.Response;
import com.example.SocialMedia.service.CommentService;
import com.example.SocialMedia.service.PostService;
import com.example.SocialMedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;

    @GetMapping("/hi")
    public String getHi(){
        return "Hi this is from server.";
    }

    @GetMapping("/get-all-user")
    public Response<User> getAllUsers(){

        return new Response<User>(Message.RETRIEVED,HttpStatus.OK,
                userService.getAllUsers());
    }

    @DeleteMapping("/delete-all-user")
    public Response<User> deleteAllUsers(){

        List<User>all = userService.getAllUsers();
        userService.deleteAllUsers();
        return new Response<User>(Message.DELETED,HttpStatus.OK,all);
    }

    @GetMapping("/get-all-post")
    public Response<Post> getAllPosts(){

        return new Response<Post>(Message.RETRIEVED,HttpStatus.OK,
                postService.findAllPosts());
    }
    @DeleteMapping("/delete-all-post")
    public Response<Post> deleteAllPosts(){

        List<Post> data = postService.findAllPosts();
        postService.deleteAllPosts();
        return new Response<Post>(Message.DELETED,HttpStatus.OK,data);
    }

}
