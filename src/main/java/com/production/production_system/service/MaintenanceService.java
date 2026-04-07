package com.production.production_system.service;

import com.production.production_system.entity.Maintenance;
import com.production.production_system.entity.Machine;
import com.production.production_system.entity.Technicien;
import com.production.production_system.repository.MaintenanceRepository;
import com.production.production_system.repository.MachineRepository;
import com.production.production_system.repository.TechnicienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MachineRepository machineRepository;
    private final TechnicienRepository technicienRepository;

    // CREATE
    public Maintenance create(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    // READ ALL
    public List<Maintenance> getAll() {
        return maintenanceRepository.findAll();
    }

    // READ BY ID
    public Maintenance getById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));
    }

    // UPDATE
    public Maintenance update(Long id, Maintenance maintenance) {
        Maintenance existing = getById(id);
        existing.setDate(maintenance.getDate());
        existing.setType(maintenance.getType());

        if (maintenance.getMachine() != null) {
            Machine machine = machineRepository.findById(maintenance.getMachine().getId())
                    .orElseThrow(() -> new RuntimeException("Machine not found"));
            existing.setMachine(machine);
        }

        if (maintenance.getTechnicien() != null) {
            Technicien technicien = technicienRepository.findById(maintenance.getTechnicien().getId())
                    .orElseThrow(() -> new RuntimeException("Technicien not found"));
            existing.setTechnicien(technicien);
        }

        return maintenanceRepository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        maintenanceRepository.deleteById(id);
    }

    // FOLLOW-UP / FILTERS

    // Get maintenances by machine
    public List<Maintenance> getByMachine(Long machineId) {
        return maintenanceRepository.findByMachineId(machineId);
    }

    // Get maintenances by technicien
    public List<Maintenance> getByTechnicien(Long technicienId) {
        return maintenanceRepository.findByTechnicienId(technicienId);
    }

    // Get maintenances between dates
    public List<Maintenance> getByDateRange(LocalDate start, LocalDate end) {
        return maintenanceRepository.findByDateBetween(start, end);
    }
}