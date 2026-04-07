package com.production.production_system.repository;

import com.production.production_system.entity.Machine;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
}