package com.rishiproject.job.Controllers;

import com.rishiproject.job.Modals.Mapper.UserMapper;
import com.rishiproject.job.Modals.Users.UpdatedUserProfileRequest;
import com.rishiproject.job.Modals.Users.User;
import com.rishiproject.job.Services.UserService;
import com.rishiproject.job.dto.Response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(@RequestHeader("X-User-Email") String email) throws Exception {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.mapToDTO((user)));
    }

    @PutMapping("/update-profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestHeader("X-User-Email") String email, @RequestBody @Valid UpdatedUserProfileRequest updateProfile) throws Exception {
        UserResponse updatedProfile = userService.updateProfile(email, updateProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getProfileById(@PathVariable Long userId) throws Exception {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(UserMapper.mapToDTO((user)));
    }

    @GetMapping("/all-profiles")
    public ResponseEntity<List<UserResponse>> getAllProfiles() throws Exception {
        return ResponseEntity.ok(UserMapper.mapToDTO(userService.getAllUsers()));
    }

    @PatchMapping("/{userId}/suspend")
    public ResponseEntity<UserResponse> suspendProfile(@PathVariable Long userId) throws Exception {
        UserResponse suspendedUser = userService.suspendUser(userId);
        return ResponseEntity.ok(suspendedUser);
    }

    @PatchMapping("/{userId}/activate")
    public ResponseEntity<UserResponse> activateProfile(@PathVariable Long userId) throws Exception {
        UserResponse activatedUser = userService.activateUser(userId);
        return ResponseEntity.ok(activatedUser);
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<UserResponse> deleteProfile(@PathVariable Long userId) throws Exception {
        UserResponse deletedUser = userService.deleteUser(userId);
        return ResponseEntity.ok(deletedUser);
    }

}
