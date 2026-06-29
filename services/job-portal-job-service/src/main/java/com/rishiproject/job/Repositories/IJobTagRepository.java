package com.rishiproject.job.Repositories;

import com.rishiproject.job.Models.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IJobTagRepository extends JpaRepository<JobTag, Long> {
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    List<JobTag> findByIsActiveTrue();
}
