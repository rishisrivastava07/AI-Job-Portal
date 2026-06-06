package com.rishiproject.job.Modals.Users;

import lombok.Data;

@Data
public class UpdatedUserProfileRequest {
    private String fullName;
    private Long phone;
    private String profileImage;
}
