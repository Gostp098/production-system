package com.production.production_system.controller;

import com.production.production_system.dto.MachineDTO;
import com.production.production_system.service.MachineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machines")
@RequiredArgsConstructor
@Tag(name = "Machines")
public class MachineController {

    private final MachineService machineService;

    @GetMapping
    @Operation(summary = "Lister toutes les machines")
    public List<MachineDTO> getAll() {
        return machineService.getAll();
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Lister les machines disponibles")
    public List<MachineDTO> getDisponibles() {
        return machineService.getDisponibles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une machine par ID")
    public ResponseEntity<MachineDTO> getById(@PathVariable Long id) {
        return machineService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer une machine")
    public ResponseEntity<MachineDTO> create(@Valid @RequestBody MachineDTO dto) {
        return ResponseEntity.status(201).body(machineService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une machine")
    public ResponseEntity<MachineDTO> update(@PathVariable Long id,
                                             @Valid @RequestBody MachineDTO dto) {
        return machineService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une machine")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return machineService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}