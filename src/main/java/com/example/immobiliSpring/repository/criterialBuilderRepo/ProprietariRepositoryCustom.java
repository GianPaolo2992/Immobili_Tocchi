package com.example.immobiliSpring.repository.criterialBuilderRepo;

import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.entity.Annessi;
import com.example.immobiliSpring.entity.Proprietari;

import java.util.List;

public interface ProprietariRepositoryCustom {
    List<Proprietari> searchProprietari(String tipo);
}
