package com.example.SocialMedia.controllers;

import com.example.SocialMedia.Message;
import com.example.SocialMedia.models.Comment;
import com.example.SocialMedia.responses.Response;
import com.example.SocialMedia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/get/{id}")
    public Response<Comment> getById(@PathVariable Long id)
    {
        Optional<Comment>oComment = commentService.findById(id);
        List<Comment>data = new ArrayList<>();
        if(oComment.isPresent())
        {
            Comment comment = oComment.get();
            return new Response<Comment>(Message.RETRIEVED, HttpStatus.OK,
                    data);
        }
        return new Response<Comment>(Message.FAILED, HttpStatus.NOT_FOUND,
                data);

    }

    @GetMapping("/get-all/{post_id}")
    public Response<Comment> getAllInAPost(@PathVariable Long post_id)
    {
        List<Comment>data = commentService.getAllCommentsInAPost(post_id);
        return new Response<Comment>(Message.RETRIEVED, HttpStatus.OK,
                data);

    }

    @PostMapping("/update")
    public Response<Comment> createComment(@RequestBody Comment comment)
    {
        Optional<Comment>oComment = commentService.findById(comment.getCommentId());
        List<Comment>data = new ArrayList<>();
        if(oComment.isPresent())
        {
            Comment oldComment = oComment.get();
            commentService.saveComment(comment);
            data.add(oldComment);
            return new Response<Comment>(Message.CREATED, HttpStatus.OK,
                    data);

        }
        return new Response<Comment>(Message.FAILED, HttpStatus.NOT_FOUND,
                data);

    }

    @DeleteMapping("/delete/{id}")
    public Response<Comment> deleteComment(@PathVariable Long id)
    {
        Optional<Comment>oComment = commentService.findById(id);
        List<Comment>data = new ArrayList<>();
        if(oComment.isPresent())
        {
            Comment comment = oComment.get();
            commentService.deleteComment(comment);
            data.add(comment);
            return new Response<Comment>(Message.DELETED, HttpStatus.OK,
                    data);
        }
        return new Response<Comment>(Message.FAILED, HttpStatus.NOT_FOUND,
                data);
    }

    @PutMapping("/update/{id}")
    public Response<Comment> updateComment(@PathVariable Long id,
                                           @RequestBody Comment newComment)
    {
        Optional<Comment>oComment = commentService.findById(id);
        List<Comment>data = new ArrayList<>();
        if(oComment.isPresent())
        {
            Comment comment = oComment.get();
            comment.setCommentDes(newComment.getCommentDes());
            data.add(comment);
            return new Response<Comment>(Message.UPDATED, HttpStatus.OK,
                    data);
        }

        return new Response<Comment>(Message.FAILED, HttpStatus.NOT_FOUND,
                data);
    }

}
