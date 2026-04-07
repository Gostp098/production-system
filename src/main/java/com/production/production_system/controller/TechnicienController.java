package com.production.production_system.controller;

import com.production.production_system.entity.Technicien;
import com.production.production_system.service.TechnicienService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/techniciens")
@RequiredArgsConstructor
public class TechnicienController {

    private final TechnicienService technicienService;

    // CREATE
    @PostMapping
    public Technicien create(@RequestBody Technicien technicien) {
        return technicienService.create(technicien);
    }

    // READ ALL
    @GetMapping
    public List<Technicien> getAll() {
        return technicienService.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Technicien getById(@PathVariable Long id) {
        return technicienService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Technicien update(@PathVariable Long id, @RequestBody Technicien technicien) {
        return technicienService.update(id, technicien);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        technicienService.delete(id);
    }

    // ASSIGN MACHINE
    @PutMapping("/{technicienId}/assign-machine/{machineId}")
    public Technicien assignMachine(@PathVariable Long technicienId, @PathVariable Long machineId) {
        return technicienService.assignMachine(technicienId, machineId);
    }
}