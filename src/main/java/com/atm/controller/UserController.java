package com.atm.controller;


import com.atm.dto.AuthRequest;
import com.atm.dto.CreateUserRequest;
import com.atm.model.User;
import com.atm.service.TokenService;
import com.atm.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final TokenService tokenService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World! this is ATM";
    }

    @PostMapping("/addNewUser")
    public User addUser(@Valid @RequestBody CreateUserRequest request){
            return service.createUser(request);
    }
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        return tokenService.generateToken(request);
    }

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return service.loadUserByUsername(username);
    }

    @GetMapping("/user")
    public String getUserString() {
        return "This is USER!";
    }

    @GetMapping("/admin")
    public String getAdminString() {
        return "This is ADMIN!";
    }
}