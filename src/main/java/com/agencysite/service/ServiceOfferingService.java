package com.agencysite.service;

import com.agencysite.dto.InquiryStatsDTO;
import com.agencysite.dto.ServiceOfferingDTO;
import com.agencysite.entity.ContactMessage;
import com.agencysite.entity.ServiceOffering;
import com.agencysite.exception.ResourceNotFound;
import com.agencysite.repository.ContactMessageRepository;
import com.agencysite.repository.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;
    private final ContactMessageRepository contactMessageRepository;

    public ServiceOfferingDTO createService(ServiceOfferingDTO request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        ServiceOffering service = ServiceOffering.builder()
                .title(request.getTitle())
                .shortDescription(request.getShortDescription())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .iconName(request.getIconName())
                .displayOrder(request.getDisplayOrder())
                .build();

        ServiceOffering saved = serviceOfferingRepository.save(service);
        return toDTO(saved);
    }

    public List<ServiceOfferingDTO> getAllServices() {
        return serviceOfferingRepository.findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ServiceOfferingDTO getServiceById(Long id) {
        ServiceOffering service = serviceOfferingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Service not found with id: " + id));
        return toDTO(service);
    }

    public ServiceOfferingDTO updateService(Long id, ServiceOfferingDTO request) {
        ServiceOffering service = serviceOfferingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Service not found with id: " + id));

        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        service.setTitle(request.getTitle());
        service.setShortDescription(request.getShortDescription());
        service.setDescription(request.getDescription());
        service.setImageUrl(request.getImageUrl());
        service.setIconName(request.getIconName());
        service.setDisplayOrder(request.getDisplayOrder());

        ServiceOffering updated = serviceOfferingRepository.save(service);
        return toDTO(updated);
    }

   public void deleteService(Long id) {
    ServiceOffering service = serviceOfferingRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Service offering not found: " + id));

    List<ContactMessage> relatedMessages = contactMessageRepository.findByInterestedServiceId(id);
    relatedMessages.forEach(msg -> msg.setInterestedService(null));
    contactMessageRepository.saveAll(relatedMessages);

    serviceOfferingRepository.deleteById(id);
}
    public List<InquiryStatsDTO> getInquiryStats() {
        List<Object[]> rows = contactMessageRepository.countMessagesGroupedByService();
        return rows.stream()
                .map(row -> InquiryStatsDTO.builder()
                        .serviceId((Long) row[0])
                        .serviceTitle((String) row[1])
                        .inquiryCount((Long) row[2])
                        .build())
                .toList();
    }

    private ServiceOfferingDTO toDTO(ServiceOffering service) {
        return ServiceOfferingDTO.builder()
                .id(service.getId())
                .title(service.getTitle())
                .shortDescription(service.getShortDescription())
                .description(service.getDescription())
                .imageUrl(service.getImageUrl())
                .iconName(service.getIconName())
                .displayOrder(service.getDisplayOrder())
                .createdAt(service.getCreatedAt())
                .build();
    }
}