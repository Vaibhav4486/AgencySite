package com.agencysite.repository;

import com.agencysite.entity.CaseStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseStudyRepository extends JpaRepository<CaseStudy, Long> {

    List<CaseStudy> findByServiceOfferingIdOrderByDisplayOrderAsc(Long serviceOfferingId);
}