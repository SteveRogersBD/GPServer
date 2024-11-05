package com.example.SocialMedia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long postId;

    @Column(name="caption")
    private String caption;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="description")
    private String description;

    @Column(name="reaction")
    private int reaction=0;

    @CreationTimestamp
    @Column(name="posted_at")
    private LocalDateTime postedAt;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

}
