package com.taller2dam.taller.mapper;

import com.taller2dam.taller.dao.Servicio;
import com.taller2dam.taller.dto.ServicioDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // Nos ahorramos el autowire
public class ServicioMapper {
    private final ModelMapper modelMapper;

    public ServicioDTO toDTO(Servicio servicio) {
        return modelMapper.map(servicio, ServicioDTO.class);
    }

    public Servicio fromDTO(ServicioDTO servicioDTO) {
        return modelMapper.map(servicioDTO, Servicio.class);
    }

    public List<ServicioDTO> toDTO(List<Servicio> servicio) {
        return servicio.stream().map(this::toDTO).collect(Collectors.toList());
    }
}