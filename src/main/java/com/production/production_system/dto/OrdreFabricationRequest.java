package com.production.production_system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class OrdreFabricationRequest {

    private int quantite;
    private Long produitId;
    private Long machineId;
}