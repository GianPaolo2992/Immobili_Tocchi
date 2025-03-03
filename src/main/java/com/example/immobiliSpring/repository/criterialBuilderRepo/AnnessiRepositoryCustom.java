package com.example.immobiliSpring.repository.criterialBuilderRepo;

import com.example.immobiliSpring.entity.Annessi;
import java.util.List;

public interface AnnessiRepositoryCustom {
    List<Annessi> searchAnnessi(String tipo);
}
