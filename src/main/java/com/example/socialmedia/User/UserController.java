package com.example.socialmedia.User;

import com.example.socialmedia.User.authorize.LogIn;
import com.example.socialmedia.User.authorize.SignUp;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
class UserController {
    private final LogIn logIn;
    private final SignUp signUp;
    private final UserRepository ur;
    @Autowired
    public UserController(LogIn logIn, SignUp signUp, UserRepository ur) {
        this.logIn = logIn;
        this.signUp = signUp;
        this.ur = ur;
    }

    @GetMapping("/login")
    public @ResponseBody ResponseEntity<Users> login(
            @RequestBody Request req)
    {
        if(ur.existsByEmail(req.getEmail()))
            return logIn.logIn(req.getEmail(),req.getPassword());
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signup")
    public @ResponseBody ResponseEntity<String> signup(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String username
    ) {
        if(ur.existsByEmail(email))
            return new ResponseEntity<>("Email Already Exists", HttpStatus.CONFLICT);
        if(ur.existsByUsername(username))
            return new ResponseEntity<>("Username Already Exists", HttpStatus.CONFLICT);
        return signUp.createUser(email, password, username);
    }

    @DeleteMapping("/deluser")
    public @ResponseBody ResponseEntity<String> delUser(@RequestBody Request req)
    {
        ResponseEntity<Users> auth = login(req);
        HttpStatusCode state = auth.getStatusCode();
        if(state == HttpStatus.OK){
            ur.delete(auth.<User>getBody());
            return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
//a DTO is a Data Transfer Object, self explanatory bruh
@Getter
class Request {
    private String email;
    private String password;
}