package com.taller2dam.taller.service;

import com.taller2dam.taller.dao.Mecanico;
import com.taller2dam.taller.repository.MecanicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MecanicoService {
    MecanicoRepository mecanicoRepository;

    public List<Mecanico> findAllMecanicos() {return mecanicoRepository.findAll();}
    public Optional<Mecanico> findMecanicoById(Long mecanicoId) {
        return mecanicoRepository.findById(mecanicoId);
    }
    public Mecanico saveMecanico(Mecanico mecanico) {
        return mecanicoRepository.save(mecanico);
    }


    public Optional<Mecanico> updateMecanico(Mecanico mecanico, Mecanico mecanicoNuevo) {
        Optional<Mecanico> mecanico1 = findMecanicoById(mecanico.getId());
        mecanico1.ifPresent(m -> {

            m.setId(mecanicoNuevo.getId());
            m.setNombre(mecanicoNuevo.getNombre());
            m.setSalario(mecanicoNuevo.getSalario());
            mecanicoRepository.save(m);

        });
        return mecanico1;

    }
    public void deleteMecanico(Mecanico mecanico) {
        mecanicoRepository.delete(mecanico);
    }
}