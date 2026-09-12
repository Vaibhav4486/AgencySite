package com.agencysite.controller;

import com.agencysite.dto.TeamMemberDTO;
import com.agencysite.service.TeamMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @PostMapping
    public ResponseEntity<TeamMemberDTO> createTeamMember(@Valid @RequestBody TeamMemberDTO request) {
        TeamMemberDTO created = teamMemberService.createTeamMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<TeamMemberDTO>> getAllTeamMembers() {
        return ResponseEntity.ok(teamMemberService.getAllTeamMembers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable Long id) {
        teamMemberService.deleteTeamMember(id);
        return ResponseEntity.noContent().build();
    }
}