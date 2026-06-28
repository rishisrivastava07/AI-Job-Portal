package com.rishiproject.job.Models.Payload;

import com.rishiproject.job.domain.Enums.SkillCategory;
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
public class JobSkillRequest {
    @NotBlank(message = "Skill name is required")
    @Size(max = 500, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Skill category is required")
    private SkillCategory skillCategory;

}