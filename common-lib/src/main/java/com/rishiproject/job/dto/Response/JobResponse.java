package com.rishiproject.job.dto.Response;

import com.rishiproject.job.domain.Enums.ExperinceLevel;
import com.rishiproject.job.domain.Enums.JobStatus;
import com.rishiproject.job.domain.Enums.JobType;
import com.rishiproject.job.domain.Enums.WorkMode;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String responsibilities;
    private String benefits;

    private CompanyResponse company;
    private Long employerId;

    private JobCategoryResponse category;
    private Set<JobSkillResponse> skills;
    private Set<JobTagResponse> tags;

    // Location
    private String address;
    private String city;
    private String state;
    private String country;
    private Long zipCode;

    // Salary
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String currency;

    //  private SalaryPeriod salaryPeriod;
    private Boolean salaryNegotiable;
    private Boolean salaryDisclosed;

    // Classification
    private JobType jobType;
    private WorkMode workMode;
    private ExperinceLevel experinceLevel;
    private JobStatus status;

    // Posting Details
    private Integer openings;
    private LocalDate applicationDeadline;
    private LocalDate expiresAt;
    private Boolean isActive;

    // Timestamps'
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime closedAt;
}
