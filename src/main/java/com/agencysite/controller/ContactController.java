package com.agencysite.controller;

import com.agencysite.dto.ContactMessageDTO;
import com.agencysite.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactMessageDTO> submitMessage(@Valid @RequestBody ContactMessageDTO request) {
        ContactMessageDTO saved = contactService.submitMessage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ContactMessageDTO>> getAllMessages() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }
}