package com.production.production_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProduitDTO {

    private Long id;

    @NotBlank(message = "Le nom du produit est obligatoire")
    private String nom;

    @NotBlank(message = "Le type est obligatoire")
    private String type;

    @Min(value = 0, message = "Le stock ne peut pas être négatif")
    private int stock;

    private String fournisseur;
}