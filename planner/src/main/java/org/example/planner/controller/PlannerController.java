package org.example.planner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping
    public ResponseEntity<PlannerResponseDTO> createPlanner(@RequestBody PlannerRequestDTO request){
        PlannerResponseDTO planner = plannerService.create(request);

        return ResponseEntity.ok(planner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlannerResponseDTO> findPlannerById(@PathVariable Long id){
        log.info("start find by id");
        PlannerResponseDTO planner = plannerService.findPlannerById(id);

        log.info("hihihihihi");
        log.debug(planner.toString());
        return ResponseEntity.ok(planner);
    }

    @GetMapping
    public ResponseEntity<List<PlannerResponseDTO>> findAllPlanner(){
        log.info("start find all");
        List<PlannerResponseDTO> planner = plannerService.findAllPlanner();

        return ResponseEntity.ok(planner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlanner(@PathVariable Long id, @RequestBody PlannerRequestDTO requestDTO){
        plannerService.deletePlanner(id, requestDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }


}
