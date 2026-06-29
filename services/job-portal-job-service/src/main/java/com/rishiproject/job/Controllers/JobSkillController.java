package com.rishiproject.job.Controllers;

import com.rishiproject.job.Models.Payload.JobSkillRequest;
import com.rishiproject.job.Services.JobSkillService;
import com.rishiproject.job.dto.Response.ApiResponse;
import com.rishiproject.job.dto.Response.JobSkillResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-skills")
public class JobSkillController {
    private final JobSkillService jobSkillService;

    @PostMapping
    public ResponseEntity<JobSkillResponse> createCategory(@RequestBody @Valid JobSkillRequest jobSkillRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobSkillService.createJobSkill(jobSkillRequest));
    }

    @GetMapping
    public ResponseEntity<List<JobSkillResponse>> getAllCategories() {
        return ResponseEntity.ok(jobSkillService.getAllSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse> getCategoryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobSkillService.getSkillById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSkillResponse> updateCategory(@PathVariable Long id, @RequestBody @Valid JobSkillRequest jobSkillRequest) throws Exception {
        return ResponseEntity.ok(jobSkillService.updateSkill(id, jobSkillRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) throws Exception {
        jobSkillService.deleteSkill(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse("Job Skill is deleted successfully", true));
    }
}
