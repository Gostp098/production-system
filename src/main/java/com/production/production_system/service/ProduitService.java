package com.production.production_system.service;

import com.production.production_system.mapper.ProduitConverter;
import com.production.production_system.dto.ProduitDTO;
import com.production.production_system.entity.Produit;
import com.production.production_system.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitConverter produitConverter;

    public List<ProduitDTO> findAll() {
        return produitRepository.findAll()
                .stream()
                .map(produitConverter::toDto)
                .toList();
    }

    public Optional<ProduitDTO> findById(Long id) {
        return produitRepository.findById(id)
                .map(produitConverter::toDto);
    }

    public List<ProduitDTO> findLowStock(int threshold) {
        return produitRepository.findByStockLessThanEqual(threshold)
                .stream()
                .map(produitConverter::toDto)
                .toList();
    }

    @Transactional
    public ProduitDTO save(ProduitDTO dto) {
        Produit produit = produitConverter.toEntity(dto);
        return produitConverter.toDto(produitRepository.save(produit));
    }

    @Transactional
    public Optional<ProduitDTO> update(Long id, ProduitDTO dto) {
        return produitRepository.findById(id).map(existing -> {
            existing.setNom(dto.getNom());
            existing.setType(dto.getType());
            existing.setStock(dto.getStock());
            existing.setFournisseur(dto.getFournisseur());
            return produitConverter.toDto(produitRepository.save(existing));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!produitRepository.existsById(id)) return false;
        produitRepository.deleteById(id);
        return true;
    }
}