package com.production.production_system.service;

import com.production.production_system.mapper.MachineConverter;
import com.production.production_system.dto.MachineDTO;
import com.production.production_system.entity.Machine;
import com.production.production_system.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MachineService {

    private final MachineRepository machineRepository;
    private final MachineConverter machineConverter;

    public List<MachineDTO> getAll() {
        return machineRepository.findAll()
                .stream()
                .map(machineConverter::toDto)
                .toList();
    }

    public List<MachineDTO> getDisponibles() {
        return machineRepository.findByEtat("DISPONIBLE")
                .stream()
                .map(machineConverter::toDto)
                .toList();
    }

    public Optional<MachineDTO> getById(Long id) {
        return machineRepository.findById(id)
                .map(machineConverter::toDto);
    }

    @Transactional
    public MachineDTO create(MachineDTO dto) {
        Machine machine = machineConverter.toEntity(dto);
        return machineConverter.toDto(machineRepository.save(machine));
    }

    @Transactional
    public Optional<MachineDTO> update(Long id, MachineDTO dto) {
        return machineRepository.findById(id).map(existing -> {
            existing.setNom(dto.getNom());
            existing.setEtat(dto.getEtat());
            existing.setMaintenanceProchaine(dto.getMaintenanceProchaine());
            return machineConverter.toDto(machineRepository.save(existing));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!machineRepository.existsById(id)) return false;
        machineRepository.deleteById(id);
        return true;
    }
}