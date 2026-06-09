package com.rishiproject.job.Mapper;

import com.rishiproject.job.Models.Company;
import com.rishiproject.job.Models.SocialLink;
import com.rishiproject.job.dto.Request.CompanyRequest;
import com.rishiproject.job.dto.Response.CompanyResponse;
import com.rishiproject.job.dto.Response.SocialLinkResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {
    public static List<SocialLink> mapSocialLinks(List<SocialLinkResponse> socialLinksResponse) {
        if(socialLinksResponse == null || socialLinksResponse.isEmpty()){
            return new ArrayList<>();
        }

        return socialLinksResponse.stream()
                .map(e->SocialLink.builder()
                        .platform(e.getPlatform())
                        .url(e.getUrl())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<SocialLinkResponse> mapSocialLinksResponse(List<SocialLink> socialLinks) {
        if(socialLinks == null || socialLinks.isEmpty()){
            return new ArrayList<>();
        }

        return socialLinks.stream()
                .map(e->SocialLinkResponse.builder()
                        .platform(e.getPlatform())
                        .url(e.getUrl())
                        .build())
                .toList();
    }

    public static CompanyResponse mapToDto(Company req){
        CompanyResponse res = new CompanyResponse();
        res.setId(req.getId());
        res.setName(req.getName());
        res.setSlug(req.getSlug());
        res.setTagLine(req.getTagLine());
        res.setDescription(req.getDescription());
        res.setLogoUrl(req.getLogoUrl());
        res.setCoverImageUrl(req.getCoverImageUrl());
        res.setWebsiteUrl(req.getWebsiteUrl());
        res.setFoundedYear(req.getFoundedYear());
        res.setEmail(req.getEmail());
        res.setPhone(req.getPhone());
        res.setCompanySize(req.getCompanySize());
        res.setCompanyType(req.getCompanyType());
        res.setIndustryType(req.getIndustryType());
        res.setCompanyStatus(req.getCompanyStatus());
        res.setIsActive(req.getIsActive());

        res.setOwnerId(req.getOwnerId());

        res.setSocialLinksList(mapSocialLinksResponse(req.getSocialLinksList()));
        res.setCreatedAt(req.getCreatedAt());
        res.setUpdatedAt(req.getUpdatedAt());

        return res;
    }

    public static void setCompanyDetails(CompanyRequest req, Company company){
        company.setName(req.getName());
        company.setTagLine(req.getTagLine());
        company.setDescription(req.getDescription());
        company.setLogoUrl(req.getLogoUrl());
        company.setCoverImageUrl(req.getCoverImageUrl());
        company.setWebsiteUrl(req.getWebsiteUrl());
        company.setFoundedYear(req.getFoundedYear());
        company.setEmail(req.getEmail());
        company.setPhone(req.getPhone());
        company.setCompanySize(req.getCompanySize());
        company.setCompanyType(req.getCompanyType());
        company.setIndustryType(req.getIndustryType());
        company.setRegistrationNumber(req.getRegistrationNumber());
        company.setSocialLinksList(CompanyMapper.mapSocialLinks(req.getSocialLinks()));
    }
}
