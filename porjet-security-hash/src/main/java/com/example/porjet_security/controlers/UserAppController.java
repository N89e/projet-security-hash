package com.example.porjet_security.controlers;

import com.example.porjet_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-app")
public class UserAppController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String userApp() throws Exception {
        return userRepository.findAll().toString();
    }
}
