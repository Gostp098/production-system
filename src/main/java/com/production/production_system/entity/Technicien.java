package com.production.production_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technicien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String competences;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machineAssignee;
}