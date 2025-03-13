package com.example.immobiliSpring.repository.criterialBuilderRepo;


import com.example.immobiliSpring.entity.Proprietari;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class ProprietariRepositoryCustomImpl implements ProprietariRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Proprietari> searchProprietari(String keyword) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Proprietari> cq = cb.createQuery(Proprietari.class);
        Root<Proprietari> proprietari = cq.from(Proprietari.class);

        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            Predicate nomePredicate = cb.like(cb.lower(proprietari.get("nome")), "%" + keyword.trim() + "%");
            Predicate cognomePredicate = cb.like(cb.lower(proprietari.get("cognome")), "%" + keyword.trim()+ "%");
            // Utilizza OR per combinare i predicati
            predicates.add(cb.or(nomePredicate, cognomePredicate));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

}
