package com.production.production_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data

public class OrdreFabrication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;
    private LocalDate date;
    private String statut;

    @ManyToOne
    private Produit produit;

    @ManyToOne
    private Machine machine;
}