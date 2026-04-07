package com.production.production_system.controller;

import com.production.production_system.entity.Machine;
import com.production.production_system.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machines")
public class MachineController {

    @Autowired
    private MachineService service;

    @GetMapping
    public List<Machine> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Machine getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Machine create(@RequestBody Machine m) {
        return service.create(m);
    }

    @PutMapping("/{id}")
    public Machine update(@PathVariable Long id, @RequestBody Machine m) {
        return service.update(id, m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}