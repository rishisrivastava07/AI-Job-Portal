package com.rishiproject.job.Models.Mapper;

import com.rishiproject.job.Models.JobCategory;
import com.rishiproject.job.Models.Payload.JobCategoryRequest;
import com.rishiproject.job.dto.Response.JobCategoryResponse;

import java.util.List;
import java.util.stream.Collectors;

public class JobCategoryMapper {

    public static JobCategoryResponse mapToDto(JobCategory jobCategory, boolean includeSubCategories){

        List<JobCategoryResponse> subCategories = null;
        if(includeSubCategories && jobCategory.getSubCategories() != null) {
            subCategories = jobCategory.getSubCategories()
                    .stream().map(sub->mapToDto(sub, false))
                    .collect(Collectors.toList());
        }

        return  JobCategoryResponse.builder()
                .id(jobCategory.getId())
                .name(jobCategory.getName())
                .description(jobCategory.getDescription())
                .slug(jobCategory.getSlug())
                .iconUrl(jobCategory.getIconUrl())
                .isActive(jobCategory.getIsActive())
                .parentId(jobCategory.getParent() != null ? jobCategory.getParent().getId() : null)
                .parentName(jobCategory.getParent() != null ? jobCategory.getParent().getName() : null)
                .subCategories(subCategories)
                .createdAt(jobCategory.getCreatedAt())
                .build();
    }
}
