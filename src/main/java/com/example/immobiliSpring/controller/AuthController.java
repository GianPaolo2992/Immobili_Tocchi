package com.example.immobiliSpring.controller;

import com.example.immobiliSpring.DTO.UserDTO;
import com.example.immobiliSpring.entity.User;
import com.example.immobiliSpring.service.JwtService;
import com.example.immobiliSpring.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    public AuthController(UserService userService, JwtService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;

    }

//    @PostMapping("/register")
//    public void register(@RequestParam String username, @RequestParam String email, @RequestParam String password ){
//        userService.registerUser(username,email,password);
//
//    }
@PostMapping("/register")
public void register(@RequestBody UserDTO userDTO) {
    userService.registerUser(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
}

//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
//            return jwtService.generateToken(username);
//        } else {
//            throw new RuntimeException("Invalid credentials");
//        }
//    }
@PostMapping("/login")
public String login(@RequestBody UserDTO userDTO) {
    User user = userService.findByUsername(userDTO.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (new BCryptPasswordEncoder().matches(userDTO.getPassword(), user.getPassword())) {
        return jwtService.generateToken(userDTO.getUsername());
    } else {
        throw new RuntimeException("Invalid credentials");
    }
}


}
