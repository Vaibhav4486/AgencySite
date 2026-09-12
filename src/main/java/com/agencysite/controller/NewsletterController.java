package com.agencysite.controller;

import com.agencysite.dto.NewsletterSubscriberDTO;
import com.agencysite.service.NewslettService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newsletter")
@RequiredArgsConstructor
public class NewsletterController {

    private final NewslettService newsletterService;

    @PostMapping
    public ResponseEntity<NewsletterSubscriberDTO> subscribe(@Valid @RequestBody NewsletterSubscriberDTO request) {
        NewsletterSubscriberDTO created = newsletterService.subscribe(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<NewsletterSubscriberDTO>> getAllSubscribers() {
        return ResponseEntity.ok(newsletterService.getAllSubscribers());
    }
}