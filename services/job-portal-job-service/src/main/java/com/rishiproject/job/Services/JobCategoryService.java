package com.rishiproject.job.Services;

import com.rishiproject.job.Models.JobCategory;
import com.rishiproject.job.Models.Mapper.JobCategoryMapper;
import com.rishiproject.job.Models.Mapper.JobMapper;
import com.rishiproject.job.Models.Payload.JobCategoryRequest;
import com.rishiproject.job.Repositories.IJobCategoryRepository;
import com.rishiproject.job.Services.Interfaces.IJobCategoryService;
import com.rishiproject.job.dto.Response.JobCategoryResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobCategoryService implements IJobCategoryService {
    private final IJobCategoryRepository jobCategoryRepository;

    @Override
    public JobCategoryResponse createJobCategory(JobCategoryRequest request) throws Exception {
        if(jobCategoryRepository.existsByName(request.getName())){
            throw new Exception("Category name already exists");
        }

        JobCategory parent = null;
        if(request.getParentId() != null){
            parent = getCategoryEntityById(request.getParentId());
        }

        String slug = generateUniqueSlug(request.getName());
        JobCategory jobCategory = JobCategory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .iconUrl(request.getIconUrl())
                .parent(parent)
                .build();

        JobCategory savedJobCategory = jobCategoryRepository.save(jobCategory);
        return JobCategoryMapper.mapToDto(savedJobCategory, true);
    }

    private String generateUniqueSlug(@NotBlank(message = "Company name is required") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]","")
                .trim().replaceAll("[\\s-]","-");

        if(!jobCategoryRepository.existsBySlug(base)){
            return base;
        }

        int counter = 1;
        while(jobCategoryRepository.existsBySlug(base + "-" + counter)){
            counter++;
        }

        return base + "-" + counter;
    }

    @Override
    public List<JobCategoryResponse> getAllCategories() {
        List<JobCategory> allJobCategories = jobCategoryRepository.findByIsActiveTrue();
        return allJobCategories.stream()
                .map(category-> JobCategoryMapper.mapToDto(category, false))
                .collect(Collectors.toList());
    }

    @Override
    public JobCategoryResponse getCategoryById(Long id) throws Exception {
        JobCategory jobCategory = getCategoryEntityById(id);
        return JobCategoryMapper.mapToDto(jobCategory, false);
    }

    @Override
    public JobCategoryResponse updateCategory(Long id, JobCategoryRequest request) throws Exception {
        JobCategory jobCategory = getCategoryEntityById(id);

        if(!jobCategory.getName().equals(request.getName()) && jobCategoryRepository.existsByName(request.getName())){
            throw new Exception("Category name already exists, choose different name");
        }

        JobCategory parent = null;
        if(request.getParentId() != null){
            if(request.getParentId().equals(id)){
                throw new Exception("A category cannot be its own parent");
            }
            parent = getCategoryEntityById(request.getParentId());
        }

        jobCategory.setName(request.getName());
        jobCategory.setDescription(request.getDescription());
        jobCategory.setIconUrl(request.getIconUrl());
        jobCategory.setParent(parent);

        JobCategory updatedJobCategory = jobCategoryRepository.save(jobCategory);
        return JobCategoryMapper.mapToDto(updatedJobCategory, true);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        JobCategory jobCategory = getCategoryEntityById(id);
        jobCategory.setIsActive(false); // soft deleting it
        jobCategoryRepository.save(jobCategory);
    }

    @Override
    public JobCategory getCategoryEntityById(Long id) throws Exception {
        return jobCategoryRepository.findById(id).orElseThrow(
                ()-> new Exception("There is no Job Category with this id")
        );
    }
}
