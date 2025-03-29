package com.example.socialmedia.User;

import com.example.socialmedia.User.authorize.LogIn;
import com.example.socialmedia.User.authorize.SignUp;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
class UserController {
    private final LogIn logIn;
    private final SignUp signUp;

    @Autowired
    public UserController(LogIn logIn, SignUp signUp) {
        this.logIn = logIn;
        this.signUp = signUp;
    }

    @GetMapping("/login")
    public @ResponseBody ResponseEntity<Users> login(
            @RequestBody Request req)
    {

        return logIn.logIn(req.getEmail(),req.getPassword());
    }

    @PostMapping("/signup")
    public @ResponseBody ResponseEntity<String> signup(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String username
    ) {
        return signUp.createUser(email, password, username);
    }
}
//a DTO is a Data Transfer Object, self explanatory bruh
@Getter
class Request {
    private String email;
    private String password;
}