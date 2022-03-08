package com.taller2dam.taller.mapper;

import com.taller2dam.taller.dao.Mecanico;
import com.taller2dam.taller.dto.MecanicoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // Nos ahorramos el autowire
public class MecanicoMapper {
    private final ModelMapper modelMapper;

    //DTOS BASICOS COMPLETOS
    public MecanicoDTO toDTO(Mecanico mecanico) {
        return modelMapper.map(mecanico, MecanicoDTO.class);
    }
    public Mecanico fromDTO(MecanicoDTO mecanicoDTO) {
        return modelMapper.map(mecanicoDTO, Mecanico.class);
    }
    public List<MecanicoDTO> toDTO(List<Mecanico> mecanico) {
        return mecanico.stream().map(this::toDTO).collect(Collectors.toList());
    }
}