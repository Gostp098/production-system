package com.production.production_system.controller;

import com.production.production_system.dto.OrdreFabricationDTO;
import com.production.production_system.dto.OrdreFabricationRequest;
import com.production.production_system.mapper.OrdreFabricationMapper;
import com.production.production_system.service.OrdreFabricationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ordres")
public class OrdreFabricationController {

    @Autowired
    private OrdreFabricationService service;

    @Autowired
    private OrdreFabricationMapper mapper;

    @GetMapping
    public List<OrdreFabricationDTO> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrdreFabricationDTO getById(@PathVariable Long id) {
        return mapper.toDTO(service.getById(id));
    }

    @PostMapping
    public OrdreFabricationDTO create(@RequestBody OrdreFabricationRequest request) {
        return mapper.toDTO(service.create(request));
    }

    @PutMapping("/{id}")
    public OrdreFabricationDTO update(@PathVariable Long id,
                                      @RequestBody OrdreFabricationRequest request) {
        return mapper.toDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}