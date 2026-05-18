package com.production.production_system.repository;

import com.production.production_system.entity.OrdreFabrication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdreFabricationRepository extends JpaRepository<OrdreFabrication, Long> {

    List<OrdreFabrication> findByStatut(String statut);

    List<OrdreFabrication> findByDateBetween(LocalDate start, LocalDate end);

    List<OrdreFabrication> findByMachineId(Long machineId);

    boolean existsByMachineIdAndDateAndStatutIn(Long machineId, LocalDate date, List<String> statuts);
}