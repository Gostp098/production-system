package com.production.production_system.repository;

import com.production.production_system.entity.Machine;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    List<Machine> findByEtat(String etat);


}