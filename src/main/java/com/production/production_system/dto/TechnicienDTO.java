package com.production.production_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TechnicienDTO {

    private Long id;

    @NotBlank(message = "Le nom du technicien est obligatoire")
    private String nom;

    private String competences;

    // Only the ID of the assigned machine, not the full object
    private Long machineAssigneeId;

    // Read-only — populated in toDto(), ignored in toEntity()
    private String machineAssigneeNom;
}