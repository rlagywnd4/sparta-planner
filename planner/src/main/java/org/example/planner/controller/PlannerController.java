package org.example.planner.controller;

import lombok.RequiredArgsConstructor;
import org.example.planner.dto.PlannerRequestDTO;
import org.example.planner.dto.PlannerResponseDTO;
import org.example.planner.service.PlannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<PlannerResponseDTO> findPlannerById(@PathVariable Long id){
        PlannerResponseDTO planner = plannerService.findPlannerById(id);

        log.debug(planner.toString());
        return ResponseEntity.ok(planner);
    }

    @GetMapping
    public ResponseEntity<List<PlannerResponseDTO>> findAllPlanner(){
        List<PlannerResponseDTO> planner = plannerService.findAllPlanner();

        return ResponseEntity.ok(planner);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlannerResponseDTO> updatePlanner(@PathVariable Long id, @RequestBody PlannerRequestDTO requestDTO){
        PlannerResponseDTO responseDTO = plannerService.updatePlanner(id, requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlanner(@PathVariable Long id, @RequestBody PlannerRequestDTO requestDTO){
        plannerService.deletePlanner(id, requestDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }


}
