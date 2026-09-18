package com.example.user_api.parser;

import com.example.user_api.entity.UserEntity;
import com.example.user_api.model.UserRequest;
import com.example.user_api.model.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserParser {

    public UserEntity parseRequestToEntity(UserRequest request)
    {
       UserEntity entity=new UserEntity();
        entity.setName(request.getName().trim());
        entity.setEmail(request.getEmail().trim().toLowerCase());
        entity.setAge(request.getAge());
        entity.setDepartment(request.getDepartment().trim().toUpperCase());
        return entity;
    }
    public UserResponse parseEntityToResponse(UserEntity entity)
    {
      UserResponse response=new UserResponse();
           response.setId(entity.getId());
           response.setName(entity.getName());
           response.setEmail(entity.getEmail());
           response.setAge(entity.getAge());
           response.setDepartment(entity.getDepartment());
           response.setStatus("SUCCESS");
           response.setMessage("Operation successfull");
           return response;
}
   public UserResponse buildErrorResponse(String message)
   {
       UserResponse response=new UserResponse();
           response.setStatus("FAILURE");
            response.setMessage(message);
            return response;
   }


}
