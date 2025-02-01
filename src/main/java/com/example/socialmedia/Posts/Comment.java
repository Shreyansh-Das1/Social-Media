package com.example.socialmedia.Posts;

import com.example.socialmedia.User.Users;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.userdetails.User;

@Data
@Entity
@Table(name= "Comments")
public class Comment {
    String comment;

    @Id @GeneratedValue(strategy = GenerationType.AUTO) int id;
    User user;
    }
