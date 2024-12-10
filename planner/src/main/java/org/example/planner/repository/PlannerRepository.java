package org.example.planner.repository;

import lombok.AllArgsConstructor;
import org.example.planner.dto.PlannerResponseDTO;
import org.example.planner.entity.Planner;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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

    public List<PlannerResponseDTO> findAllPlanner() {
        return jdbcTemplate.query("select * from planner order by updated_at desc", plannerRowMapperResp());
    }

    public Planner findPlannerById(Long id) {
        List<Planner> result = jdbcTemplate.query("select * from planner where id = ?", plannerRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    public void deletePlanner(Long id) {
        jdbcTemplate.update("delete from planner where id= ?", id);
    }

    public int updatePlanner(Long id, String todo, String name) {
        return jdbcTemplate.update("update planner set todo = ?, name = ? , updated_at = ? where id = ?", todo, name, LocalDate.now(), id);
    }

    private RowMapper<Planner> plannerRowMapper() {
        return new RowMapper<Planner>() {
            @Override
            public Planner mapRow(ResultSet rs, int rowNum) throws SQLException {
                LocalDate updateAt = rs.getDate("updated_at").toLocalDate();
                LocalDate createdAt = rs.getDate("created_at").toLocalDate();
                return new Planner(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        createdAt,
                        updateAt
                );
            }
        };
    }

    private RowMapper<PlannerResponseDTO> plannerRowMapperResp() {
        return new RowMapper<PlannerResponseDTO>() {
            @Override
            public PlannerResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlannerResponseDTO(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name")
                );
            }
        };
    }

}
