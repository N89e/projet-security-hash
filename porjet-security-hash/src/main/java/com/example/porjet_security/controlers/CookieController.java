package com.example.porjet_security.controlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @GetMapping("/get-cookie")
    public ResponseEntity<String> getCookie() {
        String cookieName = "myCookie";
        String cookieValue = "123456";

        ResponseCookie tokenCookie = ResponseCookie.from(cookieName, cookieValue).build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                .body("cookie posé avec succès");
    }
}
