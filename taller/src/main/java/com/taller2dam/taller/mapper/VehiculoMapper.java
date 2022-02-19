package com.taller2dam.taller.mapper;

import com.taller2dam.taller.dao.Vehiculo;
import com.taller2dam.taller.dto.VehiculoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VehiculoMapper {
    private final ModelMapper modelMapper;

    public VehiculoDTO toDTO(Vehiculo vehiculo) {
        return modelMapper.map(vehiculo, VehiculoDTO.class);

    }

    public Vehiculo fromDTO(VehiculoDTO vehiculoDTO) {
        return modelMapper.map(vehiculoDTO, Vehiculo.class);
    }

    public List<VehiculoDTO> toDTO(List<Vehiculo> vehiculo) {
        return vehiculo.stream().map(this::toDTO).collect(Collectors.toList());
    }
}