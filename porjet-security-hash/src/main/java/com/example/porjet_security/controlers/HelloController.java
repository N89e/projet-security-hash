package com.example.porjet_security.controlers;

import com.example.porjet_security.services.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final AuthService authService;

    public HelloController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/hello/public")
    public String helloPublic() {
        return "hello public";
    }

    @PostMapping("/hello/public")
    public String helloPublicPost() {
        return "hello public POST";
    }

    @GetMapping("/hello/private")
    public String helloPrivate() {
        return "hello private";
    }

    @PostMapping("/hello/private")
    public String helloPrivatePost() {
        return "hello private POST";
    }

    @GetMapping("/current-user")
    public String currentUser() {
        return "Utilisateur connecté : " + authService.getCurrentUsername();
    }
}
