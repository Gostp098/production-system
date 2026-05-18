package com.production.production_system.controller;

import com.production.production_system.dto.TechnicienDTO;
import com.production.production_system.service.TechnicienService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/techniciens")
@RequiredArgsConstructor
@Tag(name = "Techniciens")
public class TechnicienController {

    private final TechnicienService technicienService;

    @GetMapping
    @Operation(summary = "Lister tous les techniciens")
    public List<TechnicienDTO> getAll() {
        return technicienService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un technicien par ID")
    public ResponseEntity<TechnicienDTO> getById(@PathVariable Long id) {
        return technicienService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-competence")
    @Operation(summary = "Filtrer par compétence")
    public List<TechnicienDTO> getByCompetence(@RequestParam String competence) {
        return technicienService.findByCompetence(competence);
    }

    @PostMapping
    @Operation(summary = "Créer un technicien")
    public ResponseEntity<TechnicienDTO> create(@Valid @RequestBody TechnicienDTO dto) {
        return ResponseEntity.status(201).body(technicienService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un technicien")
    public ResponseEntity<TechnicienDTO> update(@PathVariable Long id,
                                                @Valid @RequestBody TechnicienDTO dto) {
        return technicienService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un technicien")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return technicienService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{technicienId}/assign-machine/{machineId}")
    @Operation(summary = "Assigner un technicien à une machine")
    public ResponseEntity<TechnicienDTO> assignMachine(@PathVariable Long technicienId,
                                                       @PathVariable Long machineId) {
        return technicienService.assignMachine(technicienId, machineId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{technicienId}/unassign-machine")
    @Operation(summary = "Désassigner un technicien de sa machine")
    public ResponseEntity<TechnicienDTO> unassignMachine(@PathVariable Long technicienId) {
        return technicienService.unassignMachine(technicienId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}