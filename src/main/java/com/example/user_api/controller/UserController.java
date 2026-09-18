package com.example.user_api.controller;

import com.example.user_api.model.UserRequest;
import com.example.user_api.model.UserResponse;
import com.example.user_api.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
      @GetMapping("/all")
public  ResponseEntity<List<UserResponse>> getAll()
{
      List<UserResponse> users=userService.getAllUsers();
       return ResponseEntity.ok(users);

}
   @GetMapping("/{id}")
public ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
{
      UserResponse response=userService.getUserById(id);
       return ResponseEntity.ok(response);
}
   @PutMapping("/{id}")
 public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request)
    {
   UserResponse response=userService.updateUser(id,request);
     return ResponseEntity.ok(response);
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id)
    {
        UserResponse response=userService.deleteUser(id);
         return ResponseEntity.ok(response);
    }

}
