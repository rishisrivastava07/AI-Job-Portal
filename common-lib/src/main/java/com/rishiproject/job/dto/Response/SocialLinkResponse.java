package com.rishiproject.job.dto.Response;

import com.rishiproject.job.domain.Enums.SocialPlatform;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SocialLinkResponse {
    private SocialPlatform platform;
    private String url;
}
