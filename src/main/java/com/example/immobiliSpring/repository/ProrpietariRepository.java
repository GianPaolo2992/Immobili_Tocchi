package com.example.immobiliSpring.repository;

import com.example.immobiliSpring.entity.Proprietari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProrpietariRepository extends JpaRepository<Proprietari,Integer> {
    //--METRI DI UNO SPECIFICO PROPRIETARIO
    @Query("SELECT p.nome,p.cognome, SUM(i.superficie + a.superficie) " +
            "FROM Immobile i " +
            "JOIN Proprietari p ON p.id = i.proprietari.id " +
            "JOIN Annessi a ON i.id = a.immobile.id " +
            "GROUP BY p.nome, p.cognome " +
            "ORDER BY SUM(i.superficie + a.superficie) ASC")
    List<Object[]> sumSuperficeProp();

    @Query("SELECT p.nome,p.cognome, SUM(i.superficie + a.superficie) AS totSuperfice "  +
            "FROM Immobile i " +
            "JOIN Proprietari p ON p.id = i.proprietari.id " +
            "JOIN Annessi a ON i.id = a.immobile.id " +
            "WHERE p.nome = :nome AND p.cognome = :cognome " +
            "GROUP BY p.nome,p.cognome ")
    List<Object> sumSuperficePropName(@Param("nome") String nome, @Param( "cognome")String cognome);

    @Query("SELECT p.nome, p.cognome, i.tipo " +
            "FROM Immobile i " +
            "JOIN Proprietari p ON p.id = i.proprietari.id  " +
            "WHERE i.tipo LIKE '%illa' ")
    List<Object[]> propOfVilla();
    //--ELENCO POSSESSORI APPARTEMTNEO CON BOX

    @Query("SELECT p.nome, p.cognome, i.tipo, a.tipo " +
            "FROM Immobile i " +
            "JOIN Proprietari p ON p.id = i.proprietari.id " +
            "JOIN Annessi a ON a.immobile.id = i.id " +
            "WHERE i.tipo LIKE '%ppartamento' And a.tipo LIKE '%ox' ")
    List<Object[]> propAppartmentWithBox();

    //--PRORPIETARI E VANI POSSEDUTI
    @Query("SELECT p.nome,p.cognome, SUM(i.vani) " +
            "FROM Immobile i " +
            "JOIN Proprietari p ON p.id = i.proprietari.id " +
            "GROUP BY p.nome , p.cognome " +
            "ORDER BY (SUM(i.vani)) DESC ")
    List<Object[]> propTotVani();

    // --PROPRIETARI CON PIù DI 400 MQ
    @Query("SELECT p.nome,p.cognome, SUM(i.superficie + a.superficie) AS totSuperficie " +
            "FROM Immobile i " +
            "JOIN Proprietari p ON p.id = i.proprietari.id " +
            "JOIN Annessi a ON i.id = a.immobile.id " +
            "GROUP BY p.nome, p.cognome " +
            "HAVING SUM(i.superficie + a.superficie) >= 400 " +
            "ORDER BY SUM(i.superficie + a.superficie) ASC")
    List<Object[]> ownersWithMore400MQ();

}
