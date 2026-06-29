package com.rishiproject.job.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobTagResponse {
    private Long id;
    private String name;
    private String slug;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
