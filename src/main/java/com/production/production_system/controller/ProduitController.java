package com.production.production_system.controller;

import com.production.production_system.entity.Produit;
import com.production.production_system.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping
    public Produit create(@RequestBody Produit produit) {
        return produitService.save(produit);
    }

    @GetMapping
    public List<Produit> getAll() {
        return produitService.findAll();
    }

    @GetMapping("/{id}")
    public Produit getById(@PathVariable Long id) {
        return produitService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produitService.delete(id);
    }
}