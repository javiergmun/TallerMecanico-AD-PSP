package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Cita;
import com.taller2dam.taller.dto.CitaDTO;
import com.taller2dam.taller.mapper.CitaMapper;
import com.taller2dam.taller.repository.CitaRepository;
import com.taller2dam.taller.upload.StorageService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CitaControllerTest {
    // Mis mocks
    @MockBean
    private final CitaRepository citasRepository;
    @MockBean
    private final StorageService storageService;
    @MockBean
    private final CitaMapper citaMapper;
    private final Cita cita = Cita.builder()
            .id(1L)
            .fecha("2022-10-10T11:00")
            .build();

    @InjectMocks
    private CitaController citasController;

    @Autowired
    CitaControllerTest(CitaRepository citasRepository, StorageService storageService, CitaMapper citaMapper) {
        this.citasRepository = citasRepository;
        this.storageService = storageService;
        this.citaMapper = citaMapper;
    }

    @Test
    void findAllTestMock() {
        var dto = CitaDTO.builder()
                .fecha("2022-10-10T11:00")
                .build();

        Mockito.when(citasRepository.findAll())
                .thenReturn(List.of(cita));

        Mockito.when(citaMapper.toDTO(List.of(cita))).thenReturn(List.of(dto));

        var response = citasController.findAll(
                java.util.Optional.empty(), java.util.Optional.empty()
        );
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.get(0).getFecha(), cita.getFecha())
        );
    }

    @Test
    void findByIdTestMock() {
        var dto = CitaDTO.builder()
                .fecha("2022-10-10T11:00")
                .build();

        Mockito.when(citasRepository.findById(1L))
                .thenReturn(Optional.of(cita));

        Mockito.when(citaMapper.toDTO(cita)).thenReturn(dto);

        var response = citasController.findById(1L);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getFecha(), cita.getFecha())
        );

        Mockito.verify(citasRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(citaMapper, Mockito.times(1)).toDTO(cita);
    }
    @Test
    void updateTestMock() {
        var dto = CitaDTO.builder()
                .fecha("2022-10-10T11:00")
                .build();

        Mockito.when(citasRepository.findById(1L))
                .thenReturn(java.util.Optional.of(cita));

        Mockito.when(citasRepository.save(cita))
                .thenReturn(cita);

        Mockito.when(citaMapper.toDTO(cita)).thenReturn(dto);

        var response = citasController.update(1L, cita);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getFecha(), cita.getFecha())
        );

        Mockito.verify(citasRepository, Mockito.times(1))
                .findById(1L);
        Mockito.verify(citasRepository, Mockito.times(1))
                .save(cita);
        Mockito.verify(citaMapper, Mockito.times(1))
                .toDTO(cita);
    }
    @Test
    void deleteTestMock() {
        var dto = CitaDTO.builder()
                .fecha("2022-10-10T11:00")
                .build();

        Mockito.when(citasRepository.findById(1L))
                .thenReturn(java.util.Optional.of(cita));

        Mockito.when(citaMapper.toDTO(cita)).thenReturn(dto);

        var response = citasController.delete(1L);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getFecha(), cita.getFecha())
        );

        Mockito.verify(citasRepository, Mockito.times(1))
                .findById(1L);
        Mockito.verify(citaMapper, Mockito.times(1))
                .toDTO(cita);
    }
}