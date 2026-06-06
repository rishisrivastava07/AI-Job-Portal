package com.rishiproject.job.Services.Interfaces;

import com.rishiproject.job.Modals.Users.UpdatedUserProfileRequest;
import com.rishiproject.job.Modals.Users.User;
import com.rishiproject.job.dto.Response.UserResponse;

import java.util.List;

public interface IUserService {
    User getUserByEmail(String email) throws Exception;
    User getUserById(Long id) throws Exception;
    List<User> getAllUsers();
    UserResponse updateProfile(String email, UpdatedUserProfileRequest req) throws Exception;

    // Admin Actions
    UserResponse suspendUser(Long id) throws Exception;
    UserResponse activateUser(Long id) throws Exception;
    UserResponse deleteUser(Long id) throws Exception;
}
