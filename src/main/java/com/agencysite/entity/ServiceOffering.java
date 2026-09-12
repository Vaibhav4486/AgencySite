package com.agencysite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_offerings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;              // e.g. "Web Design"
    private String shortDescription;   // one-liner for the Home page teaser card

    @Column(columnDefinition = "TEXT")
    private String description;        // longer copy for the Services page

    private String imageUrl;
    private String iconName;           // optional, e.g. "code", "megaphone", "briefcase"
    private Integer displayOrder;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.displayOrder == null) {
            this.displayOrder = 0;
        }
    }
    @OneToMany(mappedBy = "serviceOffering", cascade = CascadeType.ALL, orphanRemoval = true)
private List<CaseStudy> caseStudies = new ArrayList<>();
}
