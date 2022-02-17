package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Servicio;
import com.taller2dam.taller.dao.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    Optional<Servicio> findByName(String name);
}
