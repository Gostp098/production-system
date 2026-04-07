package com.production.production_system.service;

import com.production.production_system.entity.Machine;
import com.production.production_system.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    @Autowired
    private MachineRepository repo;

    public List<Machine> getAll() {
        return repo.findAll();
    }

    public Machine getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Machine create(Machine m) {
        return repo.save(m);
    }

    public Machine update(Long id, Machine m) {
        Machine existing = getById(id);
        existing.setNom(m.getNom());
        existing.setEtat(m.getEtat());
        existing.setMaintenanceProchaine(m.getMaintenanceProchaine());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}