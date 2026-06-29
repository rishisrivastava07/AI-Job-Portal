package com.rishiproject.job.Services.Interfaces;

import com.rishiproject.job.Models.Payload.JobSearchRequest;
import com.rishiproject.job.dto.Request.JobRequest;
import com.rishiproject.job.dto.Response.JobResponse;

import java.util.List;

public interface IJobService {
    JobResponse createJob(Long employerId, JobRequest jobRequest) throws Exception;
    JobResponse getJobById(Long id) throws Exception;
    List<JobResponse> getJobs(JobSearchRequest jobSearchRequest);
    List<JobResponse> getJobsByCompany(Long companyId);

    JobResponse updateJob(Long jobId, Long employerId, JobRequest jobRequest) throws Exception;
    JobResponse publishJob(Long jobId, Long employerId) throws Exception;
    JobResponse closeJob(Long jobId, Long employerId) throws Exception;
    void deleteJob(Long jobId, Long employerId) throws Exception;

    // void incrementApplicationCount(Long jobId);
    List<JobResponse> getAllJobsAdmin();
}
