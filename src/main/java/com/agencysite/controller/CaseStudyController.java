package com.agencysite.controller;

import com.agencysite.dto.CaseStudyDTO;
import com.agencysite.service.CaseStudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/case-studies")
@RequiredArgsConstructor
public class CaseStudyController {

    private final CaseStudyService caseStudyService;

    @PostMapping
    public ResponseEntity<CaseStudyDTO> createCaseStudy(@Valid @RequestBody CaseStudyDTO request) {
        CaseStudyDTO created = caseStudyService.createCaseStudy(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/service/{serviceOfferingId}")
    public ResponseEntity<List<CaseStudyDTO>> getCaseStudiesByService(@PathVariable Long serviceOfferingId) {
        return ResponseEntity.ok(caseStudyService.getCaseStudiesByService(serviceOfferingId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaseStudy(@PathVariable Long id) {
        caseStudyService.deleteCaseStudy(id);
        return ResponseEntity.noContent().build();
    }
}