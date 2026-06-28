package com.rishiproject.job.Services.Interfaces;

import com.rishiproject.job.Models.JobSkill;
import com.rishiproject.job.Models.Payload.JobSkillRequest;
import com.rishiproject.job.dto.Response.JobSkillResponse;

import java.util.List;
import java.util.Set;

public interface IJobSkillService {
    JobSkillResponse createJobCSkill(JobSkillRequest request) throws Exception;
    List<JobSkillResponse> getAllSkills();
    JobSkillResponse getSkillById(Long id) throws Exception;
    JobSkillResponse updateSkill(Long id, JobSkillRequest request) throws Exception;
    void deleteSkill(Long id) throws Exception;
    Set<JobSkill> getSkillsByIds(Set<Long> ids) throws Exception;
}
