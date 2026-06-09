package com.rishiproject.job.Models.Users;

import lombok.Data;

@Data
public class UpdatedUserProfileRequest {
    private String fullName;
    private Long phone;
    private String profileImage;
}
