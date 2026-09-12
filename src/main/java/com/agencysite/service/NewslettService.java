package com.agencysite.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.agencysite.dto.NewsletterSubscriberDTO;
import com.agencysite.entity.NewsletterSubscriber;
import com.agencysite.repository.NewsletterSubscriberRepository;

import lombok.RequiredArgsConstructor;
@Service 
@RequiredArgsConstructor 
public class NewslettService 
{
    private final NewsletterSubscriberRepository nRepository;
    public NewsletterSubscriberDTO subscribe(NewsletterSubscriberDTO request)
    {
        if(request.getEmail() == null || request.getEmail().isEmpty())
        {
            throw new IllegalArgumentException("Email is required");
        }
        if(nRepository.existsByEmail(request.getEmail()))
        {
            throw new IllegalArgumentException("Email is already subscribed");
        }
        NewsletterSubscriber ns=NewsletterSubscriber.builder()
                .email(request.getEmail())
                .build();
        NewsletterSubscriber saved=nRepository.save(ns);
        return NewsletterSubscriberDTO.builder()
                .id(saved.getId())
                .email(saved.getEmail())
                .subscribedAt(saved.getSubscribedAt())
                .build();
    }
    public List<NewsletterSubscriberDTO> getAllSubscribers()
    {
        List<NewsletterSubscriber> subscribers=nRepository.findAll();
        return subscribers.stream()
                .map(subscriber -> NewsletterSubscriberDTO.builder()
                        .id(subscriber.getId())
                        .email(subscriber.getEmail())
                        .subscribedAt(subscriber.getSubscribedAt())
                        .build())
                .collect(Collectors.toList());
    }

    
}
