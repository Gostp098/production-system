package com.production.production_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MachineDTO {

    private Long id;

    @NotBlank(message = "Le nom de la machine est obligatoire")
    private String nom;

    @NotBlank(message = "L'état est obligatoire")
    private String etat;

    private LocalDate maintenanceProchaine;
}