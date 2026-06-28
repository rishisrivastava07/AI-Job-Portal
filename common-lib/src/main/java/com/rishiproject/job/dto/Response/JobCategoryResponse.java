package com.rishiproject.job.dto.Response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCategoryResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String iconUrl;
    private Boolean isActive;

    private Long parentId;
    private String parentName;

    private List<JobCategoryResponse> subCategories;
    private LocalDateTime createdAt;
}
