package com.example.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.dto.UserDTO;
import com.example.jpa.dto.UserDetailsDTO;
import com.example.jpa.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDTO findById(@PathVariable Integer userId) {
        return userService.findById(userId);
    }

    @GetMapping("/{userId}/details")
    public UserDetailsDTO findByIdDetails(@PathVariable Integer userId) {
        return userService.findByIdDetails(userId);
    }
}
