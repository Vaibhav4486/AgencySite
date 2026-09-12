package com.agencysite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agencysite.dto.ContactMessageDTO;
import com.agencysite.entity.ContactMessage;
import com.agencysite.entity.ServiceOffering;
import com.agencysite.exception.ResourceNotFound;
import com.agencysite.repository.ContactMessageRepository;
import com.agencysite.repository.ServiceOfferingRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ContactService 
{
   private final ContactMessageRepository contactMessageRepository;   
   private final ServiceOfferingRepository serviceOfferingRepository;

   public ContactMessageDTO submitMessage(ContactMessageDTO request) {
      if (request.getEmail() == null || request.getEmail().isBlank()
            || request.getMessage() == null || request.getMessage().isBlank()) {
         throw new IllegalArgumentException("Email and message are required");
      }

      ContactMessage contactMessage = new ContactMessage();
      contactMessage.setName(request.getName());
      contactMessage.setEmail(request.getEmail());
      contactMessage.setPhone(request.getPhone());
      contactMessage.setSubject(request.getSubject());
      contactMessage.setMessage(request.getMessage());

      if (request.getInterestedServiceId() != null) {
         ServiceOffering serviceOffering = serviceOfferingRepository.findById(request.getInterestedServiceId())
               .orElseThrow(() -> new ResourceNotFound(
                     "Service offering not found: " + request.getInterestedServiceId()));
         contactMessage.setInterestedService(serviceOffering);
      }

      ContactMessage savedMessage = contactMessageRepository.save(contactMessage);
      ServiceOffering interestedService = savedMessage.getInterestedService();

      return ContactMessageDTO.builder()
            .id(savedMessage.getId())
            .name(savedMessage.getName())
            .email(savedMessage.getEmail())
            .phone(savedMessage.getPhone())
            .subject(savedMessage.getSubject())
            .message(savedMessage.getMessage())
            .createdAt(savedMessage.getCreatedAt())
            .interestedServiceId(interestedService == null ? null : interestedService.getId())
            .build();
   }

         public List<ContactMessageDTO> getAllMessages() {
         return contactMessageRepository.findAll().stream()
            .map(contactMessage -> {
               ServiceOffering interestedService = contactMessage.getInterestedService();
               return ContactMessageDTO.builder()
                  .id(contactMessage.getId())
                  .name(contactMessage.getName())
                  .email(contactMessage.getEmail())
                  .phone(contactMessage.getPhone())
                  .subject(contactMessage.getSubject())
                  .message(contactMessage.getMessage())
                  .createdAt(contactMessage.getCreatedAt())
                  .interestedServiceId(
                     interestedService == null ? null : interestedService.getId())
                  .build();
            })
            .toList();
         }
}
