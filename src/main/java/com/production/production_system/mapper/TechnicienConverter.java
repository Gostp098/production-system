package com.production.production_system.mapper;

import com.production.production_system.dto.TechnicienDTO;
import com.production.production_system.entity.Technicien;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TechnicienConverter {

    private final ModelMapper modelMapper;

    public TechnicienDTO toDto(Technicien technicien) {
        TechnicienDTO dto = modelMapper.map(technicien, TechnicienDTO.class);
        // machineAssignee (full object) → machineAssigneeId + machineAssigneeNom (flat fields)
        // ModelMapper cannot resolve this automatically with STRICT strategy
        if (technicien.getMachineAssignee() != null) {
            dto.setMachineAssigneeId(technicien.getMachineAssignee().getId());
            dto.setMachineAssigneeNom(technicien.getMachineAssignee().getNom());
        }
        return dto;
    }

    public Technicien toEntity(TechnicienDTO dto) {
        Technicien technicien = modelMapper.map(dto, Technicien.class);
        // machineAssignee must be resolved from the repository in the service
        technicien.setMachineAssignee(null);
        technicien.setId(null);
        return technicien;
    }
}
