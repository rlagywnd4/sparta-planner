package org.example.planner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.planner.entity.Planner;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PlannerResponseDTO {
    private Long id;
    private String todo;
    private String name;

    public PlannerResponseDTO(Planner planner){
        this.id = planner.getId();
        this.todo = planner.getTodo();
        this.name = planner.getName();
    }
}
