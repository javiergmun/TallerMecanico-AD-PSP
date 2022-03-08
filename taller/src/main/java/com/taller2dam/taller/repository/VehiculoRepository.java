package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    //List<Vehiculo> findByNombreContainsIgnoreCase(String nombre);
    //Page<Vehiculo> findByNombreContainsIgnoreCase(String nombre, Pageable pageable);
}
