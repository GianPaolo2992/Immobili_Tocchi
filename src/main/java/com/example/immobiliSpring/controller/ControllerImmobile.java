package com.example.immobiliSpring.controller;

import com.example.immobiliSpring.DTO.ImmobileDTO;
import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.service.ServiceImmobili;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/immobile")
public class ControllerImmobile {

    private final ServiceImmobili serviceImmobili;

    public ControllerImmobile(ServiceImmobili serviceImmobili) {
        this.serviceImmobili = serviceImmobili;
    }
    @GetMapping("/search")
    public List<ImmobileDTO> searchProprietari(@RequestParam String keyword) {
        return serviceImmobili.searchImmobile(keyword);
    }
    @GetMapping("/getAllImmobili")
    public List<ImmobileDTO> getAllImmobili(){
        return serviceImmobili.getAllImmobili();
    }

    @GetMapping("/getImmobileById/{id}")
    public ImmobileDTO getImmobileById(@PathVariable("id") Integer id) {
            return serviceImmobili.getImmobileById(id);
    }

    @PostMapping("/insertImmobile")
    public ImmobileDTO insertImmobile(@RequestBody ImmobileDTO immobileDTO){
        return serviceImmobili.insertImmobile(immobileDTO);
    }

    @PutMapping("/updateImmobile/{id}")
    public ImmobileDTO updateImmobile(@PathVariable("id") Integer id, @RequestBody ImmobileDTO immobileDTO) {
        return serviceImmobili.updateImmobile(id, immobileDTO);
    }

    @DeleteMapping("/deleteImmobileById/{id}")
    public ImmobileDTO deleteImmobileById(@PathVariable("id") Integer id) {
        return serviceImmobili.deleteImmobileById(id);
    }

    @PostMapping("/AssociateAnnessi{idAnn}/immobili{idImmbl}")
    public ImmobileDTO AssociateAnnessi(@PathVariable("idAnn") Integer idAnnesso,@PathVariable("idImmbl") Integer idImmbl) {
        return serviceImmobili.AssociateAnnessi(idAnnesso,idImmbl);
    }
    @PutMapping("/DissociaAnnessi{idAnn}")
    public ImmobileDTO DissociaAnnessi(@PathVariable("idAnn") Integer id) {
        return serviceImmobili.DissociaAnnessi(id);
    }

    @GetMapping("/getVillaWithGarden")
    public List<Object[]> VilleWithGarden() {
        return serviceImmobili.getVillaWithGarden();
    }

    @GetMapping("/findImmobiliAfter1996")
    public List<ImmobileDTO> findImmobiliAfter1996() {
        return serviceImmobili.findImmobiliAfter1996();
    }

    @GetMapping("/findImmobiliNullProp")
    public List<ImmobileDTO> findImmobiliNullProp(){return serviceImmobili.findImmobiliNullProp();}

}
