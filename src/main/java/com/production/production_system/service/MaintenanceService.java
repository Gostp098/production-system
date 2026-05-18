package com.production.production_system.service;

import com.production.production_system.mapper.MaintenanceConverter;
import com.production.production_system.dto.MaintenanceDTO;
import com.production.production_system.entity.Machine;
import com.production.production_system.entity.Maintenance;
import com.production.production_system.entity.Technicien;
import com.production.production_system.repository.MachineRepository;
import com.production.production_system.repository.MaintenanceRepository;
import com.production.production_system.repository.TechnicienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MachineRepository machineRepository;
    private final TechnicienRepository technicienRepository;
    private final MaintenanceConverter maintenanceConverter;

    public List<MaintenanceDTO> getAll() {
        return maintenanceRepository.findAll().stream().map(maintenanceConverter::toDto).toList();
    }

    public Optional<MaintenanceDTO> getById(Long id) {
        return maintenanceRepository.findById(id).map(maintenanceConverter::toDto);
    }

    public List<MaintenanceDTO> getByMachine(Long machineId) {
        return maintenanceRepository.findByMachineId(machineId)
                .stream().map(maintenanceConverter::toDto).toList();
    }

    public List<MaintenanceDTO> getByTechnicien(Long technicienId) {
        return maintenanceRepository.findByTechnicienId(technicienId)
                .stream().map(maintenanceConverter::toDto).toList();
    }

    public List<MaintenanceDTO> getByDateRange(LocalDate start, LocalDate end) {
        return maintenanceRepository.findByDateBetween(start, end)
                .stream().map(maintenanceConverter::toDto).toList();
    }

    @Transactional
    public MaintenanceDTO create(MaintenanceDTO dto) {
        Machine machine = machineRepository.findById(dto.getMachineId()).orElse(null);
        Technicien technicien = technicienRepository.findById(dto.getTechnicienId()).orElse(null);

        if (machine == null || technicien == null) return null;

        Maintenance maintenance = new Maintenance();
        maintenance.setDate(dto.getDate());
        maintenance.setType(dto.getType());
        maintenance.setMachine(machine);
        maintenance.setTechnicien(technicien);

        return maintenanceConverter.toDto(maintenanceRepository.save(maintenance));
    }

    @Transactional
    public Optional<MaintenanceDTO> update(Long id, MaintenanceDTO dto) {
        return maintenanceRepository.findById(id).map(existing -> {
            Machine machine = machineRepository.findById(dto.getMachineId()).orElse(null);
            Technicien technicien = technicienRepository.findById(dto.getTechnicienId()).orElse(null);

            if (machine != null) existing.setMachine(machine);
            if (technicien != null) existing.setTechnicien(technicien);
            existing.setDate(dto.getDate());
            existing.setType(dto.getType());

            return maintenanceConverter.toDto(maintenanceRepository.save(existing));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!maintenanceRepository.existsById(id)) return false;
        maintenanceRepository.deleteById(id);
        return true;
    }
}