package com.example.immobiliSpring.repository;

import com.example.immobiliSpring.entity.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImmobileRepository extends JpaRepository<Immobile,Integer> {
}
