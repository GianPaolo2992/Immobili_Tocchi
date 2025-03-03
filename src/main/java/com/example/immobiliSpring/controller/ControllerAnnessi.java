package com.example.immobiliSpring.controller;

import com.example.immobiliSpring.DTO.AnnessiDTO;
import com.example.immobiliSpring.entity.Annessi;
import com.example.immobiliSpring.service.ServiceAnnessi;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annessi")
public class ControllerAnnessi {

    private final ServiceAnnessi serviceAnnessi;

    public ControllerAnnessi(ServiceAnnessi serviceAnnessi) {
        this.serviceAnnessi = serviceAnnessi;
    }
    @GetMapping("/search")
    public List<Annessi> searchAnnessi(@RequestParam String keyword) {
        return serviceAnnessi.searchAnnessi(keyword);
    }

    @GetMapping("/getAllAnnessi")
    public List<AnnessiDTO> getAllAnnessi() {
        return serviceAnnessi.getAllAnnessi();
    }

    @GetMapping("/getAllAnnessiById/{id}")
    public AnnessiDTO getAllAnnessiById(@PathVariable("id") Integer id) {
        return serviceAnnessi.getAllAnnessiById(id);
    }

    @PostMapping("/insertAnnessi")
    public AnnessiDTO insertAnnessi(@RequestBody AnnessiDTO annessiDTO) {
        return serviceAnnessi.insertAnnessi(annessiDTO);
    }

    @PutMapping("/updateAnnessi/{id}")
    public AnnessiDTO updateAnessi(@PathVariable("id") Integer id, @RequestBody AnnessiDTO annessiDTO) {
        return serviceAnnessi.updateAnessi(id, annessiDTO);
    }

    @DeleteMapping("/deleteAnnessi/{id}")
    public AnnessiDTO deleteAnessi(@PathVariable("id") Integer id) {
        return serviceAnnessi.deleteAnessi(id);
    }

    @GetMapping("/countBox")
    public List<Object[]> countBoxIntoDB() {
        return serviceAnnessi.getCountBoxIntoDB();
    }


}
