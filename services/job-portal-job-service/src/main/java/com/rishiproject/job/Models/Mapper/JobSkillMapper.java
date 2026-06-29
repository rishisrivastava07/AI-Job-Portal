package com.rishiproject.job.Models.Mapper;

import com.rishiproject.job.Models.JobSkill;
import com.rishiproject.job.dto.Response.JobSkillResponse;

public class JobSkillMapper {

    public static JobSkillResponse mapToDto(JobSkill jobSkill){
        return  JobSkillResponse.builder()
                .id(jobSkill.getId())
                .name(jobSkill.getName())
                .slug(jobSkill.getSlug())
                .isActive(jobSkill.getIsActive())
                .skillCategory(jobSkill.getSkillCategory())
                .createdAt(jobSkill.getCreatedAt())
                .build();
    }
}
