package com.rishiproject.job.Controllers;

import com.rishiproject.job.domain.Enums.UserRole;
import com.rishiproject.job.dto.Response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController()
    {
        return new ApiResponse(
                "Home Controller for job posting and search - Job Service - "+ UserRole.ROLE_EMPLOYER,
                true
        );
    }
}
