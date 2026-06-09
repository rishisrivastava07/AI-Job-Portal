package com.rishiproject.job.domain.Enums;

public enum CompanyStatus {
    PENDING_VERIFICATION, // just registered, waiting admin review
    ACTIVE,               // verified and operational
    SUSPENDED,            // temporarily blocked by admin
    REJECTED              // not allowed by the admin
}
