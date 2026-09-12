package com.agencysite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agencysite.dto.CaseStudyDTO;
import com.agencysite.entity.CaseStudy;
import com.agencysite.entity.ServiceOffering;
import com.agencysite.exception.ResourceNotFound;
import com.agencysite.repository.CaseStudyRepository;
import com.agencysite.repository.ServiceOfferingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service 
public class CaseStudyService 
{
    private final CaseStudyRepository caseStudyRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;

    public CaseStudyDTO createCaseStudy(CaseStudyDTO request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (request.getServiceOfferingId() == null) {
            throw new IllegalArgumentException("serviceOfferingId is required");
        }

        ServiceOffering serviceOffering = serviceOfferingRepository.findById(request.getServiceOfferingId())
                .orElseThrow(() -> new ResourceNotFound(
                        "Service offering not found: " + request.getServiceOfferingId()));

        CaseStudy caseStudy = new CaseStudy();
        caseStudy.setTitle(request.getTitle());
        caseStudy.setDescription(request.getDescription());
        caseStudy.setImageUrl(request.getImageUrl());
        caseStudy.setResultMetric(request.getResultMetric());
        caseStudy.setDisplayOrder(request.getDisplayOrder());
        caseStudy.setServiceOffering(serviceOffering);

        CaseStudy savedCaseStudy = caseStudyRepository.save(caseStudy);
        return CaseStudyDTO.builder()
                .id(savedCaseStudy.getId())
                .title(savedCaseStudy.getTitle())
                .description(savedCaseStudy.getDescription())
                .imageUrl(savedCaseStudy.getImageUrl())
                .resultMetric(savedCaseStudy.getResultMetric())
                .displayOrder(savedCaseStudy.getDisplayOrder())
                .serviceOfferingId(savedCaseStudy.getServiceOffering().getId())
                .createdAt(savedCaseStudy.getCreatedAt())
                .build();
    }

    public List<CaseStudyDTO> getCaseStudiesByService(Long serviceOfferingId) {
        if (!serviceOfferingRepository.existsById(serviceOfferingId)) {
            throw new ResourceNotFound("Service offering not found: " + serviceOfferingId);
        }

        return caseStudyRepository.findByServiceOfferingIdOrderByDisplayOrderAsc(serviceOfferingId).stream()
                .map(caseStudy -> CaseStudyDTO.builder()
                        .id(caseStudy.getId())
                        .title(caseStudy.getTitle())
                        .description(caseStudy.getDescription())
                        .imageUrl(caseStudy.getImageUrl())
                        .resultMetric(caseStudy.getResultMetric())
                        .displayOrder(caseStudy.getDisplayOrder())
                        .serviceOfferingId(caseStudy.getServiceOffering().getId())
                        .createdAt(caseStudy.getCreatedAt())
                        .build())
                .toList();
    }
    public void deleteCaseStudy(Long id) {
        CaseStudy cs = caseStudyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Case study not found: " + id));
        caseStudyRepository.delete(cs);
    }
    
}
