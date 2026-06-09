package com.rishiproject.job.Services.Interfaces;

import com.rishiproject.job.Models.Company;
import com.rishiproject.job.domain.Enums.CompanyStatus;
import com.rishiproject.job.domain.Enums.CompanyType;
import com.rishiproject.job.domain.Enums.IndustryType;
import com.rishiproject.job.dto.Request.CompanyRequest;
import com.rishiproject.job.dto.Response.CompanyResponse;

import java.util.List;

public interface ICompanyService {

    CompanyResponse createCompany(Long ownerId, CompanyRequest req) throws Exception;
    CompanyResponse getCompanyById(Long id) throws Exception;
    CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest req) throws Exception;
    CompanyResponse getMyCompany(Long ownerId) throws Exception;
    List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus);

    CompanyResponse verifyCompany(Long companyId) throws Exception;
    void deleteCompany(Long companyId, Long ownerId) throws Exception;
    CompanyResponse deactivateCompany(Long companyId) throws Exception;

    Company getCompanyEntityById(Long id) throws Exception; // inter service calls
}
