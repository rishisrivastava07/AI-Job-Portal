package com.rishiproject.job.Controllers;

import com.rishiproject.job.Services.Interfaces.ICompanyService;
import com.rishiproject.job.domain.Enums.CompanyStatus;
import com.rishiproject.job.domain.Enums.CompanyType;
import com.rishiproject.job.domain.Enums.IndustryType;
import com.rishiproject.job.dto.Request.CompanyRequest;
import com.rishiproject.job.dto.Response.ApiResponse;
import com.rishiproject.job.dto.Response.CompanyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final ICompanyService companyService;

    @PostMapping("/create-company")
    public ResponseEntity<CompanyResponse> createCompany(@RequestHeader ("X-User-Id") Long ownerId, @RequestBody @Valid CompanyRequest req) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companyService.createCompany(ownerId, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyService.getCompanyById(id));
    }

    @GetMapping("/my")
    public ResponseEntity<CompanyResponse> getMyCompany(@RequestHeader ("X-User-Id") Long ownerId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyService.getMyCompany(ownerId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies(
            @RequestParam(required = false) CompanyType companyType,
            @RequestParam(required = false) IndustryType industryType,
            @RequestParam(required = false) CompanyStatus companyStatus) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyService.getAllCompanies(companyType, industryType, companyStatus));
    }

    @PutMapping("/{companyId}/update")
    public ResponseEntity<CompanyResponse> updateCompany(@RequestHeader ("X-User-Id") Long ownerId, @PathVariable Long companyId, @RequestBody @Valid CompanyRequest req) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyService.updateCompany(companyId, ownerId, req));
    }

    @PatchMapping("/{companyId}/verify")
    public ResponseEntity<CompanyResponse> verifyCompany(@PathVariable Long companyId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyService.verifyCompany(companyId));
    }

    @PatchMapping("/{companyId}/deactivate")
    public ResponseEntity<CompanyResponse> deactivateCompany(@PathVariable Long companyId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyService.deactivateCompany(companyId));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<ApiResponse> deleteCompany(@RequestHeader ("X-User-Id") Long ownerId, @PathVariable Long companyId) throws Exception {
        companyService.deleteCompany(companyId, ownerId);
        return ResponseEntity.ok(new ApiResponse("Company deleted successfully", true));
    }
}
