package com.example.immobiliSpring.repository.criterialBuilderRepo;


import com.example.immobiliSpring.entity.Immobile;
import com.example.immobiliSpring.entity.Proprietari;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class ImmobileRepositoryCustomImpl implements ImmobileRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Immobile> searchImmobile(String keyword) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Immobile> cq = cb.createQuery(Immobile.class);
        Root<Immobile> immobile = cq.from(Immobile.class);
        Join<Immobile, Proprietari> proprietariJoin = immobile.join("proprietari");
        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.isEmpty()) {
            if (isNumeric(keyword)) {
               Predicate superficie =  cb.like(cb.function("TO_CHAR", String.class, immobile.get("superficie"),cb.literal("999999999")), "%" + keyword + "%");
               Predicate costo =  cb.like(cb.function("TO_CHAR", String.class, immobile.get("costo"),cb.literal("999999999")), "%" + keyword + "%");
               Predicate vani =  cb.like(cb.function("TO_CHAR", String.class, immobile.get("vani"),cb.literal("9999999")), "%" + keyword + "%");
               Predicate anno =  cb.like(cb.function("TO_CHAR", String.class, immobile.get("anno"),cb.literal("9999")), "%" + keyword + "%");

                predicates.add(cb.or(superficie, costo, vani, anno));
            } else {

                Predicate tipo =cb.like(immobile.get("tipo"), "%" + keyword.trim() + "%");
                Predicate nomeProprietario = cb.like(cb.lower(proprietariJoin.get("nome")), "%" + keyword.trim() + "%");
                Predicate cognomeProprietario = cb.like(cb.lower(proprietariJoin.get("cognome")), "%" + keyword.trim() + "%");

                predicates.add(cb.or(tipo, nomeProprietario, cognomeProprietario));

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
