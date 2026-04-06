package com.production.production_system.service;

import com.production.production_system.entity.Produit;
import com.production.production_system.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;

    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    public Produit findById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit not found"));
    }

    public void delete(Long id) {
        produitRepository.deleteById(id);
    }
}