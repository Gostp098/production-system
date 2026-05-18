package com.production.production_system.controller;

import com.production.production_system.dto.ProduitDTO;
import com.production.production_system.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
@Tag(name = "Produits")
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    @Operation(summary = "Lister tous les produits")
    public List<ProduitDTO> getAll() {
        return produitService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un produit par ID")
    public ResponseEntity<ProduitDTO> getById(@PathVariable Long id) {
        return produitService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stock-bas")
    @Operation(summary = "Produits avec stock bas (≤ seuil, défaut 10)")
    public List<ProduitDTO> getLowStock(@RequestParam(defaultValue = "10") int seuil) {
        return produitService.findLowStock(seuil);
    }

    @PostMapping
    @Operation(summary = "Créer un produit")
    public ResponseEntity<ProduitDTO> create(@Valid @RequestBody ProduitDTO dto) {
        return ResponseEntity.status(201).body(produitService.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un produit")
    public ResponseEntity<ProduitDTO> update(@PathVariable Long id,
                                             @Valid @RequestBody ProduitDTO dto) {
        return produitService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un produit")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return produitService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}