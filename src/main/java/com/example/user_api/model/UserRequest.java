package com.example.user_api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequest {

     @NotBlank(message="name cannot be empty")
    private String name;
     @NotBlank(message="email cannot be empty")
    private String email;
     @NotNull(message="age cannot be null")
    private Integer age;
     @NotBlank(message="department cannot be empty")
    private String department;

    UserRequest()
    {

    }

    public @NotBlank(message = "name cannot be empty") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "name cannot be empty") String name) {
        this.name = name;
    }

    public @NotBlank(message = "email cannot be empty") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "email cannot be empty") String email) {
        this.email = email;
    }

    public @NotNull(message = "age cannot be null") Integer getAge() {
        return age;
    }

    public void setAge(@NotNull(message = "age cannot be null") Integer age) {
        this.age = age;
    }

    public @NotBlank(message = "department cannot be empty") String getDepartment() {
        return department;
    }

    public void setDepartment(@NotBlank(message = "department cannot be empty") String department) {
        this.department = department;
    }
}
