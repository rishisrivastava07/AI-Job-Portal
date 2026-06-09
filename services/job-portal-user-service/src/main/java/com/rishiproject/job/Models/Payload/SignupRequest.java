package com.rishiproject.job.Models.Payload;

import com.rishiproject.job.domain.Enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank(message = "Full Name is mandatory")
    private String fullName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private Long phone;

    @NotNull(message = "UserRole is mandatory")
    private UserRole role;
}