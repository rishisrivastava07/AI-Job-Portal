package com.rishiproject.job.Models.Embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobLocation {
    private String address;
    private String city;
    private String state;
    private String country;
    private Long zipcode;
}
