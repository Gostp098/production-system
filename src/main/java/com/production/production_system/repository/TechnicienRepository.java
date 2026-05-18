package com.production.production_system.repository;

import com.production.production_system.entity.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TechnicienRepository extends JpaRepository<Technicien, Long> {

    List<Technicien> findByCompetencesContainingIgnoreCase(String competence);

    Optional<Technicien> findByMachineAssigneeId(Long machineId);
}