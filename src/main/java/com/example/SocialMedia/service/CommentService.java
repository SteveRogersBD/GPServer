package com.example.SocialMedia.service;

import com.example.SocialMedia.models.Comment;
import com.example.SocialMedia.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    public Optional<Comment> findById(Long id)
    {
        return commentRepo.findById(id);
    }

    public List<Comment> getAllComments()
    {
        return commentRepo.findAll();
    }

    public void deleteComment(Comment comment)
    {
        commentRepo.delete(comment);
    }

    public void saveComment(Comment comment)
    {
        commentRepo.save(comment);
    }

    public List<Comment> getAllCommentsInAPost(Long postId)
    {
        return commentRepo.findByPost_PostId(postId);
    }
}
