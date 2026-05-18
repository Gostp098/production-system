package com.production.production_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String type;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private Technicien technicien;
}