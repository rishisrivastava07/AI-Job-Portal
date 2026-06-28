package com.rishiproject.job.dto.Response;

import com.rishiproject.job.domain.Enums.SkillCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSkillResponse {
    private Long id;
    private String name;
    private String slug;
    private Boolean isActive;
    private SkillCategory skillCategory;
    private LocalDateTime createdAt;
}
