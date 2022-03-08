package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Cita;
import com.taller2dam.taller.dao.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    Optional<List<Cita>> findAllByUsuario(Usuario usuario);
}
