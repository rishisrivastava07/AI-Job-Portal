package com.rishiproject.job.Services;

import com.rishiproject.job.Models.JobSkill;
import com.rishiproject.job.Models.Mapper.JobSkillMapper;
import com.rishiproject.job.Models.Payload.JobSkillRequest;
import com.rishiproject.job.Repositories.IJobSkillRepository;
import com.rishiproject.job.Services.Interfaces.IJobSkillService;
import com.rishiproject.job.dto.Response.JobSkillResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSkillService implements IJobSkillService {
    private final IJobSkillRepository jobSkillRepository;

    @Override
    public JobSkillResponse createJobCSkill(JobSkillRequest request) throws Exception {
        if(jobSkillRepository.existsByName(request.getName())){
            throw new Exception("Skill name already exists");
        }

        String slug = generateUniqueSlug(request.getName());
        JobSkill jobSkill = JobSkill.builder()
                .name(request.getName())
                .slug(slug)
                .skillCategory(request.getSkillCategory())
                .build();

        JobSkill savedJobSkill = jobSkillRepository.save(jobSkill);
        return JobSkillMapper.mapToDto(savedJobSkill);
    }

    private String generateUniqueSlug(@NotBlank(message = "Company name is required") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]","")
                .trim().replaceAll("[\\s-]","-");

        if(!jobSkillRepository.existsBySlug(base)){
            return base;
        }

        int counter = 1;
        while(jobSkillRepository.existsBySlug(base + "-" + counter)){
            counter++;
        }

        return base + "-" + counter;
    }

    @Override
    public List<JobSkillResponse> getAllSkills() {
        List<JobSkill> allJobSkills = jobSkillRepository.findByIsActiveTrue();
        return allJobSkills.stream()
                .map(JobSkillMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobSkillResponse getSkillById(Long id) throws Exception {
        JobSkill jobSkill = jobSkillRepository.findById(id).orElseThrow(
                ()-> new Exception("Skill is not present with this ID")
        );
        return JobSkillMapper.mapToDto(jobSkill);
    }

    @Override
    public JobSkillResponse updateSkill(Long id, JobSkillRequest request) throws Exception {
        JobSkill jobSkill = jobSkillRepository.findById(id).orElseThrow(
                ()-> new Exception("Skill is not present with this ID")
        );

        if(jobSkillRepository.existsByName(request.getName()) && !jobSkill.getName().equals(request.getName())){
            throw new Exception("Skill name already exists, choose different name");
        }

        jobSkill.setName(request.getName());
        jobSkill.setSkillCategory(request.getSkillCategory());

        JobSkill updatedJobSkill = jobSkillRepository.save(jobSkill);
        return JobSkillMapper.mapToDto(updatedJobSkill);
    }

    @Override
    public void deleteSkill(Long id) throws Exception {
        JobSkill jobSkill = jobSkillRepository.findById(id).orElseThrow(
                ()-> new Exception("Skill is not present with this ID")
        );
        jobSkill.setIsActive(false); // soft deleting it
        jobSkillRepository.save(jobSkill);
    }

    @Override
    public Set<JobSkill> getSkillsByIds(Set<Long> ids) {
        return new HashSet<>(jobSkillRepository.findAllById(ids));
    }
}
