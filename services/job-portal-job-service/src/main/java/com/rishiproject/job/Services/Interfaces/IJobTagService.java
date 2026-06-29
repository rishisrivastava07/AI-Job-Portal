package com.rishiproject.job.Services.Interfaces;

import com.rishiproject.job.Models.JobSkill;
import com.rishiproject.job.Models.JobTag;
import com.rishiproject.job.Models.Payload.JobTagRequest;
import com.rishiproject.job.dto.Response.JobTagResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IJobTagService {
    JobTagResponse createJobTag(JobTagRequest request) throws Exception;
    List<JobTagResponse> getAllTags();
    JobTagResponse getTagById(Long id) throws Exception;
    JobTagResponse updateTag(Long id, JobTagRequest request) throws Exception;
    void deleteTag(Long id) throws Exception;
    JobTag getTagEntitiesById(Long id) throws Exception;
    Set<JobTag> getTagsByIds(Set<Long> ids);
}
