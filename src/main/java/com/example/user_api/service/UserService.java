package com.example.user_api.service;


import com.example.user_api.entity.UserEntity;
import com.example.user_api.exception.ResourceNotFoundException;
import com.example.user_api.model.UserRequest;
import com.example.user_api.model.UserResponse;
import com.example.user_api.parser.UserParser;
import com.example.user_api.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepo userRepository;
    @Autowired
    UserParser userParser;

    public UserResponse saveUser(UserRequest request) {
        UserEntity userEntity = userParser.parseRequestToEntity(request);
        UserEntity saved = userRepository.save(userEntity);
        return userParser.parseEntityToResponse(saved);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(entity -> userParser.parseEntityToResponse(entity))
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id" + id
                ));
        return userParser.parseEntityToResponse(userEntity);

    }

    public UserResponse updateUser(Long id, UserRequest request) {
        UserEntity existing = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("user not found with id" + id));
        UserEntity updated = userParser.parseRequestToEntity(request);
        updated.setId(existing.getId());
        UserEntity saved = userRepository.save(updated);
        return userParser.parseEntityToResponse(saved);
    }

    public UserResponse deleteUser(Long id)
    {
        UserEntity entity=userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("user not found eith id"+id));
                userRepository.deleteById(id);
                UserResponse response=userParser.parseEntityToResponse(entity);
                response.setMessage("User deleted successfully");
                return response;

    }
}