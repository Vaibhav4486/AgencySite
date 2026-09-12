package com.agencysite.controller;

import com.agencysite.dto.FaqDTO;
import com.agencysite.service.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @PostMapping
    public ResponseEntity<FaqDTO> createFaq(@Valid @RequestBody FaqDTO request) {
        FaqDTO created = faqService.createFaq(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<FaqDTO>> getAllFaqs() {
        return ResponseEntity.ok(faqService.getAllFaqs());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaq(@PathVariable Long id) {
        faqService.deleteFaq(id);
        return ResponseEntity.noContent().build();
    }
}