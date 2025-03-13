package com.example.immobiliSpring.controller;

import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.entity.Proprietari;
import com.example.immobiliSpring.service.ServiceProprietari;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietari")
public class ControllerProprietari {

    private final ServiceProprietari serviceProprietari;

    public ControllerProprietari(ServiceProprietari serviceProprietari) {
        this.serviceProprietari = serviceProprietari;
    }
    @GetMapping("/search")
    public List<ProprietariDTO> searchProprietari(@RequestParam String keyword) {
        return serviceProprietari.searchProprietari(keyword);
    }

    @GetMapping("/getProprietariById/{id}")
    public ProprietariDTO getProprietariById(@PathVariable("id") Integer id) {
        return serviceProprietari.getProprietariById(id);
    }

    @GetMapping("/getAllProprietari")
    public List<ProprietariDTO> getAllProprietari() {
        return serviceProprietari.getAllProprietari();
    }
//
//    @PostMapping("/insertProp")
//    public ProprietariDTO insertProprietari(@RequestBody ProprietariDTO proprietariDTO) {
//        return serviceProprietari.insertProprietari(proprietariDTO);
//    }
@PostMapping("/insertProp")
public ResponseEntity<ProprietariDTO> insertProprietari(@RequestBody ProprietariDTO proprietariDTO) {
//    try {
        ProprietariDTO savedProprietario = serviceProprietari.insertProprietario(proprietariDTO);
        return new ResponseEntity<>(savedProprietario, HttpStatus.CREATED);
//    } catch (Exception e) {
//        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//    }
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

    @GetMapping("/getSumSuperficeProp")
    public List<Object[]> sumSuperficeProp() {
        return serviceProprietari.getSumSuperficeProp();
    }
    @GetMapping("/getSumSuperficePropName")
    public List<Object> sumSuperficePropName( @RequestParam String nome,@RequestParam String cognome) {
        return serviceProprietari.sumSuperficePropName(nome,cognome);
    }


    @GetMapping("/getPropOfVilla")
    public List<Object[]> getPropOfVilla() {
        return serviceProprietari.getPropOfVilla();
    }
    @GetMapping("/getPropAppartmentWithBox")
    public List<Object[]> propAppartmentWithBox() {
        return serviceProprietari.getPropAppartmentWithBox();
    }

    @GetMapping("/getPropTotVani")
    public List<Object[]> propTotVani() {
        return serviceProprietari.getPropTotVani();
    }



    @GetMapping("/getOwnersWithMore400MQ")
    public List<Object[]>  ownersWithMore400MQ() {
        return serviceProprietari.getOwnersWithMore400MQ();
    }


}
