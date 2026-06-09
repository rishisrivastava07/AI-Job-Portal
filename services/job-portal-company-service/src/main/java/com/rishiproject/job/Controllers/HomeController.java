package com.rishiproject.job.Controllers;

import com.rishiproject.job.domain.Enums.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home(){
        return "Home Controller for - Company Service - " + UserRole.ROLE_EMPLOYER;
    }
}
