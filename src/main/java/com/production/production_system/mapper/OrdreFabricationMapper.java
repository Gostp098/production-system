package com.production.production_system.mapper;

import com.production.production_system.dto.OrdreFabricationDTO;
import com.production.production_system.entity.OrdreFabrication;
import org.springframework.stereotype.Component;

@Component
public class OrdreFabricationMapper {

    public OrdreFabricationDTO toDTO(OrdreFabrication o) {
        OrdreFabricationDTO dto = new OrdreFabricationDTO();

        dto.setId(o.getId());
        dto.setQuantite(o.getQuantite());
        dto.setStatut(o.getStatut());
        dto.setProduitNom(o.getProduit().getNom());
        dto.setMachineNom(o.getMachine().getNom());

        return dto;
    }
}