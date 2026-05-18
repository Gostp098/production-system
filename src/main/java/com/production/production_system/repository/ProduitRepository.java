package com.production.production_system.repository;


import com.production.production_system.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByStockLessThanEqual(int threshold);
}