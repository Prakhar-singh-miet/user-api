package com.example.user_api.controller;

import com.example.user_api.model.UserRequest;
import com.example.user_api.model.UserResponse;
import com.example.user_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/save")
     public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody
                                                  UserRequest request)
     {
        UserResponse response=userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }


}
