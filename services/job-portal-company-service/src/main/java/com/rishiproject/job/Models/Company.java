package com.rishiproject.job.Models;

import com.rishiproject.job.domain.Enums.CompanySize;
import com.rishiproject.job.domain.Enums.CompanyStatus;
import com.rishiproject.job.domain.Enums.CompanyType;
import com.rishiproject.job.domain.Enums.IndustryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String slug;

    private String tagLine;

    private String description;

    private String logoUrl;

    private String coverImageUrl;

    private String websiteUrl;

    private Integer foundedYear;

    private String email;

    private Long phone;

    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Enumerated(EnumType.STRING)
    private IndustryType industryType;

    private CompanyStatus companyStatus;
    private boolean isVerified = false;

    @Column(unique = true)
    private String registrationNumber;

    @Column(nullable = false)
    private Long ownerId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<SocialLink> socialLinksList = new ArrayList<>();

    private Boolean isActive = true;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime verifiedAt;
}

