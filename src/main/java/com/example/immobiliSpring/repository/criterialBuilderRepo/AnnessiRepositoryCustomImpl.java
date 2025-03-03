package com.example.immobiliSpring.repository.criterialBuilderRepo;


import com.example.immobiliSpring.entity.Annessi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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

        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.isEmpty()) {
            if (isNumeric(keyword)) {
                predicates.add(cb.like(cb.function("TO_CHAR", String.class, annessi.get("superficie"),cb.literal("9999999")), "%" + keyword + "%"));
            } else {
                predicates.add(cb.like(annessi.get("tipo"), "%" + keyword + "%"));
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

