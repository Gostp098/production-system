package com.production.production_system.service;

import com.production.production_system.mapper.OrdreFabricationConverter;
import com.production.production_system.dto.OrdreFabricationDTO;
import com.production.production_system.entity.Machine;
import com.production.production_system.entity.OrdreFabrication;
import com.production.production_system.entity.Produit;
import com.production.production_system.repository.MachineRepository;
import com.production.production_system.repository.OrdreFabricationRepository;
import com.production.production_system.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrdreFabricationService {

    private final OrdreFabricationRepository ordreRepo;
    private final ProduitRepository produitRepo;
    private final MachineRepository machineRepo;
    private final OrdreFabricationConverter ordreConverter;

    public List<OrdreFabricationDTO> getAll() {
        return ordreRepo.findAll().stream().map(ordreConverter::toDto).toList();
    }

    public Optional<OrdreFabricationDTO> getById(Long id) {
        return ordreRepo.findById(id).map(ordreConverter::toDto);
    }

    public List<OrdreFabricationDTO> getByStatut(String statut) {
        return ordreRepo.findByStatut(statut).stream().map(ordreConverter::toDto).toList();
    }

    public List<OrdreFabricationDTO> getPlanning(LocalDate start, LocalDate end) {
        return ordreRepo.findByDateBetween(start, end).stream().map(ordreConverter::toDto).toList();
    }

    @Transactional
    public OrdreFabricationDTO create(OrdreFabricationDTO dto) {
        Produit produit = produitRepo.findById(dto.getProduitId()).orElse(null);
        Machine machine = machineRepo.findById(dto.getMachineId()).orElse(null);

        if (produit == null || machine == null) return null;

        OrdreFabrication ordre = new OrdreFabrication();
        ordre.setQuantite(dto.getQuantite());
        ordre.setProduit(produit);
        ordre.setMachine(machine);
        ordre.setStatut("PLANIFIE");
        ordre.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());

        return ordreConverter.toDto(ordreRepo.save(ordre));
    }

    @Transactional
    public Optional<OrdreFabricationDTO> update(Long id, OrdreFabricationDTO dto) {
        return ordreRepo.findById(id).map(existing -> {
            Produit produit = produitRepo.findById(dto.getProduitId()).orElse(null);
            Machine machine = machineRepo.findById(dto.getMachineId()).orElse(null);

            if (produit != null) existing.setProduit(produit);
            if (machine != null) existing.setMachine(machine);
            existing.setQuantite(dto.getQuantite());
            if (dto.getDate() != null) existing.setDate(dto.getDate());

            return ordreConverter.toDto(ordreRepo.save(existing));
        });
    }

    @Transactional
    public Optional<OrdreFabricationDTO> changeStatut(Long id, String newStatut) {
        return ordreRepo.findById(id).map(ordre -> {
            ordre.setStatut(newStatut);
            return ordreConverter.toDto(ordreRepo.save(ordre));
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!ordreRepo.existsById(id)) return false;
        ordreRepo.deleteById(id);
        return true;
    }
}