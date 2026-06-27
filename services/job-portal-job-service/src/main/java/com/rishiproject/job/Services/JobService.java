package com.rishiproject.job.Services;

import com.rishiproject.job.Models.Embeddable.JobLocation;
import com.rishiproject.job.Models.Embeddable.SalaryRange;
import com.rishiproject.job.Models.Job;
import com.rishiproject.job.Models.Mapper.JobMapper;
import com.rishiproject.job.Models.Payload.JobSearchRequest;
import com.rishiproject.job.Repositories.IJobRepository;
import com.rishiproject.job.Repositories.JobSpecifications;
import com.rishiproject.job.Services.Interfaces.IJobService;
import com.rishiproject.job.domain.Enums.JobStatus;
import com.rishiproject.job.dto.Request.JobRequest;
import com.rishiproject.job.dto.Response.CompanyResponse;
import com.rishiproject.job.dto.Response.JobResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService implements IJobService {
    private final IJobRepository jobRepository;

    @Override
    public JobResponse createJob(Long employerId, JobRequest jobRequest) {
        // todo - fetch company by the employer id
        Long companyId = 1L;

        Job newJob = Job.builder()
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .requirements(jobRequest.getRequirements())
                .responsibilities(jobRequest.getResponsibilities())
                .benefits(jobRequest.getBenefits())
                .companyId(companyId)
                .employerId(employerId)
//                .category(category)
//                .skills(skills)
//                .tags(tags)
                .location(buildLocation(jobRequest))
                .range(buildSalaryRange(jobRequest))
                .type(jobRequest.getJobType())
                .workMode(jobRequest.getWorkMode())
                .experinceLevel(jobRequest.getExperinceLevel())
                .openings(jobRequest.getOpenings() != null ? jobRequest.getOpenings() : 1)
                .applicationDeadline(jobRequest.getApplicationDeadline())
                .expiresAt(jobRequest.getExpiresAt())
                .build();

        return convertToResponse(jobRepository.save(newJob));
    }

    private JobResponse convertToResponse(Job savedJob) {
        // todo : fetch the company response
        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(savedJob.getCompanyId())
                .build();

        return JobMapper.mapToDto(savedJob, companyResponse);
    }

    private SalaryRange buildSalaryRange(JobRequest jobRequest) {
        return SalaryRange.builder()
                .minSalary(jobRequest.getMinSalary())
                .maxSalary(jobRequest.getMaxSalary())
                .currency(jobRequest.getCurrency())
                .build();
    }

    private JobLocation buildLocation(JobRequest jobRequest) {
        return JobLocation.builder()
                .address(jobRequest.getAddress())
                .city(jobRequest.getCity())
                .state(jobRequest.getState())
                .country(jobRequest.getCountry())
                .zipcode(jobRequest.getZipCode())
                .build();
    }

    @Override
    public JobResponse getJobById(Long id) throws Exception {
        Job job = jobRepository.findById(id).orElseThrow(
                () -> new Exception("Job not found")
        );

        return convertToResponse(job);
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest jobSearchRequest) {
        List<Job> jobs = jobRepository.findAll(JobSpecifications.build(jobSearchRequest));
        return jobs.stream().map(
                this::convertToResponse
        ).collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        return jobs.stream().map(
                this::convertToResponse
        ).collect(Collectors.toList());
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest jobRequest) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found")
        );

        assertEmployer(job, employerId);

        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setBenefits(jobRequest.getBenefits());
        job.setRequirements(jobRequest.getRequirements());
        job.setResponsibilities(jobRequest.getResponsibilities());

        // todo - implement this still not implemented

        //  job.setSkills(jobRequest.getSkills());
        //  job.setCategory(jobRequest.getCategory());
        // job.setTags(jobRequest.getTags());

        job.setLocation(buildLocation(jobRequest));
        job.setRange(buildSalaryRange(jobRequest));
        job.setType(jobRequest.getJobType());
        job.setWorkMode(jobRequest.getWorkMode());
        job.setExperinceLevel(jobRequest.getExperinceLevel());
        job.setOpenings(jobRequest.getOpenings() != null ? jobRequest.getOpenings() : job.getOpenings());
        job.setApplicationDeadline(jobRequest.getApplicationDeadline());
        job.setExpiresAt(jobRequest.getExpiresAt());

        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found")
        );

        assertEmployer(job, employerId);
        if(job.getStatus() == JobStatus.CLOSED || job.getStatus() == JobStatus.EXPIRED){
            throw new Exception("Job is already closed or expired");
        }
        job.setStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setIsActive(true);

        return convertToResponse(jobRepository.save(job));
    }

    private void assertEmployer(Job job, Long employerId) throws Exception {
        if(!job.getEmployerId().equals(employerId)){
            throw new Exception("Not the employer who posted this job");
        }
    }

    @Override
    public JobResponse closeJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found")
        );

        assertEmployer(job, employerId);
        job.setStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        job.setIsActive(false);

        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public void deleteJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new Exception("Job not found")
        );

        assertEmployer(job, employerId);

        if(job.getStatus() == JobStatus.EXPIRED){
            throw new Exception("Job is already expired");
        }
        job.setStatus(JobStatus.EXPIRED);
        job.setClosedAt(LocalDateTime.now());
        job.setIsActive(false);

        jobRepository.delete(job);
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(
                this::convertToResponse
        ).collect(Collectors.toList());
    }
}
