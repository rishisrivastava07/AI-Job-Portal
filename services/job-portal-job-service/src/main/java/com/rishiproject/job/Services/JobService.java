package com.rishiproject.job.Services;

import com.rishiproject.job.Models.Payload.JobSearchRequest;
import com.rishiproject.job.Services.Interfaces.IJobService;
import com.rishiproject.job.dto.Request.JobRequest;
import com.rishiproject.job.dto.Response.JobResponse;

import java.util.List;

public class JobService implements IJobService {
    @Override
    public JobResponse createJob(Long employerId, JobRequest jobRequest) {
        return null;
    }

    @Override
    public JobRequest getJobById(Long id) {
        return null;
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest jobSearchRequest) {
        return List.of();
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        return List.of();
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest jobRequest) {
        return null;
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employerId) {
        return null;
    }

    @Override
    public JobResponse closeJob(Long jobId, Long employerId) {
        return null;
    }

    @Override
    public JobResponse deleteJob(Long jobId, Long employerId) {
        return null;
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return List.of();
    }
}
