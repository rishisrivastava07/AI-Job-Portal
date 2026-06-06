package com.rishiproject.job.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String HomeController()
    {
        return "Job Portal System Service powered by AI";
    }
}
