package com.example.SocialMedia.controllers;

import com.example.SocialMedia.Message;
import com.example.SocialMedia.models.User;
import com.example.SocialMedia.responses.Response;
import com.example.SocialMedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/get/{id}")
    public Response<User> getById(@PathVariable Long id){
        Optional<User> oUser = userService.getById(id);
        List<User>data = new ArrayList<>();
        if(oUser.isPresent())
        {
            //found
            User user = oUser.get();
            data.add(user);
            return new Response<User>(Message.CREATED, HttpStatus.CREATED,data);
        }
        return new Response<User>(Message.FAILED,HttpStatus.NOT_FOUND,data);
    }

    @PostMapping("/create")
    public Response<User> createUser(@RequestBody User user)
    {
        Optional<User> oUser = userService.getById(user.getUserId());
        List<User>data = new ArrayList<>();
        if(oUser.isPresent())
        {
            return new Response<User>("User already exists!!!",
                    HttpStatus.CONFLICT,data);
        }
        data.add(user);
        userService.createUser(user);
        return new Response<User>(Message.UPDATED, HttpStatus.OK,data);
    }

    @DeleteMapping("/delete/{id}")
    public Response<User> createUser(@PathVariable Long id)
    {
        Optional<User> oUser = userService.getById(id);
        List<User>data= new ArrayList<>();
        if(oUser.isPresent())
        {
            //found
            User user = oUser.get();
            userService.deleteUser(user);
            data.add(user);
            return new Response<User>(Message.DELETED, HttpStatus.OK,data);
        }
        return new Response<User>(Message.FAILED, HttpStatus.NOT_FOUND,data);
    }

    @PostMapping("/update/{id}")
    public Response createUser(@PathVariable Long id,
                               @RequestBody User newUser)
    {
        Optional<User> oUser = userService.getById(id);
        List<User>data= new ArrayList<>();
        if(oUser.isPresent())
        {
            //found
            User user = oUser.get();
            user.setFullName(newUser.getFullName());
            user.setUsername(newUser.getUsername());
            user.setLocation(newUser.getLocation());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            userService.updateUser(user);
            data.add(user);
            return new Response<User>(Message.UPDATED, HttpStatus.OK,data);
        }
        return new Response<User>(Message.FAILED, HttpStatus.NOT_FOUND,data);
    }


}
