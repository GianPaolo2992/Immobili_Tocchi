package com.example.immobiliSpring.repository.criterialBuilderRepo;

import com.example.immobiliSpring.entity.Immobile;

import java.util.List;

public interface ImmobileRepositoryCustom {
    List<Immobile> searchImmobile(String tipo);
}
