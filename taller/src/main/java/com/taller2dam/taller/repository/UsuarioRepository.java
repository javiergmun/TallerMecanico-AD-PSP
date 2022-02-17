package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //Optional<Usuario> findByName(String name);
}
