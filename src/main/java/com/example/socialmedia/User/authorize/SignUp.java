package com.example.socialmedia.User.authorize;


import com.example.socialmedia.User.Users;
import com.example.socialmedia.User.UserRepository;
import com.example.socialmedia.User.security.Encrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignUp {


    @Autowired
    UserRepository userepo;

    @Autowired
    private Encrypter encrypter;

    public ResponseEntity<String> createUser(String email, String password, String username)
    {
        if(userepo.existsByEmail(email))
            return new ResponseEntity<>("Email Already Exists", HttpStatus.CONFLICT);

        if(userepo.existsByUsername(username))
            return new ResponseEntity<>("Username Already Exists", HttpStatus.CONFLICT);

        Users user = new Users();
        user.setEmail(email);
        user.setPassword(encrypter.encryptPassword(password));
        user.setUsername(username);
        user.setBio("Hi");
        userepo.save(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }


}
