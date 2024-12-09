package org.example.planner.repository;

import lombok.AllArgsConstructor;
import org.example.planner.dto.PlannerResponseDTO;
import org.example.planner.entity.Planner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PlannerRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlannerResponseDTO save(Planner planner) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("planner").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", planner.getTodo());
        parameters.put("name", planner.getName());
        parameters.put("password", planner.getPassword());
        parameters.put("createdAt", LocalDate.now());
        parameters.put("updatedAt", LocalDate.now());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new PlannerResponseDTO(key.longValue(), planner.getTodo(), planner.getName());
    }
}
