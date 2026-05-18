package com.production.production_system.controller;

import com.production.production_system.dto.OrdreFabricationDTO;
import com.production.production_system.service.OrdreFabricationService;
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
@RequestMapping("/api/ordres")
@RequiredArgsConstructor
@Tag(name = "Ordres de fabrication")
public class OrdreFabricationController {

    private final OrdreFabricationService service;

    @GetMapping
    @Operation(summary = "Lister tous les ordres")
    public List<OrdreFabricationDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un ordre par ID")
    public ResponseEntity<OrdreFabricationDTO> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Filtrer par statut")
    public List<OrdreFabricationDTO> getByStatut(@PathVariable String statut) {
        return service.getByStatut(statut);
    }

    @GetMapping("/planning")
    @Operation(summary = "Planning entre deux dates")
    public List<OrdreFabricationDTO> getPlanning(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.getPlanning(start, end);
    }

    @PostMapping
    @Operation(summary = "Créer un ordre de fabrication")
    public ResponseEntity<OrdreFabricationDTO> create(@Valid @RequestBody OrdreFabricationDTO dto) {
        OrdreFabricationDTO created = service.create(dto);
        return created != null
                ? ResponseEntity.status(201).body(created)
                : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un ordre")
    public ResponseEntity<OrdreFabricationDTO> update(@PathVariable Long id,
                                                      @Valid @RequestBody OrdreFabricationDTO dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/statut")
    @Operation(summary = "Changer le statut")
    public ResponseEntity<OrdreFabricationDTO> changeStatut(@PathVariable Long id,
                                                            @RequestParam String statut) {
        return service.changeStatut(id, statut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un ordre")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}