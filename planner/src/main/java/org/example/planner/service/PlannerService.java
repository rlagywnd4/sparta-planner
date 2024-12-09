package org.example.planner.service;

import lombok.RequiredArgsConstructor;
import org.example.planner.dto.PlannerRequestDTO;
import org.example.planner.dto.PlannerResponseDTO;
import org.example.planner.entity.Planner;
import org.example.planner.repository.PlannerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final PlannerRepository plannerRepository;

    public PlannerResponseDTO create(PlannerRequestDTO request) {
        Planner planner = Planner.builder()
                .todo(request.getTodo())
                .name(request.getName())
                .password(request.getPassword())
                .build();
        return plannerRepository.save(planner);
    }
}
