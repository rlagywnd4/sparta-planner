package org.example.planner.controller;

import lombok.RequiredArgsConstructor;
import org.example.planner.dto.PlannerRequestDTO;
import org.example.planner.dto.PlannerResponseDTO;
import org.example.planner.service.PlannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planner")
@RequiredArgsConstructor
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping
    public ResponseEntity<PlannerResponseDTO> createPlanner(@RequestBody PlannerRequestDTO request){
        PlannerResponseDTO planner = plannerService.create(request);

        return ResponseEntity.ok(planner);
    }

}
