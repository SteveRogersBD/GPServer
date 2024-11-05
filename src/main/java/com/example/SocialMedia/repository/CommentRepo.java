package com.example.SocialMedia.repository;

import com.example.SocialMedia.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByPost_PostId(Long Id);
}
