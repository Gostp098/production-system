package com.production.production_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type;
    private int stock;
    private String fournisseur;
}