package com.production.production_system.mapper;


import com.production.production_system.dto.MaintenanceDTO;
import com.production.production_system.entity.Maintenance;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceConverter {

    private final ModelMapper modelMapper;

    public MaintenanceDTO toDto(Maintenance maintenance) {
        MaintenanceDTO dto = modelMapper.map(maintenance, MaintenanceDTO.class);
        // Flatten nested machine object → machineId, machineNom
        if (maintenance.getMachine() != null) {
            dto.setMachineId(maintenance.getMachine().getId());
            dto.setMachineNom(maintenance.getMachine().getNom());
        }
        // Flatten nested technicien object → technicienId, technicienNom
        if (maintenance.getTechnicien() != null) {
            dto.setTechnicienId(maintenance.getTechnicien().getId());
            dto.setTechnicienNom(maintenance.getTechnicien().getNom());
        }
        return dto;
    }

    public Maintenance toEntity(MaintenanceDTO dto) {
        Maintenance maintenance = modelMapper.map(dto, Maintenance.class);
        // machine and technicien must be resolved from repos in the service
        maintenance.setMachine(null);
        maintenance.setTechnicien(null);
        maintenance.setId(null);
        return maintenance;
    }
}
