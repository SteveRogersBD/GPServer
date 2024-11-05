package com.example.SocialMedia.repository;

import com.example.SocialMedia.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findByUser_UserId(Long userId);

}
