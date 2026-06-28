package com.rishiproject.job.Repositories;

import com.rishiproject.job.Models.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IJobSkillRepository extends JpaRepository<JobSkill, Long> {
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    List<JobSkill> findByIsActiveTrue();
}
