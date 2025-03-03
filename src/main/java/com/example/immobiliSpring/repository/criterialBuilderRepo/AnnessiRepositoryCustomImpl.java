package com.example.immobiliSpring.repository.criterialBuilderRepo;


import com.example.immobiliSpring.entity.Annessi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

//import javax.persisten.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnnessiRepositoryCustomImpl implements AnnessiRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
//    public List<Annessi> searchAnnessi(String keyword, Integer superficie) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Annessi> cq = cb.createQuery(Annessi.class);
//        Root<Annessi> annessi = cq.from(Annessi.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (keyword != null && !keyword.isEmpty()) {
//            predicates.add(cb.like(annessi.get("tipo"), "%" + keyword + "%"));
//            // Aggiungi altri campi se necessario
//        }
//
//        if (superficie != null) {
//            predicates.add(cb.equal(annessi.get("superfice"), superficie));
//        }
//
//        cq.where(predicates.toArray(new Predicate[0]));
//
//        return entityManager.createQuery(cq).getResultList();
//    }
    public List<Annessi> searchAnnessi(String keyword) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Annessi> cq = cb.createQuery(Annessi.class);
        Root<Annessi> annessi = cq.from(Annessi.class);

        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.isEmpty()) {
            if (isNumeric(keyword)) {
//                    Integer superficie = Integer.parseInt(keyword);
                    predicates.add(cb.like(annessi.get("superficie").as(String.class), "%" + keyword + "%"));
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
//    public List<Annessi> searchAnnessi(String keyword) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Annessi> cq = cb.createQuery(Annessi.class);
//        Root<Annessi> annessi = cq.from(Annessi.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (keyword != null && !keyword.isEmpty()) {
////            try {
//
////                predicates.add(cb.like(annessi.get("superfice").as(String.class), "%" + keyword + "%"));
////                Integer superficie = Integer.parseInt(keyword);
//////                predicates.add(cb.equal(annessi.get("superficie"), superficie ));
////                predicates.add(cb.like(cb.string(annessi.get("sup")), "%" + superficie + "%"));
////            } catch (NumberFormatException e) {
//                // La parola chiave non è un numero valido, gestisci di conseguenza
//                predicates.add(cb.like(annessi.get("tipo"), "%" + keyword + "%"));
////            }
//        }
//
//        cq.where(predicates.toArray(new Predicate[0]));
//
//        return entityManager.createQuery(cq).getResultList();
//    }

}

