package com.example.immobiliSpring.repository;

import com.example.immobiliSpring.entity.Annessi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AnnessiRepository extends JpaRepository<Annessi, Integer> {
}
