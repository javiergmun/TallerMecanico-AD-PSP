package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
