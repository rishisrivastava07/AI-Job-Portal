package com.rishiproject.job.Repositories;

import com.rishiproject.job.Models.Job;
import com.rishiproject.job.Models.Payload.JobSearchRequest;
import com.rishiproject.job.domain.Enums.JobStatus;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class JobSpecifications {
    private JobSpecifications() {}
    public static Specification<Job> build(JobSearchRequest jobSearchRequest){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isTrue(root.get("isActive")));

            JobStatus status = jobSearchRequest.getStatus() != null ? jobSearchRequest.getStatus() : JobStatus.OPEN;
            predicates.add(cb.equal(root.get("status"), status));

            if(jobSearchRequest.getJobType() != null){
                predicates.add(cb.equal(root.get("jobType"), jobSearchRequest.getJobType()));
            }

            if(jobSearchRequest.getWorkMode() != null){
                predicates.add(cb.equal(root.get("workMode"), jobSearchRequest.getWorkMode()));
            }

            if(jobSearchRequest.getExperinceLevel() != null){
                predicates.add(cb.equal(root.get("experinceLevel"), jobSearchRequest.getExperinceLevel()));
            }

            if(jobSearchRequest.getCompanyId() != null){
                predicates.add(cb.equal(root.get("companyId"), jobSearchRequest.getCompanyId()));
            }

            if(jobSearchRequest.getCategoryId() != null){
                predicates.add(cb.equal(root.get("category").get("id"), jobSearchRequest.getCategoryId()));
            }

            if(jobSearchRequest.getLocation() != null && !jobSearchRequest.getLocation().isBlank()){
                String pattern = "%" + jobSearchRequest.getLocation().toLowerCase() + "%";
                Path<String> city = root.get("location").get("city");
                Path<String> state = root.get("location").get("state");
                Path<String> country = root.get("location").get("country");
                Path<String> zipCode = root.get("location").get("zipCode");

                predicates.add(cb.or(
                        cb.like(cb.lower(city), pattern),
                        cb.like(cb.lower(state), pattern),
                        cb.like(cb.lower(country), pattern),
                        cb.like(cb.lower(zipCode), pattern)
                ));
            }

            if(jobSearchRequest.getMinSalary() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("salaryRange").get("minSalary"), jobSearchRequest.getMinSalary()));
            }

            if(jobSearchRequest.getMaxSalary() != null){
                predicates.add(cb.lessThanOrEqualTo(root.get("salaryRange").get("maxSalary"), jobSearchRequest.getMaxSalary()));
            }

            if(jobSearchRequest.getMinOpenings() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("openings"), jobSearchRequest.getMinOpenings()));
            }

            if(jobSearchRequest.getMaxOpenings() != null){
                predicates.add(cb.lessThanOrEqualTo(root.get("openings"), jobSearchRequest.getMaxOpenings()));
            }

            // todo - filtering for tags and skills
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
