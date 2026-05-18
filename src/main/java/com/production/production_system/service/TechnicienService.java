package com.production.production_system.service;

import com.production.production_system.mapper.TechnicienConverter;
import com.production.production_system.dto.TechnicienDTO;
import com.production.production_system.entity.Machine;
import com.production.production_system.entity.Technicien;
import com.production.production_system.repository.MachineRepository;
import com.production.production_system.repository.TechnicienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TechnicienService {

    private final TechnicienRepository technicienRepository;
    private final MachineRepository machineRepository;
    private final TechnicienConverter technicienConverter;

    public List<TechnicienDTO> getAll() {
        return technicienRepository.findAll()
                .stream()
                .map(technicienConverter::toDto)
                .toList();
    }

    public Optional<TechnicienDTO> getById(Long id) {
        return technicienRepository.findById(id)
                .map(technicienConverter::toDto);
    }

    public List<TechnicienDTO> findByCompetence(String competence) {
        return technicienRepository.findByCompetencesContainingIgnoreCase(competence)
                .stream()
                .map(technicienConverter::toDto)
                .toList();
    }

    @Transactional
    public TechnicienDTO create(TechnicienDTO dto) {
        Technicien technicien = technicienConverter.toEntity(dto);
        if (dto.getMachineAssigneeId() != null) {
            Machine machine = machineRepository.findById(dto.getMachineAssigneeId()).orElse(null);
            technicien.setMachineAssignee(machine);
        }
        return technicienConverter.toDto(technicienRepository.save(technicien));
    }

    @Transactional
    public Optional<TechnicienDTO> update(Long id, TechnicienDTO dto) {
        return technicienRepository.findById(id).map(existing -> {
            existing.setNom(dto.getNom());
            existing.setCompetences(dto.getCompetences());
            if (dto.getMachineAssigneeId() != null) {
                Machine machine = machineRepository.findById(dto.getMachineAssigneeId()).orElse(null);
                existing.setMachineAssignee(machine);
            } else {
                existing.setMachineAssignee(null);
            }
            return technicienConverter.toDto(technicienRepository.save(existing));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!technicienRepository.existsById(id)) return false;
        technicienRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Optional<TechnicienDTO> assignMachine(Long technicienId, Long machineId) {
        return technicienRepository.findById(technicienId).map(technicien -> {
            Machine machine = machineRepository.findById(machineId).orElse(null);
            technicien.setMachineAssignee(machine);
            return technicienConverter.toDto(technicienRepository.save(technicien));
        });
    }

    @Transactional
    public Optional<TechnicienDTO> unassignMachine(Long technicienId) {
        return technicienRepository.findById(technicienId).map(technicien -> {
            technicien.setMachineAssignee(null);
            return technicienConverter.toDto(technicienRepository.save(technicien));
        });
    }
}