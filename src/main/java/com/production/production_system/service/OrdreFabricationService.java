package com.production.production_system.service;

import com.production.production_system.dto.OrdreFabricationRequest;
import com.production.production_system.entity.Machine;
import com.production.production_system.entity.OrdreFabrication;
import com.production.production_system.entity.Produit;
import com.production.production_system.repository.MachineRepository;
import com.production.production_system.repository.OrdreFabricationRepository;
import com.production.production_system.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class OrdreFabricationService {

    @Autowired
    private OrdreFabricationRepository ordreRepo;

    @Autowired
    private ProduitRepository produitRepo;

    @Autowired
    private MachineRepository machineRepo;

    public List<OrdreFabrication> getAll() {
        return ordreRepo.findAll();
    }

    public OrdreFabrication getById(Long id) {
        return ordreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordre not found"));
    }

    public OrdreFabrication create(OrdreFabricationRequest request) {

        Produit produit = produitRepo.findById(request.getProduitId()).orElseThrow();
        Machine machine = machineRepo.findById(request.getMachineId()).orElseThrow();

        OrdreFabrication ordre = new OrdreFabrication();
        ordre.setQuantite(request.getQuantite());
        ordre.setProduit(produit);
        ordre.setMachine(machine);
        ordre.setStatut("PLANIFIE");
        ordre.setDate(LocalDate.now());

        return ordreRepo.save(ordre);
    }

    public OrdreFabrication update(Long id, OrdreFabricationRequest request) {

        OrdreFabrication ordre = getById(id);

        Produit produit = produitRepo.findById(request.getProduitId()).orElseThrow();
        Machine machine = machineRepo.findById(request.getMachineId()).orElseThrow();

        ordre.setQuantite(request.getQuantite());
        ordre.setProduit(produit);
        ordre.setMachine(machine);

        return ordreRepo.save(ordre);
    }

    public void delete(Long id) {
        ordreRepo.deleteById(id);
    }
}