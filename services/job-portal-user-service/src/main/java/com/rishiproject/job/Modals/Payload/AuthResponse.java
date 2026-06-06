package com.rishiproject.job.Modals.Payload;

import com.rishiproject.job.dto.Response.UserResponse;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String title;
    private String message;
    private UserResponse user;
}
