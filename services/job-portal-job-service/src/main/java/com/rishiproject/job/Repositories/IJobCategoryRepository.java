package com.rishiproject.job.Repositories;

import com.rishiproject.job.Models.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IJobCategoryRepository extends JpaRepository<JobCategory, Long> {
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    List<JobCategory> findByIsActiveTrue();
}
