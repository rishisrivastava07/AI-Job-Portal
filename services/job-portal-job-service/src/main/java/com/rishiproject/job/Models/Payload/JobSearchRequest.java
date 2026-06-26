package com.rishiproject.job.Models.Payload;

import com.rishiproject.job.domain.Enums.ExperinceLevel;
import com.rishiproject.job.domain.Enums.JobStatus;
import com.rishiproject.job.domain.Enums.JobType;
import com.rishiproject.job.domain.Enums.WorkMode;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSearchRequest {
    private String keyword;
    private Long categoryId;
    private List<Long> skillIds;
    private List<Long> tagIds;

    private Long companyId;

    private String location;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    private JobType jobType;
    private WorkMode workMode;
    private ExperinceLevel experinceLevel;

    private JobStatus status;
    private Integer minOpenings;
    private Integer maxOpenings;
}
