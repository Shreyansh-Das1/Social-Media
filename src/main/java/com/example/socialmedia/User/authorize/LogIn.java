package com.example.socialmedia.User.authorize;

import com.example.socialmedia.User.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.socialmedia.User.UserRepository;
import com.example.socialmedia.User.security.Encrypter;

@Service
public class LogIn {
    @Autowired
    UserRepository userepo;
    @Autowired
    private Encrypter encrypter;

    public ResponseEntity<Users> logIn(String email, String password) {
        if(userepo.existsByEmail(email)) {
            Users user = userepo.findByEmail(email);

            if (encrypter.encryptPassword(password).equals(user.getPassword()))
                return new ResponseEntity<>(user, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}