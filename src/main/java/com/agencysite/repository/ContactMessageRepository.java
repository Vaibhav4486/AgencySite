package com.agencysite.repository;

import com.agencysite.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    @Query("SELECT c.interestedService.id, c.interestedService.title, COUNT(c) " +
           "FROM ContactMessage c " +
           "WHERE c.interestedService IS NOT NULL " +
           "GROUP BY c.interestedService.id, c.interestedService.title")
    List<Object[]> countMessagesGroupedByService();
    List<ContactMessage> findByInterestedServiceId(Long serviceId);
}