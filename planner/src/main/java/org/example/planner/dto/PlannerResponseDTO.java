package org.example.planner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PlannerResponseDTO {
    private Long id;
    private String todo;
    private String name;
}
