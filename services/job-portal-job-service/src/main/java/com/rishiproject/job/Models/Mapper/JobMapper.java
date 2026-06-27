package com.rishiproject.job.Models.Mapper;

import com.rishiproject.job.Models.Embeddable.JobLocation;
import com.rishiproject.job.Models.Embeddable.SalaryRange;
import com.rishiproject.job.Models.Job;
import com.rishiproject.job.dto.Response.CompanyResponse;
import com.rishiproject.job.dto.Response.JobResponse;

public class JobMapper {
    public static JobResponse mapToDto(Job job, CompanyResponse companyResponse){
        JobLocation loc = job.getLocation();
        SalaryRange sal = job.getRange();
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())
                .company(companyResponse)
                .employerId(job.getEmployerId())
                //.category(maptoCategoryResponse(category))
                //.skills(skills)
                //.tags(tags)

                //  Location
                .address(loc != null ? loc.getAddress() : null)
                .city(loc != null ? loc.getCity() : null)
                .state(loc != null ? loc.getState() : null)
                .country(loc != null ? loc.getCountry() : null)
                .zipCode(loc != null ? loc.getZipcode() : null)

                // Salary
                .minSalary(sal != null ? sal.getMinSalary() : null)
                .maxSalary(sal != null ? sal.getMaxSalary() : null)
                .currency(sal != null ? sal.getCurrency() : null)

                // Classification
                .jobType(job.getType())
                .workMode(job.getWorkMode())
                .experinceLevel(job.getExperinceLevel())
                .status(job.getStatus())

                // Postings
                .openings(job.getOpenings())
                .applicationDeadline(job.getApplicationDeadline())
                .expiresAt(job.getExpiresAt())
                .isActive(job.getIsActive())

                // Analytics
                // .viewCount(job.getViewCount())
                // .applicationCount(job.getApplicationCount())

                // Timestamps
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .closedAt(job.getClosedAt())
                .publishedAt(job.getPublishedAt())

                .build();
    }
}