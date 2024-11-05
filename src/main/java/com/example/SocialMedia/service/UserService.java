package com.example.SocialMedia.service;

import com.example.SocialMedia.models.User;
import com.example.SocialMedia.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User>getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User>getById(Long id){
        return userRepo.findById(id);
    }

    public void updateUser(User user)
    {
        userRepo.save(user);
    }

    public void deleteUser(User user)
    {
        userRepo.delete(user);
    }

    public void createUser(User user)
    {
        userRepo.save(user);
    }

    public void deleteAllUsers(){
        userRepo.deleteAll();
    }


}
