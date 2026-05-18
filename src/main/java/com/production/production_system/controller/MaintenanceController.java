package com.production.production_system.controller;

import com.production.production_system.dto.MaintenanceDTO;
import com.production.production_system.service.MaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping
    @Operation(summary = "Lister toutes les maintenances")
    public List<MaintenanceDTO> getAll() {
        return maintenanceService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une maintenance par ID")
    public ResponseEntity<MaintenanceDTO> getById(@PathVariable Long id) {
        return maintenanceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/machine/{machineId}")
    @Operation(summary = "Maintenances d'une machine")
    public List<MaintenanceDTO> getByMachine(@PathVariable Long machineId) {
        return maintenanceService.getByMachine(machineId);
    }

    @GetMapping("/technicien/{technicienId}")
    @Operation(summary = "Maintenances d'un technicien")
    public List<MaintenanceDTO> getByTechnicien(@PathVariable Long technicienId) {
        return maintenanceService.getByTechnicien(technicienId);
    }

    @GetMapping("/planning")
    @Operation(summary = "Maintenances entre deux dates")
    public List<MaintenanceDTO> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return maintenanceService.getByDateRange(start, end);
    }

    @PostMapping
    @Operation(summary = "Planifier une maintenance")
    public ResponseEntity<MaintenanceDTO> create(@Valid @RequestBody MaintenanceDTO dto) {
        MaintenanceDTO created = maintenanceService.create(dto);
        return created != null
                ? ResponseEntity.status(201).body(created)
                : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une maintenance")
    public ResponseEntity<MaintenanceDTO> update(@PathVariable Long id,
                                                 @Valid @RequestBody MaintenanceDTO dto) {
        return maintenanceService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une maintenance")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return maintenanceService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}