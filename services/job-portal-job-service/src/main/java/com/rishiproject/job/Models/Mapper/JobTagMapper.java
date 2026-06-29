package com.rishiproject.job.Models.Mapper;

import com.rishiproject.job.Models.JobTag;
import com.rishiproject.job.dto.Response.JobTagResponse;

public class JobTagMapper {
    public static JobTagResponse mapToDto(JobTag jobTag){
        return  JobTagResponse.builder()
                .id(jobTag.getId())
                .name(jobTag.getName())
                .slug(jobTag.getSlug())
                .isActive(jobTag.getIsActive())
                .createdAt(jobTag.getCreatedAt())
                .build();
    }
}
