package com.rishiproject.job.Models.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobTagRequest {
    @NotBlank(message = "Tag name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;
}
