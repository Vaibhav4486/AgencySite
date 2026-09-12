package com.agencysite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOfferingDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String shortDescription;

    @NotBlank(message = "Description is required")
    private String description;

    private String imageUrl;
    private String iconName;
    private Integer displayOrder;
    private LocalDateTime createdAt;
}
