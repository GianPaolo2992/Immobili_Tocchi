package com.example.immobiliSpring.repository;

import com.example.immobiliSpring.entity.Proprietari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProrpietariRepository extends JpaRepository<Proprietari,Integer> {
}
