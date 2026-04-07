package com.production.production_system.controller;

import com.production.production_system.entity.Maintenance;
import com.production.production_system.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    // CREATE
    @PostMapping
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }

    // READ ALL
    @GetMapping
    public List<Maintenance> getAll() {
        return maintenanceService.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Maintenance getById(@PathVariable Long id) {
        return maintenanceService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Maintenance update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        maintenanceService.delete(id);
    }

    // FILTERS / SUIVI

    // By machine
    @GetMapping("/machine/{machineId}")
    public List<Maintenance> getByMachine(@PathVariable Long machineId) {
        return maintenanceService.getByMachine(machineId);
    }

    // By technicien
    @GetMapping("/technicien/{technicienId}")
    public List<Maintenance> getByTechnicien(@PathVariable Long technicienId) {
        return maintenanceService.getByTechnicien(technicienId);
    }

    // By date range
    @GetMapping("/date")
    public List<Maintenance> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return maintenanceService.getByDateRange(start, end);
    }
}