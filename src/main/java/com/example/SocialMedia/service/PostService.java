package com.example.SocialMedia.service;

import com.example.SocialMedia.models.Post;
import com.example.SocialMedia.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    public void savePost(Post post)
    {
        postRepo.save(post);
    }

    public void deletePost(Post post)
    {
        postRepo.delete(post);
    }

    public Optional<Post> findById(Long id)
    {
        return postRepo.findById(id);
    }

    public List<Post> findAllPostByaUser(Long id){
        return postRepo.findByUser_UserId(id);
    }

    public List<Post> findAllPosts()
    {
        return postRepo.findAll();
    }

    public void deleteAllPosts()
    {
        postRepo.deleteAll();
    }


}
