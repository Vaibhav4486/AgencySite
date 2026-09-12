package com.agencysite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaseStudyDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private String imageUrl;
    private String resultMetric;
    private Integer displayOrder;

    @NotNull(message = "serviceOfferingId is required")
    private Long serviceOfferingId;

    private LocalDateTime createdAt;
}