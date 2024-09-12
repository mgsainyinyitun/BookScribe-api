package com.sai.bookscribe.controllers.users;

import com.sai.bookscribe.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/user")
@RestController
public class UserController {
    @GetMapping("/me")
    public ResponseEntity<UserEntity> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> allUsers() {
        return ResponseEntity.ok(null);
    }
}

