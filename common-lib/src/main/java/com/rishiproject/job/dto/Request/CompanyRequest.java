package com.rishiproject.job.dto.Request;

import com.rishiproject.job.domain.Enums.CompanySize;
import com.rishiproject.job.domain.Enums.CompanyType;
import com.rishiproject.job.domain.Enums.IndustryType;
import com.rishiproject.job.dto.Response.SocialLinkResponse;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    private String name;

    private String tagLine;

    private String description;

    private String logoUrl;
    private String coverImageUrl;

    @Pattern(regexp = "^(https?://).*", message = "Website must have valid URL")
    private String websiteUrl;

    @Email(message = "Email must be valid")
    private String email;

    private Long phone;

    @Min(value = 1800, message = "Founded year seems too old")
    @Max(value = 2100, message = "Founded year seems invalid")
    private Integer foundedYear;

    @NotNull(message = "Company size is required")
    private CompanySize companySize;

    @NotNull(message = "Company type is required")
    private CompanyType companyType;

    @NotNull(message = "Industry type is required")
    private IndustryType industryType;

    private String registrationNumber;   // official company registration - CIN number generated

    private List<SocialLinkResponse> socialLinks;
}
