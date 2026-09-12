package com.agencysite.controller;

import com.agencysite.dto.InquiryStatsDTO;
import com.agencysite.dto.ServiceOfferingDTO;
import com.agencysite.service.ServiceOfferingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOfferingDTO> createService(@Valid @RequestBody ServiceOfferingDTO request) {
        ServiceOfferingDTO created = serviceOfferingService.createService(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ServiceOfferingDTO>> getAllServices() {
        return ResponseEntity.ok(serviceOfferingService.getAllServices());
    }

    @GetMapping("/inquiry-stats")
    public ResponseEntity<List<InquiryStatsDTO>> getInquiryStats() {
        return ResponseEntity.ok(serviceOfferingService.getInquiryStats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOfferingDTO> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceOfferingService.getServiceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOfferingDTO> updateService(@PathVariable Long id, @Valid @RequestBody ServiceOfferingDTO request) {
        return ResponseEntity.ok(serviceOfferingService.updateService(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceOfferingService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}