package com.example.immobiliSpring.controller;

import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.service.ServiceProprietari;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proprietari")
public class ControllerProprietari {

    private final ServiceProprietari serviceProprietari;

    public ControllerProprietari(ServiceProprietari serviceProprietari) {
        this.serviceProprietari = serviceProprietari;
    }

    @GetMapping("/getProprietariById/{id}")
    public ProprietariDTO getProprietariById(@PathVariable("id") Integer id){
        return serviceProprietari.getProprietariById(id);
    }

}
