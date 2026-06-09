package com.rishiproject.job.dto.Response;

import com.rishiproject.job.domain.Enums.CompanySize;
import com.rishiproject.job.domain.Enums.CompanyStatus;
import com.rishiproject.job.domain.Enums.CompanyType;
import com.rishiproject.job.domain.Enums.IndustryType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CompanyResponse {
    private Long id;
    private String name;
    private String slug;
    private String tagLine;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String websiteUrl;
    private Integer foundedYear;
    private String email;
    private Long phone;
    private CompanySize companySize;
    private CompanyType companyType;
    private IndustryType industryType;
    private CompanyStatus companyStatus;
    private Boolean isActive;

    private Long ownerId;

    private List<SocialLinkResponse> socialLinksList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime verifiedAt;
}
