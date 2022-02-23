package com.taller2dam.taller.service;

import com.taller2dam.taller.dao.Vehiculo;
import com.taller2dam.taller.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoService {
    VehiculoRepository vehiculoRepository;


    //CONSULTAS CRUD BÁSICAS

    public List<Vehiculo> findAllVehiculos() {return vehiculoRepository.findAll();}

    public Optional<Vehiculo> findVehiculoById(Long vehiculoId) {
        return vehiculoRepository.findById(vehiculoId);
    }

    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }


    //A falta de probar su funcionamiento
    public Optional<Vehiculo> updateVehiculo(Vehiculo vehiculo, Vehiculo vehiculoNuevo) {
        Optional<Vehiculo>vehiculo1= findVehiculoById(vehiculo.getId());
        vehiculo1.ifPresent(v -> {

            v.setId(vehiculoNuevo.getId());
            v.setMarca(vehiculoNuevo.getMarca());
            v.setModelo(vehiculoNuevo.getModelo());
            v.setMatricula(vehiculoNuevo.getMatricula());
            v.setColor(vehiculoNuevo.getColor());
            //v.setPropietario(vehiculoNuevo.getPropietario());
            vehiculoRepository.save(v);

        });
        return vehiculo1;

    }

    public void deleteVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.delete(vehiculo);
    }


}
