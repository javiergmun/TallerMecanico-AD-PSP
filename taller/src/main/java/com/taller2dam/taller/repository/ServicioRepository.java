package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    //Optional<Servicio> findByName(String name);
}
