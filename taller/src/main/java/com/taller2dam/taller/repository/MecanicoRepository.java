package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Mecanico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, Long> {
   // Page<Mecanico> findByNombreAndPrecioPageable(String nombre, double salario, Pageable pageable);
}