package com.example.socialmedia.Posts;

import com.example.socialmedia.User.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Entity
@Data
@Table(name = "Posts")

public class Post {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) int id;
    byte[] media;
    String caption;
    int likes;
    @OneToMany
  //  ArrayList<Comment> cmt;
    @ManyToOne
    @JoinColumn(name = "user_username", nullable = false)
    private Users user;

}
