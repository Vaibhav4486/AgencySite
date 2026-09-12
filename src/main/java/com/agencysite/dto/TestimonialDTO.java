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
public class TestimonialDTO {

    private Long id;

    @NotBlank(message = "Client name is required")
    private String clientName;

    private String companyName;

    @NotBlank(message = "Quote is required")
    private String quote;

    private String imageUrl;
    private Integer displayOrder;
    private LocalDateTime createdAt;
}
