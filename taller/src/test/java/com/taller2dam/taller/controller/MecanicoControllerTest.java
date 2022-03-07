package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Mecanico;
import com.taller2dam.taller.dto.MecanicoDTO;
import com.taller2dam.taller.mapper.MecanicoMapper;
import com.taller2dam.taller.repository.MecanicoRepository;
import com.taller2dam.taller.upload.StorageService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MecanicoControllerTest {

    @MockBean
    private final MecanicoRepository mecanicosRepository;
    @MockBean
    private final StorageService storageService;
    @MockBean
    private final MecanicoMapper mecanicoMapper;
    private final Mecanico mecanico = Mecanico.builder()
            .id(1L)
            .nombre("Adam")
            .salario(100.0)
            .build();

    @InjectMocks
    private MecanicoController mecanicosController;

    @Autowired
    MecanicoControllerTest(MecanicoRepository mecanicosRepository, StorageService storageService, MecanicoMapper mecanicoMapper) {
        this.mecanicosRepository = mecanicosRepository;
        this.storageService = storageService;
        this.mecanicoMapper = mecanicoMapper;
    }

    @Test
    void findAllTestMock() {
        var dto = MecanicoDTO.builder()
                .nombre(mecanico.getNombre())
                .salario(mecanico.getSalario())
                .build();

        Mockito.when(mecanicosRepository.findAll())
                .thenReturn(List.of(mecanico));

        Mockito.when(mecanicoMapper.toDTO(List.of(mecanico))).thenReturn(List.of(dto));

        var response = mecanicosController.findAll(
                java.util.Optional.empty(), java.util.Optional.empty()
        );
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.get(0).getNombre(), mecanico.getNombre()),
                () -> assertEquals(res.get(0).getSalario(), mecanico.getSalario())
        );
    }

    @Test
    void findByIdTestMock() {
        var dto = MecanicoDTO.builder()
                .nombre(mecanico.getNombre())
                .salario(mecanico.getSalario())
                .build();

        Mockito.when(mecanicosRepository.findById(1L))
                .thenReturn(Optional.of(mecanico));

        Mockito.when(mecanicoMapper.toDTO(mecanico)).thenReturn(dto);

        var response = mecanicosController.findById(1L);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getNombre(), mecanico.getNombre()),
                () -> assertEquals(res.getSalario(), mecanico.getSalario())
        );

        Mockito.verify(mecanicosRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(mecanicoMapper, Mockito.times(1)).toDTO(mecanico);
    }
    @Test
    void updateTestMock() {
        var dto = MecanicoDTO.builder()
                .nombre(mecanico.getNombre())
                .salario(mecanico.getSalario())
                .build();

        Mockito.when(mecanicosRepository.findById(1L))
                .thenReturn(java.util.Optional.of(mecanico));

        Mockito.when(mecanicosRepository.save(mecanico))
                .thenReturn(mecanico);

        Mockito.when(mecanicoMapper.toDTO(mecanico)).thenReturn(dto);

        var response = mecanicosController.update(1L, mecanico);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getNombre(), mecanico.getNombre()),
                () -> assertEquals(res.getSalario(), mecanico.getSalario())
        );

        Mockito.verify(mecanicosRepository, Mockito.times(1))
                .findById(1L);
        Mockito.verify(mecanicosRepository, Mockito.times(1))
                .save(mecanico);
        Mockito.verify(mecanicoMapper, Mockito.times(1))
                .toDTO(mecanico);
    }
    @Test
    void deleteTestMock() {
        var dto = MecanicoDTO.builder()
                .nombre(mecanico.getNombre())
                .salario(mecanico.getSalario())
                .build();

        Mockito.when(mecanicosRepository.findById(1L))
                .thenReturn(java.util.Optional.of(mecanico));

        Mockito.when(mecanicoMapper.toDTO(mecanico)).thenReturn(dto);

        var response = mecanicosController.delete(1L);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getNombre(), mecanico.getNombre()),
                () -> assertEquals(res.getSalario(), mecanico.getSalario())
        );

        Mockito.verify(mecanicosRepository, Mockito.times(1))
                .findById(1L);
        Mockito.verify(mecanicoMapper, Mockito.times(1))
                .toDTO(mecanico);
    }

}