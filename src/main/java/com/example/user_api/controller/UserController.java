package com.example.user_api.controller;

import com.example.user_api.model.UserRequest;
import com.example.user_api.model.UserResponse;
import com.example.user_api.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

   private static final Logger logger= LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @PostMapping("/save")
     public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody
                                                  UserRequest request)
     {
         logger.info("POST /api/user/save called");
         logger.debug("Request:name={},email={}",request.getName(),request.getEmail());
        UserResponse response=userService.saveUser(request);
        logger.info("user saved successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }
      @GetMapping("/all")
public  ResponseEntity<List<UserResponse>> getAll()
{
     logger.info("GET /api/user/all called");
      List<UserResponse> users=userService.getAllUsers();
      logger.info("Returning {} users",users.size());
       return ResponseEntity.ok(users);

}
   @GetMapping("/{id}")
public ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
{
     logger.info("GET /api/user/{} called",id);
      UserResponse response=userService.getUserById(id);
       return ResponseEntity.ok(response);
}
   @PutMapping("/{id}")
 public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request)
    {
        logger.info("PUT /api/user/{} called", id);
   UserResponse response=userService.updateUser(id,request);
        logger.info("User updated successfully");
     return ResponseEntity.ok(response);
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id)
    {logger.info("DELETE /api/user/{} called", id);
        UserResponse response=userService.deleteUser(id);

        logger.info("User deleted successfully");
         return ResponseEntity.ok(response);

    }

}
