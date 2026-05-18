package com.production.production_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MaintenanceDTO {

    private Long id;

    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    @NotBlank(message = "Le type est obligatoire")
    private String type;

    // Relations as flat IDs + names
    @NotNull(message = "La machine est obligatoire")
    private Long machineId;

    private String machineNom;

    @NotNull(message = "Le technicien est obligatoire")
    private Long technicienId;

    private String technicienNom;
}