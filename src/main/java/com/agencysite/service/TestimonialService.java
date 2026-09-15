package com.agencysite.service;

import com.agencysite.dto.TestimonialDTO;
import com.agencysite.entity.Testimonial;
import com.agencysite.exception.ResourceNotFound;
import com.agencysite.repository.TestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;

    public TestimonialDTO createTestimonial(TestimonialDTO request) {
        if (request.getClientName() == null || request.getClientName().isBlank()) {
            throw new IllegalArgumentException("Client name is required");
        }
        if (request.getQuote() == null || request.getQuote().isBlank()) {
            throw new IllegalArgumentException("Quote is required");
        }

        Testimonial testimonial = Testimonial.builder()
                .clientName(request.getClientName())
                .companyName(request.getCompanyName())
                .quote(request.getQuote())
                .imageUrl(request.getImageUrl())
                .displayOrder(request.getDisplayOrder())
                .build();

        Testimonial saved = testimonialRepository.save(testimonial);
        return toDTO(saved);
    }

    public List<TestimonialDTO> getAllTestimonials() {
        return testimonialRepository.findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void deleteTestimonial(Long id) {
        if (!testimonialRepository.existsById(id)) {
            throw new ResourceNotFound("Testimonial not found with id: " + id);
        }
        testimonialRepository.deleteById(id);
    }

    private TestimonialDTO toDTO(Testimonial testimonial) {
        return TestimonialDTO.builder()
                .id(testimonial.getId())
                .clientName(testimonial.getClientName())
                .companyName(testimonial.getCompanyName())
                .quote(testimonial.getQuote())
                .imageUrl(testimonial.getImageUrl())
                .displayOrder(testimonial.getDisplayOrder())
                .createdAt(testimonial.getCreatedAt())
                .build();
    }
}