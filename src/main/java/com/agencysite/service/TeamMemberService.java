package com.agencysite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agencysite.dto.TeamMemberDTO;
import com.agencysite.entity.TeamMember;
import com.agencysite.exception.ResourceNotFound;
import com.agencysite.repository.TeamMemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service 
public class TeamMemberService 
{
    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberDTO createTeamMember(TeamMemberDTO request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (request.getRole() == null || request.getRole().isBlank()) {
            throw new IllegalArgumentException("Role is required");
        }

        TeamMember teamMember = new TeamMember();
        teamMember.setName(request.getName());
        teamMember.setRole(request.getRole());
        teamMember.setBio(request.getBio());
        teamMember.setImageUrl(request.getImageUrl());
        teamMember.setLinkedinUrl(request.getLinkedinUrl());
        teamMember.setDisplayOrder(request.getDisplayOrder());

        TeamMember savedTeamMember = teamMemberRepository.save(teamMember);
        return TeamMemberDTO.builder()
                .id(savedTeamMember.getId())
                .name(savedTeamMember.getName())
                .role(savedTeamMember.getRole())
                .bio(savedTeamMember.getBio())
                .imageUrl(savedTeamMember.getImageUrl())
                .linkedinUrl(savedTeamMember.getLinkedinUrl())
                .displayOrder(savedTeamMember.getDisplayOrder())
                .build();
    }

    public List<TeamMemberDTO> getAllTeamMembers() {
        return teamMemberRepository.findAllByOrderByDisplayOrderAsc().stream()
                .map(teamMember -> TeamMemberDTO.builder()
                        .id(teamMember.getId())
                        .name(teamMember.getName())
                        .role(teamMember.getRole())
                        .bio(teamMember.getBio())
                        .imageUrl(teamMember.getImageUrl())
                        .linkedinUrl(teamMember.getLinkedinUrl())
                        .displayOrder(teamMember.getDisplayOrder())
                        .build())
                .toList();
    }

    public void deleteTeamMember(Long id) {
        if (!teamMemberRepository.existsById(id)) {
            throw new ResourceNotFound("Team member not found: " + id);
        }
        teamMemberRepository.deleteById(id);
    }
    
}
