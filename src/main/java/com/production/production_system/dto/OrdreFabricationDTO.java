package com.production.production_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class OrdreFabricationDTO {

    private Long id;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    private int quantite;

    private LocalDate date;

    private String statut;

    // Relations represented as flat IDs + names
    @NotNull(message = "Le produit est obligatoire")
    private Long produitId;

    private String produitNom;

    @NotNull(message = "La machine est obligatoire")
    private Long machineId;

    private String machineNom;
}