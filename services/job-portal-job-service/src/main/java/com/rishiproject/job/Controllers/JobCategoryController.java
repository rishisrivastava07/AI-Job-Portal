package com.rishiproject.job.Controllers;

import com.rishiproject.job.Models.Payload.JobCategoryRequest;
import com.rishiproject.job.Services.JobCategoryService;
import com.rishiproject.job.dto.Request.JobRequest;
import com.rishiproject.job.dto.Response.ApiResponse;
import com.rishiproject.job.dto.Response.JobCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-categories")
public class JobCategoryController {
    private final JobCategoryService jobCategoryService;

    @PostMapping
    public ResponseEntity<JobCategoryResponse> createCategory(@RequestBody @Valid JobCategoryRequest jobCategoryRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobCategoryService.createJobCategory(jobCategoryRequest));
    }

    @GetMapping
    public ResponseEntity<List<JobCategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(jobCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> getCategoryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobCategoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> updateCategory(@PathVariable Long id, @RequestBody @Valid JobCategoryRequest jobCategoryRequest) throws Exception {
        return ResponseEntity.ok(jobCategoryService.updateCategory(id, jobCategoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) throws Exception {
        jobCategoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse("Job Category is deleted successfully", true));
    }
}
