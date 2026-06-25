package com.rishiproject.job.Services.Interfaces;

import com.rishiproject.job.Models.Payload.JobSearchRequest;
import com.rishiproject.job.dto.Request.JobRequest;
import com.rishiproject.job.dto.Response.JobResponse;

import java.util.List;

public interface IJobService {
    JobResponse createJob(Long employerId, JobRequest jobRequest);
    JobRequest getJobById(Long id);
    List<JobResponse> getJobs(JobSearchRequest jobSearchRequest);
    List<JobResponse> getJobsByCompany(Long companyId);

    JobResponse updateJob(Long jobId, Long employerId, JobRequest jobRequest);
    JobResponse publishJob(Long jobId, Long employerId);
    JobResponse closeJob(Long jobId, Long employerId);
    JobResponse deleteJob(Long jobId, Long employerId);

//    void incrementApplicationCount(Long jobId);
    List<JobResponse> getAllJobsAdmin();
}
