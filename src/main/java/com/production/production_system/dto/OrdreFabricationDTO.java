package com.production.production_system.dto;

import lombok.Data;

@Data
public class OrdreFabricationDTO {

    private Long id;
    private int quantite;
    private String statut;
    private String produitNom;
    private String machineNom;

}