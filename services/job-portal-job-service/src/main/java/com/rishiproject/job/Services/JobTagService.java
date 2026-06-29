package com.rishiproject.job.Services;

import com.rishiproject.job.Models.JobSkill;
import com.rishiproject.job.Models.JobTag;
import com.rishiproject.job.Models.Mapper.JobTagMapper;
import com.rishiproject.job.Models.Payload.JobTagRequest;
import com.rishiproject.job.Repositories.IJobTagRepository;
import com.rishiproject.job.Services.Interfaces.IJobTagService;
import com.rishiproject.job.dto.Response.JobTagResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobTagService implements IJobTagService {
    private final IJobTagRepository jobTagRepository;

    @Override
    public JobTagResponse createJobTag(JobTagRequest request) throws Exception {
        if(jobTagRepository.existsByName(request.getName())){
            throw new Exception("Tag name already exists");
        }

        String slug = generateUniqueSlug(request.getName());
        JobTag jobTag = JobTag.builder()
                .name(request.getName())
                .slug(slug)
                .isActive(true)
                .build();

        JobTag savedJobTag = jobTagRepository.save(jobTag);
        return JobTagMapper.mapToDto(savedJobTag);
    }

    private String generateUniqueSlug(@NotBlank(message = "Company name is required") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]","")
                .trim().replaceAll("[\\s-]","-");

        if(!jobTagRepository.existsBySlug(base)){
            return base;
        }

        int counter = 1;
        while(jobTagRepository.existsBySlug(base + "-" + counter)){
            counter++;
        }

        return base + "-" + counter;
    }

    @Override
    public List<JobTagResponse> getAllTags() {
        List<JobTag> allJobTags = jobTagRepository.findByIsActiveTrue();
        return allJobTags.stream()
                .map(JobTagMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobTagResponse getTagById(Long id) throws Exception {
        JobTag jobTag = getTagEntitiesById(id);
        return JobTagMapper.mapToDto(jobTag);
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest request) throws Exception {
        JobTag jobTag = getTagEntitiesById(id);

        if(!jobTag.getName().equals(request.getName()) && jobTagRepository.existsByName(request.getName())){
            throw new Exception("Tag name already exists, choose different name");
        }

        jobTag.setName(request.getName());

        JobTag updatedJobCategory = jobTagRepository.save(jobTag);
        return JobTagMapper.mapToDto(updatedJobCategory);
    }

    @Override
    public void deleteTag(Long id) throws Exception {
        JobTag jobTag = getTagEntitiesById(id);
        jobTag.setIsActive(false); // soft deleting it
        jobTagRepository.save(jobTag);
    }

    @Override
    public JobTag getTagEntitiesById(Long id) throws Exception {
        return jobTagRepository.findById(id).orElseThrow(
                ()-> new Exception("There is no Job Tags with this id")
        );
    }

    @Override
    public Set<JobTag> getTagsByIds(Set<Long> ids) {
        return new HashSet<>(jobTagRepository.findAllById(ids));
    }
}
