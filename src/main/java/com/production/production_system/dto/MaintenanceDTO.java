package com.production.production_system.dto;

import lombok.Data;

import java.time.LocalDate;
@Data

public class MaintenanceDTO {

    private Long id;
    private String machineNom;
    private String technicienNom;
    private LocalDate date;
    private String type;
}