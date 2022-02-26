package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //Optional<Usuario> findByUsername(String username);
}
