package com.rishiproject.job.Models.Mapper;

import com.rishiproject.job.Models.Users.User;
import com.rishiproject.job.dto.Response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponse mapToDTO(User user){
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPassword(user.getPassword());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setProfileImage(user.getProfileImage());
        dto.setLastLoggedIn(user.getLastLoggedIn());
        dto.setStatus(user.getStatus());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    public static List<UserResponse> mapToDTO(List<User> users){
        return users.stream().map(UserMapper::mapToDTO).collect(Collectors.toList());
    }
}
