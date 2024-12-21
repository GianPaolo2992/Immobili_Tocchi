package com.example.immobiliSpring.controller;

import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.service.ServiceProprietari;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietari")
public class ControllerProprietari {

    private final ServiceProprietari serviceProprietari;

    public ControllerProprietari(ServiceProprietari serviceProprietari) {
        this.serviceProprietari = serviceProprietari;
    }

    @GetMapping("/getProprietariById/{id}")
    public ProprietariDTO getProprietariById(@PathVariable("id") Integer id) {
        return serviceProprietari.getProprietariById(id);
    }

    @GetMapping("/getAllProprietari")
    public List<ProprietariDTO> getAllProprietari() {
        return serviceProprietari.getAllProprietari();
    }

    @PostMapping("/insertProp")
    public ProprietariDTO insertProprietari(@RequestBody ProprietariDTO proprietariDTO) {
        return serviceProprietari.insertProprietari(proprietariDTO);
    }

    @PutMapping("/updatePropById/{id}")
    public ProprietariDTO updateProprietario(@PathVariable("id")Integer id,@RequestBody ProprietariDTO proprietariDTO) {
        return serviceProprietari.updateProprietario(id,proprietariDTO);
    }

    @DeleteMapping("/deletePropById/{id}")
    public ProprietariDTO deleteProprietario(@PathVariable("id")Integer id) {
        return serviceProprietari.deleteProprietario(id);
    }

    @PostMapping("/AssociateProprietario{idProp}/Immobile{idImmbl}")
    public ProprietariDTO associateImmobile(@PathVariable("idProp")Integer idProp,@PathVariable("idImmbl")Integer idImmbl) {
        return serviceProprietari.AssocateImmobile(idProp,idImmbl);
    }


}
