package com.rishiproject.job.Models.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCategoryRequest {

    @NotBlank(message = "Category Name cannot be null it's required")
    private String name;

    @Size(max = 500, message = "Description  must not exceed 500 characters")
    private String description;

    private String iconUrl;

    private Long parentId;
}
