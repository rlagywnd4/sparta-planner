package org.example.planner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Planner {
    private Long id;
    private String todo;
    private String name;
    private String password;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
