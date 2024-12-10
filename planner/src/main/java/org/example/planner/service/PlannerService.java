package org.example.planner.service;

import lombok.RequiredArgsConstructor;
import org.example.planner.dto.PlannerRequestDTO;
import org.example.planner.dto.PlannerResponseDTO;
import org.example.planner.entity.Planner;
import org.example.planner.repository.PlannerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

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

    public List<PlannerResponseDTO> findAllPlanner(){
        return plannerRepository.findAllPlanner();
    }

    public PlannerResponseDTO findPlannerById(Long id){
        PlannerResponseDTO responseDTO = new PlannerResponseDTO(plannerRepository.findPlannerById(id));
        return responseDTO;
    }

    public void deletePlanner(Long id, PlannerRequestDTO requestDTO) {
        Planner planner = plannerRepository.findPlannerById(id);

        if(planner==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }
        if(!planner.getPassword().equals(requestDTO.getPassword())||!planner.getId().equals(id)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Password not match. ");
        }

        plannerRepository.deletePlanner(id);
    }

        public PlannerResponseDTO updatePlanner(Long id, PlannerRequestDTO requestDTO) {
        Planner planner = plannerRepository.findPlannerById(id);
        if(planner.getPassword().equals(requestDTO.getPassword())|| Objects.equals(planner.getId(), id)){
            int updateRow = plannerRepository.updatePlanner(id,requestDTO.getTodo(),requestDTO.getName());
            planner = plannerRepository.findPlannerById(id);
            if(updateRow==0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No data has been modified");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"user_id, password not matched");
        }

        return new PlannerResponseDTO(planner);
    }
}
