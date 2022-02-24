package com.taller2dam.taller.service;

import com.taller2dam.taller.dao.Servicio;
import com.taller2dam.taller.repository.ServicioRepository;
import java.util.List;
import java.util.Optional;

public class ServicioService {
    ServicioRepository servicioRepository;

    public List<Servicio> findAllServicios() {return servicioRepository.findAll();}
    public Optional<Servicio> findServicioById(Long servicioId) {
        return servicioRepository.findById(servicioId);
    }
    public Servicio saveServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    //A falta de probar su funcionamiento
    public Optional<Servicio> updateServicio(Servicio servicio, Servicio servicioNuevo) {
        Optional<Servicio> servicio1 = findServicioById(servicio.getId());
        servicio1.ifPresent(s -> {

            s.setId(servicioNuevo.getId());
            s.setPrecio(servicioNuevo.getPrecio());
            s.setTipo(servicioNuevo.getTipo());
            s.setTiempo(servicioNuevo.getTiempo());
            s.setFecha_inicio(servicioNuevo.getFecha_inicio());
            s.setFecha_fin(servicioNuevo.getFecha_fin());
            servicioRepository.save(s);

        });
        return servicio1;
    }

    public void deleteServicio(Servicio servicio) {
        servicioRepository.delete(servicio);
    }
}