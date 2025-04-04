package com.example.socialmedia.User;

//import com.example.socialmedia.Posts.Comment;
import com.example.socialmedia.Posts.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Entity
@Data
@Table(name = "Users")

public class Users {

    @Id private String username;
    @Column(unique = true, nullable = false)
    @Nullable private String email,  bio;
    @JsonIgnore
    private String password;
    //@OneToMany @Nullable @JsonIgnore
    //ArrayList<Post> post;
    //@OneToMany  @Nullable
    //ArrayList<Comment> comments;
}
