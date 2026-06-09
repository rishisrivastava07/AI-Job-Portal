package com.rishiproject.job.Services;

import com.rishiproject.job.Mapper.CompanyMapper;
import com.rishiproject.job.Models.Company;
import com.rishiproject.job.Repositories.ICompanyRepository;
import com.rishiproject.job.Services.Interfaces.ICompanyService;
import com.rishiproject.job.domain.Enums.CompanyStatus;
import com.rishiproject.job.domain.Enums.CompanyType;
import com.rishiproject.job.domain.Enums.IndustryType;
import com.rishiproject.job.dto.Request.CompanyRequest;
import com.rishiproject.job.dto.Response.CompanyResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.rishiproject.job.Mapper.CompanyMapper.mapSocialLinks;

@Service
@RequiredArgsConstructor
public class CompanyService implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final ICompanyService iCompanyService;

    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest req) throws Exception {
        if(companyRepository.existsById(ownerId)){
            throw new Exception("You already have company registered. Only one company per account is allowed");
        }
        if(companyRepository.existsByName(req.getName())){
            throw new Exception("Company already exists. Please contact to the Admin");
        }
        if(req.getRegistrationNumber() != null && companyRepository.existsByRegistrationNumber(req.getRegistrationNumber())){
            throw new Exception("Company already exists with this Registration Number.");
        }

        String slug = generateUniqueSlug(req.getName());
        Company newCompany = Company.builder()
                .name(req.getName())
                .slug(slug)
                .tagLine(req.getTagLine())
                .description(req.getDescription())
                .logoUrl(req.getLogoUrl())
                .coverImageUrl(req.getCoverImageUrl())
                .websiteUrl(req.getWebsiteUrl())
                .email(req.getEmail())
                .phone(req.getPhone())
                .foundedYear(req.getFoundedYear())
                .companySize(req.getCompanySize())
                .companyType(req.getCompanyType())
                .industryType(req.getIndustryType())
                .registrationNumber(req.getRegistrationNumber())
                .ownerId(ownerId)
                .socialLinksList(mapSocialLinks(req.getSocialLinks()))
                .build();

        Company savedCompany = companyRepository.save(newCompany);
        return CompanyMapper.mapToDto(savedCompany);
    }



    private String generateUniqueSlug(@NotBlank(message = "Company name is required") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]","")
                .trim().replaceAll("[\\s-]","-");

        if(!companyRepository.existsBySlug(base)){
            return base;
        }

        int counter = 1;
        while(companyRepository.existsBySlug(base + "-" + counter)){
            counter++;
        }

        return base + "-" + counter;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {
        Company company = companyRepository.findById(id).orElseThrow(
                ()-> new Exception("Company not found with this ID")
        );

        return CompanyMapper.mapToDto(company);
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest req) throws Exception {
        Company company = getCompanyEntityById(companyId);
        if(!company.getName().equals(req.getName()) && companyRepository.existsByName(req.getName())){
            throw new Exception("Company with this name is already registered");
        }
        if(req.getRegistrationNumber() != null
                && !company.getRegistrationNumber().equals(req.getRegistrationNumber())
                && companyRepository.existsByRegistrationNumber(req.getRegistrationNumber())){
            throw new Exception("Company Registration number cannot be null or it's already there in DB");
        }
        if (!company.getOwnerId().equals(ownerId)) {
            throw new Exception("You are not authorized to update this company");
        }

        CompanyMapper.setCompanyDetails(req, company);
        Company savedNewCompany = companyRepository.save(company);
        return CompanyMapper.mapToDto(savedNewCompany);
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) throws Exception {
        Company company = companyRepository.findByOwnerId(ownerId).orElseThrow(
                ()-> new Exception("Company not found with this OwnerId")
        );

        return CompanyMapper.mapToDto(company);
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        List<Company> companies = companyRepository.findByFilters(companyType, industryType, companyStatus);

        if (companies == null || companies.isEmpty()) {
            return Collections.emptyList();
        }

        return companies.stream()
                .map(CompanyMapper::mapToDto)
                .toList();
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setCompanyStatus(CompanyStatus.ACTIVE);
        company.setVerified(true);
        company.setVerifiedAt(LocalDateTime.now());

        return CompanyMapper.mapToDto(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Long companyId, Long ownerId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        assertOwner(company, ownerId);
        companyRepository.delete(company);
    }

    private void assertOwner(Company company, Long ownerId) throws Exception {
        if(!company.getOwnerId().equals(ownerId)){
            throw new Exception("You are not the owner of the company");
        }
    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setCompanyStatus(CompanyStatus.SUSPENDED);
        company.setVerified(false);
        company.setVerifiedAt(null);

        return CompanyMapper.mapToDto(companyRepository.save(company));
    }

    @Override
    public Company getCompanyEntityById(Long id) throws Exception {
        return companyRepository.findById(id).orElseThrow(
                ()-> new Exception("Company not found with this ID")
        );
    }
}
