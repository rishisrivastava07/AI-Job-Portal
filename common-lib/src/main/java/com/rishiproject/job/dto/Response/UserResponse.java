package com.rishiproject.job.dto.Response;

import com.rishiproject.job.domain.UserRole;
import com.rishiproject.job.domain.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;
    private String fullName;
    private String password;
    private String email;
    private Long phone;
    private String profileImage;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoggedIn;

}
