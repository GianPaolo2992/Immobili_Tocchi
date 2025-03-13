package com.example.immobiliSpring.repository.criterialBuilderRepo;


import com.example.immobiliSpring.entity.Annessi;
import com.example.immobiliSpring.entity.Immobile;
import com.example.immobiliSpring.entity.Proprietari;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnnessiRepositoryCustomImpl implements AnnessiRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Annessi> searchAnnessi(String keyword) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Annessi> cq = cb.createQuery(Annessi.class);
        Root<Annessi> annessi = cq.from(Annessi.class);
        Join<Annessi, Immobile> ImmobileJoin = annessi.join("immobile");
        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.isEmpty()) {
            if (isNumeric(keyword)) {
                predicates.add(cb.like(cb.function("TO_CHAR", String.class, annessi.get("superficie"),cb.literal("9999999")), "%" + keyword + "%"));
            } else {
                Predicate tipo =cb.like(annessi.get("tipo"), "%" + keyword.trim()+ "%");
                Predicate immobileTipo = cb.like(cb.lower(ImmobileJoin.get("tipo")), "%" + keyword.trim() + "%");
                predicates.add(cb.or(tipo,immobileTipo));
            }
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

//     Metodo di supporto per verificare se una stringa è numerica
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}

