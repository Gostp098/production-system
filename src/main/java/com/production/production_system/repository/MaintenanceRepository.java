package com.production.production_system.repository;

import com.production.production_system.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findByMachineId(Long machineId);
    List<Maintenance> findByTechnicienId(Long technicienId);
    List<Maintenance> findByDateBetween(LocalDate start, LocalDate end);

}
