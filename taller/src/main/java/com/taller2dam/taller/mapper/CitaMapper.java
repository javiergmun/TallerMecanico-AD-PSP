package com.taller2dam.taller.mapper;


import com.taller2dam.taller.dao.Cita;
import com.taller2dam.taller.dto.CitaDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CitaMapper {

    private final ModelMapper modelMapper;

    public CitaDTO toDTO(Cita cita) {
        return modelMapper.map(cita, CitaDTO.class);

    }

    public Cita fromDTO(CitaDTO citaDTO) {
        return modelMapper.map(citaDTO, Cita.class);

    }

    public List<CitaDTO> toDTO(List<Cita> cita) {
        return cita.stream().map(this::toDTO).collect(Collectors.toList());
    }

}