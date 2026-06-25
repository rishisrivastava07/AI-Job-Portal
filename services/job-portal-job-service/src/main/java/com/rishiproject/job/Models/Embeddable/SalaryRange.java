package com.rishiproject.job.Models.Embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SalaryRange {
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String currency;
}
