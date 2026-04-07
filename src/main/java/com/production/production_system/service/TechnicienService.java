package com.production.production_system.service;

import com.production.production_system.entity.Machine;
import com.production.production_system.entity.Technicien;
import com.production.production_system.repository.MachineRepository;
import com.production.production_system.repository.TechnicienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicienService {

    private final TechnicienRepository technicienRepository;
    private final MachineRepository machineRepository;

    // CREATE
    public Technicien create(Technicien technicien) {
        return technicienRepository.save(technicien);
    }

    // READ ALL
    public List<Technicien> getAll() {
        return technicienRepository.findAll();
    }

    // READ BY ID
    public Technicien getById(Long id) {
        return technicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technicien not found"));
    }

    // UPDATE
    public Technicien update(Long id, Technicien technicien) {
        Technicien existing = getById(id);
        existing.setNom(technicien.getNom());
        existing.setCompetences(technicien.getCompetences());

        if (technicien.getMachineAssignee() != null) {
            Machine machine = machineRepository.findById(technicien.getMachineAssignee().getId())
                    .orElseThrow(() -> new RuntimeException("Machine not found"));
            existing.setMachineAssignee(machine);
        }

        return technicienRepository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        technicienRepository.deleteById(id);
    }

    // ASSIGN MACHINE
    public Technicien assignMachine(Long technicienId, Long machineId) {
        Technicien technicien = getById(technicienId);
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new RuntimeException("Machine not found"));

        technicien.setMachineAssignee(machine);
        return technicienRepository.save(technicien);
    }
}