package com.rishiproject.job.dto.Request;

import com.rishiproject.job.domain.Enums.ExperinceLevel;
import com.rishiproject.job.domain.Enums.JobType;
import com.rishiproject.job.domain.Enums.WorkMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    @NotBlank(message = "Job Title is required")
    private String title;
    @NotBlank(message = "Job Description is required")
    private String description;

    private String requirements;
    private String responsibilities;
    private String benefits;

    @NotBlank(message = "Category is required")
    private Long categoryId;

    /* Ids from the job_skills table */
//    private Set<JobSkillResponse> skills;

    /* Ids from the job_tags table */
//    private Set<JobTagResponse> tags;

    // Location
    private String address;
    private String city;
    private String state;
    private String country;
    private Long zipCode;

    // Salary
    @DecimalMin(value = "0.0", inclusive = true, message = "Min salary must not be negative")
    private BigDecimal minSalary;

    @DecimalMin(value = "0.0", inclusive = true, message = "Max salary must not be negative")
    private BigDecimal maxSalary;

    private String currency;
//    private SalaryPeriod salaryPeriod;
//    private Boolean salaryNegotiable;
//    private Boolean salaryDisclosed;

    // Classification
    @NotNull(message = "Job Type is required")
    private JobType jobType;
    @NotNull(message = "Work Mode is required")
    private WorkMode workMode;
    @NotNull(message = "Experience level is required")
    private ExperinceLevel experinceLevel;

    // Posting Details
    @Min(value = 1, message = "Openings must be at least 1")
    @Builder.Default
    private Integer openings = 1;

    private LocalDate applicationDeadline;
    private LocalDate expiresAt;
}
