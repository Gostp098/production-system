package com.production.production_system.mapper;

import com.production.production_system.dto.ProduitDTO;
import com.production.production_system.entity.Produit;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProduitConverter {

    private final ModelMapper modelMapper;

    public ProduitDTO toDto(Produit produit) {
        // All fields match 1-to-1 (id, nom, type, stock, fournisseur)
        return modelMapper.map(produit, ProduitDTO.class);
    }

    public Produit toEntity(ProduitDTO dto) {
        Produit produit = modelMapper.map(dto, Produit.class);
        produit.setId(null);
        return produit;
    }
}
