package com.example.immobiliSpring.repository;

import com.example.immobiliSpring.entity.Annessi;
import com.example.immobiliSpring.repository.criterialBuilderRepo.AnnessiRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AnnessiRepository extends JpaRepository<Annessi, Integer> , AnnessiRepositoryCustom {
    @Query("SELECT tipo, COUNT(tipo) FROM Annessi WHERE tipo LIKE 'Box' GROUP BY(tipo)")
    List<Object[]> countBoxIntoDB();
}
