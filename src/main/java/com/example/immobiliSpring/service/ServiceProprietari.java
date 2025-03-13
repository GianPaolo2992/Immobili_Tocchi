package com.example.immobiliSpring.service;

import com.example.immobiliSpring.DTO.ProprietariDTO;
import com.example.immobiliSpring.converter.ConverterProprietari;
import com.example.immobiliSpring.entity.Immobile;
import com.example.immobiliSpring.entity.Proprietari;
import com.example.immobiliSpring.repository.ImmobileRepository;
import com.example.immobiliSpring.repository.ProrpietariRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceProprietari {

    private final ProrpietariRepository proprietariRepository;
    private final ImmobileRepository immobileRepository;
    private final ConverterProprietari converterProprietari;

//    public ServiceProprietari(ProrpietariRepository prorpietariRepository, ImmobileRepository immobileRepository, ConverterProprietari converterProprietari) {
//        this.prorpietariRepository = prorpietariRepository;
//        this.immobileRepository = immobileRepository;
//        this.converterProprietari = converterProprietari;
//    }
public List<ProprietariDTO> searchProprietari(String keyword) {

    return proprietariRepository.searchProprietari(keyword).stream().map(converterProprietari::converterToDTO).toList();
}
    @Transactional
    public ProprietariDTO getProprietariById(Integer id) {
        Optional<Proprietari> proprietariOPT = proprietariRepository.findById(id);

        if (proprietariOPT.isPresent()) {

            return converterProprietari.converterToDTO(proprietariOPT.get());

        } else {
            throw new EntityNotFoundException("Proprietario Not Found");
        }
    }

    @Transactional
    public List<ProprietariDTO> getAllProprietari() {
        List<ProprietariDTO> listaPropDTO = new ArrayList<>();
        List<Proprietari> listaProprietari = proprietariRepository.findAll();

        for (Proprietari prop : listaProprietari) {
            ProprietariDTO propDTO = this.converterProprietari.converterToDTO(prop);
            listaPropDTO.add(propDTO);
        }

        return listaPropDTO;
    }


    @Transactional
    public ProprietariDTO insertProprietario(ProprietariDTO proprietariDTO) {
        Proprietari propSaved = converterProprietari.converterToEntityXInsert(proprietariDTO);
        try {

            proprietariRepository.save(propSaved);

            System.out.println("Proprietario salvato: " + propSaved);
            return this.converterProprietari.converterToDTO(propSaved);
        } catch (Exception e) {

            System.err.println("Errore durante l'inserimento del proprietario: " + e.getMessage());

            throw new RuntimeException("Errore durante l'inserimento del proprietario", e);
        }
    }


    //    @Transactional
    public ProprietariDTO updateProprietario(Integer id, ProprietariDTO proprietariDTO) {
        Proprietari propOPT = proprietariRepository.findById(id).orElseThrow(() -> new RuntimeException("Proprietario non trovato"));


        propOPT = converterProprietari.converterToEntityXUpdate(proprietariDTO, propOPT);
        try {

            proprietariRepository.save(propOPT);

            System.out.println("Proprietario salvato: " + propOPT);
            return this.converterProprietari.converterToDTO(propOPT);
        } catch (Exception e) {

            System.err.println("Errore durante l'inserimento del proprietario: " + e.getMessage());

            throw new RuntimeException("Errore durante l'inserimento del proprietario", e);
        }

    }

    //    @Transactional
    public ProprietariDTO deleteProprietario(Integer id) {
        Proprietari propOPT = proprietariRepository.findById(id).orElseThrow(() -> new RuntimeException("prop non trovato"));
        try {
            if (propOPT.getListaImmobili() != null) {
                List<Immobile> immobileListCopy = new ArrayList<>(propOPT.getListaImmobili());
                for (Immobile immobile : immobileListCopy) {
                    Immobile immobileEntity = immobileRepository.findById(immobile.getId()).orElseThrow(
                            () -> new EntityNotFoundException("Immobile non trovato"));
                    immobile.setProprietari(null);
                    immobileRepository.save(immobileEntity);

                }
                proprietariRepository.delete(propOPT);
                return this.converterProprietari.converterToDTO(propOPT);
            } else {
                throw new EntityNotFoundException("Lista immobili null");
            }
        } catch (Exception e) {
            System.err.println("Errore durante l'eliminazione dell'immobile: " + e.getMessage());
            throw new RuntimeException("Errore durante l'eliminazione dell'immobile", e);
        }
    }

    @Transactional
    public ProprietariDTO AssocateImmobile(Integer idProp, Integer idImmbl) {
        Optional<Proprietari> propOPT = proprietariRepository.findById(idProp);
        Optional<Immobile> immobileOPT = immobileRepository.findById(idImmbl);
        if (propOPT.isPresent() && immobileOPT.isPresent()) {
            Proprietari propAssociate = propOPT.get();
            if (immobileOPT.get().getProprietari() == null) {

                Immobile immblAssociate = immobileOPT.get();

                propAssociate.getListaImmobili().add(immblAssociate);
                immblAssociate.setProprietari(propAssociate);

                proprietariRepository.save(propOPT.get());
                immobileRepository.save(immblAssociate);

            } else {
                System.out.println("non è possibile associare questo immobile perche appartenente ad un altro prop");
            }
            return this.converterProprietari.converterToDTO(propAssociate);
        } else {
            throw new EntityNotFoundException("Prop Not Found");
        }
    }

    public List<Object[]> getSumSuperficeProp() {
        return proprietariRepository.sumSuperficeProp();
    }

    public List<Object> sumSuperficePropName(String nome, String cognome) {
        return proprietariRepository.sumSuperficePropName(nome, cognome);
    }

    public List<Object[]> getPropOfVilla() {
        return proprietariRepository.propOfVilla();
    }

    public List<Object[]> getPropAppartmentWithBox() {
        return proprietariRepository.propAppartmentWithBox();
    }

    public List<Object[]> getPropTotVani() {
//        List<ProprietariDTO> listPropVaniDTO = new ArrayList<>();
//        List<Proprietari> listPropVani = prorpietariRepository.propTotVani();
//        if (!listPropVani.isEmpty()) {
//            for (Proprietari proprietari : listPropVani) {
//                ProprietariDTO proprietariDTO = ConverterProprietari.converterToDTO(proprietari);
//                    listPropVaniDTO.add(proprietariDTO);
//            }
//        }
//        return listPropVaniDTO;
        return proprietariRepository.propTotVani();
    }

    public List<Object[]> getOwnersWithMore400MQ() {
        return proprietariRepository.ownersWithMore400MQ();
    }

}
