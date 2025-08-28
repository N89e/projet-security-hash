package com.example.porjet_security.controlers;

import com.example.porjet_security.items.UserApp;
import com.example.porjet_security.services.JwtAuthentificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

    private final JwtAuthentificationService jwtService;
    private String lastJwt;

    public JwtController(JwtAuthentificationService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/create-jwt")
    public String createJwt() {
        lastJwt = jwtService.generateToken("demoUser");
        return lastJwt;
    }
    @PostMapping("/create-jwt")
    public ResponseEntity<String> createJwtPost(@RequestBody UserApp user) {
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body("Username requis");
        }
        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/get-jwt")
    public String getJwt() {
        return lastJwt != null ? lastJwt : "Aucun JWT généré";
    }

    @GetMapping("/verify-jwt/{jwt}")
    public boolean verifyJwt(@PathVariable String jwt) {
        return jwtService.isValidJwt(jwt);
    }
}
