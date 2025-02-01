package com.example.socialmedia.User.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Encrypter {

    @Autowired
    PasswordEncoder passwordEncoder;
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
