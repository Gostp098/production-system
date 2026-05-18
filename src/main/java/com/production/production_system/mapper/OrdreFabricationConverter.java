package com.production.production_system.mapper;

import com.production.production_system.dto.OrdreFabricationDTO;
import com.production.production_system.entity.OrdreFabrication;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrdreFabricationConverter {

    private final ModelMapper modelMapper;

    public OrdreFabricationDTO toDto(OrdreFabrication ordre) {
        OrdreFabricationDTO dto = modelMapper.map(ordre, OrdreFabricationDTO.class);
        // Flatten nested produit object → produitId, produitNom
        if (ordre.getProduit() != null) {
            dto.setProduitId(ordre.getProduit().getId());
            dto.setProduitNom(ordre.getProduit().getNom());
        }
        // Flatten nested machine object → machineId, machineNom
        if (ordre.getMachine() != null) {
            dto.setMachineId(ordre.getMachine().getId());
            dto.setMachineNom(ordre.getMachine().getNom());
        }
        return dto;
    }

    public OrdreFabrication toEntity(OrdreFabricationDTO dto) {
        OrdreFabrication ordre = modelMapper.map(dto, OrdreFabrication.class);
        // produit and machine must be resolved from repos in the service
        ordre.setProduit(null);
        ordre.setMachine(null);
        ordre.setId(null);
        return ordre;
    }
}
