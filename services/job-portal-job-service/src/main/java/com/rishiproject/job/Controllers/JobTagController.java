package com.rishiproject.job.Controllers;

import com.rishiproject.job.Models.Payload.JobTagRequest;
import com.rishiproject.job.Services.JobTagService;
import com.rishiproject.job.dto.Response.ApiResponse;
import com.rishiproject.job.dto.Response.JobTagResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-tags")
public class JobTagController {
    private final JobTagService jobTagService;

    @PostMapping
    public ResponseEntity<JobTagResponse> createCategory(@RequestBody @Valid JobTagRequest jobTagRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobTagService.createJobTag(jobTagRequest));
    }

    @GetMapping
    public ResponseEntity<List<JobTagResponse>> getAllCategories() {
        return ResponseEntity.ok(jobTagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTagResponse> getCategoryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobTagService.getTagById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTagResponse> updateCategory(@PathVariable Long id, @RequestBody @Valid JobTagRequest jobTagRequest) throws Exception {
        return ResponseEntity.ok(jobTagService.updateTag(id, jobTagRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) throws Exception {
        jobTagService.deleteTag(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse("Job Tag is deleted successfully", true));
    }
}
