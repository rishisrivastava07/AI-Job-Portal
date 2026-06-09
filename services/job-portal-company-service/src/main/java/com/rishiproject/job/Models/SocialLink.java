package com.rishiproject.job.Models;

import com.rishiproject.job.domain.Enums.SocialPlatform;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLink {
    private SocialPlatform platform;
    private String url;
}

