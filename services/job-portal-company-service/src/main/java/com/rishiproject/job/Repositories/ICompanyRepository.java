package com.rishiproject.job.Repositories;

import com.rishiproject.job.Models.Company;
import com.rishiproject.job.domain.Enums.CompanyStatus;
import com.rishiproject.job.domain.Enums.CompanyType;
import com.rishiproject.job.domain.Enums.IndustryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByOwnerId(Long ownerId);
    boolean existsCompaniesById(Long ownerId);
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    boolean existsByRegistrationNumber(String registrationNumber);

    @Query(
            "select c from Company c where " +
            "(:companyType is null or c.companyType = :companyType) and " +
            "(:industryType is null or c.industryType = :industryType) and " +
            "(:companyStatus is null or c.companyStatus  = :companyStatus)"
    )
    List<Company> findByFilters(
            @Param("companyType") CompanyType companyType,
            @Param("industryType") IndustryType industryType,
            @Param("companyStatus") CompanyStatus companyStatus);

}
