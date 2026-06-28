package com.rishiproject.job.Services.Interfaces;

import com.rishiproject.job.Models.JobCategory;
import com.rishiproject.job.Models.Payload.JobCategoryRequest;
import com.rishiproject.job.dto.Response.JobCategoryResponse;

import java.util.List;

public interface IJobCategoryService {
    JobCategoryResponse createJobCategory(JobCategoryRequest request) throws Exception;
    List<JobCategoryResponse> getAllCategories();
    JobCategoryResponse getCategoryById(Long id) throws Exception;
    JobCategoryResponse updateCategory(Long id, JobCategoryRequest request) throws Exception;
    void deleteCategory(Long id) throws Exception;
    JobCategory getCategoryEntityById(Long id) throws Exception;

}
