package com.example.immobiliSpring.repository;


import com.example.immobiliSpring.entity.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ImmobileRepository extends JpaRepository<Immobile,Integer> {

@Query("SELECT i.tipo, a.tipo, COUNT(i.tipo) " +
        "FROM Immobile i " +
        "JOIN Annessi a ON i.id = a.immobile.id " +
        "WHERE i.tipo = 'Villa' AND a.tipo = 'Giardino' " +
        "GROUP BY i.tipo, a.tipo")
List<Object[]> VilleWithGarden();


//--ELENCO IMMOBILI COSTRUITI DOPO IL 1996
  @Query("SELECT i FROM Immobile i WHERE i.anno > 1996 ")
 List<Immobile> findImmobiliAfter1996();



//elenco immobili senza proprietario
    @Query("SELECT i FROM Immobile i WHERE i.proprietari IS NULL")
    List<Immobile> findImmobiliNullProp();

}

