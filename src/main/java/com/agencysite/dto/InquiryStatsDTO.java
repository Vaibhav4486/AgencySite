package com.agencysite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryStatsDTO {

    private Long serviceId;
    private String serviceTitle;
    private Long inquiryCount;
}