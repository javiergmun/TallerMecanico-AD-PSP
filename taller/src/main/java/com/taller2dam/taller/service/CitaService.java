package com.taller2dam.taller.service;

import com.taller2dam.taller.dao.Cita;
import com.taller2dam.taller.repository.CitaRepository;
import com.taller2dam.taller.repository.CitaRepository;

import java.util.List;
import java.util.Optional;

public class CitaService {
    CitaRepository citaRepository;


    //CONSULTAS CRUD BÁSICAS

    public List<Cita> findAllCitas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> findCitaById(Long citaId) {
        return citaRepository.findById(citaId);
    }

    public Cita saveCita(Cita cita) {
        return citaRepository.save(cita);
    }

    //A falta de probar su funcionamiento
    public Optional<Cita> updateCita(Cita cita, Cita citaNueva) {
        Optional<Cita>cita1= findCitaById(cita.getId());
        cita1.ifPresent(c -> {

            c.setId(citaNueva.getId());
            //c.setPrecio(citaNueva.getPrecio());
            c.setFecha(citaNueva.getFecha());
            c.setUsuario(citaNueva.getUsuario());
            c.setMecanico(citaNueva.getMecanico());
            c.setServicio(citaNueva.getServicio());
            citaRepository.save(c);

        });
        return cita1;

    }

    public void deleteCita(Cita cita) {
        citaRepository.delete(cita);
    }



}