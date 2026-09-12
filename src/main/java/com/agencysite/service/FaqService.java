package com.agencysite.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.agencysite.dto.FaqDTO;
import com.agencysite.entity.Faq;
import com.agencysite.exception.ResourceNotFound;
import com.agencysite.repository.FaqRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FaqService 
{
    private final FaqRepository faq;
    public FaqDTO createFaq(FaqDTO request)
    {
        if(request.getQuestion()==null || request.getQuestion().isEmpty())
        {
            throw new IllegalArgumentException("Question is Required");
        }
        if(request.getAnswer()==null || request.getAnswer().isEmpty())
        {
            throw new IllegalArgumentException("Answer is Required");
        }
        Faq f=Faq.builder()
             .question(request.getQuestion())
             .answer(request.getAnswer())
             .displayOrder(request.getDisplayOrder())
             .build();
        Faq fa=faq.save(f);
        return new FaqDTO(fa.getId(),fa.getQuestion(),fa.getAnswer(),fa.getDisplayOrder());

        
    }
    public List<FaqDTO> getAllFaqs()
    {
        List<Faq> fa = faq.findAll(); 
return fa.stream()
         .map(fs -> { return new FaqDTO(fs.getId(), fs.getQuestion(), fs.getAnswer(), fs.getDisplayOrder()); }) // Added semicolon here
         .toList();

    }
    public void deleteFaq(Long id)
    {
        Faq fq=faq.findById(id).orElseThrow(()-> new ResourceNotFound(" Faq Not Found"+id));
        faq.delete(fq);
    }
}
