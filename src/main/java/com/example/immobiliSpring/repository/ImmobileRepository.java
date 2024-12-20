package com.example.immobiliSpring.repository;

import com.example.immobiliSpring.entity.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ImmobileRepository extends JpaRepository<Immobile,Integer> {
}
