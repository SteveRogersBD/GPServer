package com.example.SocialMedia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name="comment_des")
    private String commentDes;

    @CreationTimestamp
    @Column(name="posted_at")
    private LocalDateTime postedAt;

    @ManyToOne
    @JoinColumn(name="post_id",nullable = false)
    private Post post;
}
