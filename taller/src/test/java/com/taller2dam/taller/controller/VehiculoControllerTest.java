package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dao.Vehiculo;
import com.taller2dam.taller.dto.VehiculoDTO;
import com.taller2dam.taller.mapper.VehiculoMapper;
import com.taller2dam.taller.repository.VehiculoRepository;
import com.taller2dam.taller.upload.StorageService;
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

@SpringBootTest
class VehiculoControllerTest {

    @MockBean
    private final VehiculoRepository vehiculosRepository;
    @MockBean
    private final StorageService storageService;
    @MockBean
    private final VehiculoMapper vehiculoMapper;
    private final Vehiculo vehiculo = Vehiculo.builder()
            .id(1L)
            .marca("AudiTest")
            .modelo("ModeloTest")
            .matricula("5566TES")
            .color("negro")
            .imagen("imagenTest")
            .bitmap("bitmap1")
            .build();

    @InjectMocks
    private VehiculoController vehiculosController;

    @Autowired
    VehiculoControllerTest(VehiculoRepository vehiculosRepository, StorageService storageService, VehiculoMapper vehiculoMapper) {
        this.vehiculosRepository = vehiculosRepository;
        this.storageService = storageService;
        this.vehiculoMapper = vehiculoMapper;
    }

    @Test
    void findAllTestMock() {
        var dto = VehiculoDTO.builder()
                .marca("AudiTest")
                .modelo("ModeloTest")
                .matricula("5566TES")
                .color("negro")
                .imagen("imagenTest")
                .bitmap("bitmap1")
                .build();

        Mockito.when(vehiculosRepository.findAll())
                .thenReturn(List.of(vehiculo));

        Mockito.when(vehiculoMapper.toDTO(List.of(vehiculo))).thenReturn(List.of(dto));

        var response = vehiculosController.findAll(
                java.util.Optional.empty(), java.util.Optional.empty()
        );
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.get(0).getMarca(), vehiculo.getMarca()),
                () -> assertEquals(res.get(0).getModelo(), vehiculo.getModelo()),
                () -> assertEquals(res.get(0).getMatricula(), vehiculo.getMatricula()),
                () -> assertEquals(res.get(0).getImagen(), vehiculo.getImagen()),
                () -> assertEquals(res.get(0).getBitmap(), vehiculo.getBitmap())
        );
    }

    @Test
    void findByIdTestMock() {
        var dto = VehiculoDTO.builder()
                .marca("AudiTest")
                .modelo("ModeloTest")
                .matricula("5566TES")
                .color("negro")
                .imagen("imagenTest")
                .bitmap("bitmap1")
                .build();

        Mockito.when(vehiculosRepository.findById(1L))
                .thenReturn(Optional.of(vehiculo));

        Mockito.when(vehiculoMapper.toDTO(vehiculo)).thenReturn(dto);

        var response = vehiculosController.findById(1L);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getMarca(), vehiculo.getMarca()),
                () -> assertEquals(res.getModelo(), vehiculo.getModelo()),
                () -> assertEquals(res.getMatricula(), vehiculo.getMatricula()),
                () -> assertEquals(res.getImagen(), vehiculo.getImagen()),
                () -> assertEquals(res.getBitmap(), vehiculo.getBitmap())
        );

        Mockito.verify(vehiculosRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(vehiculoMapper, Mockito.times(1)).toDTO(vehiculo);
    }
    @Test
    void updateTestMock() {
        var dto = VehiculoDTO.builder()
                .marca("AudiTest")
                .modelo("ModeloTest")
                .matricula("5566TES")
                .color("negro")
                .imagen("imagenTest")
                .bitmap("bitmap1")
                .build();

        Mockito.when(vehiculosRepository.findById(1L))
                .thenReturn(java.util.Optional.of(vehiculo));

        Mockito.when(vehiculosRepository.save(vehiculo))
                .thenReturn(vehiculo);

        Mockito.when(vehiculoMapper.toDTO(vehiculo)).thenReturn(dto);

        var response = vehiculosController.update(1L, vehiculo);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getMarca(), vehiculo.getMarca()),
                () -> assertEquals(res.getModelo(), vehiculo.getModelo()),
                () -> assertEquals(res.getMatricula(), vehiculo.getMatricula()),
                () -> assertEquals(res.getImagen(), vehiculo.getImagen()),
                () -> assertEquals(res.getBitmap(), vehiculo.getBitmap())
        );

        Mockito.verify(vehiculosRepository, Mockito.times(1))
                .findById(1L);
        Mockito.verify(vehiculosRepository, Mockito.times(1))
                .save(vehiculo);
        Mockito.verify(vehiculoMapper, Mockito.times(1))
                .toDTO(vehiculo);
    }
    @Test
    void deleteTestMock() {
        var dto = VehiculoDTO.builder()
                .marca("AudiTest")
                .modelo("ModeloTest")
                .matricula("5566TES")
                .color("negro")
                .imagen("imagenTest")
                .bitmap("bitmap1")
                .build();

        Mockito.when(vehiculosRepository.findById(1L))
                .thenReturn(java.util.Optional.of(vehiculo));

        Mockito.when(vehiculoMapper.toDTO(vehiculo)).thenReturn(dto);

        var response = vehiculosController.delete(1L);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getMarca(), vehiculo.getMarca()),
                () -> assertEquals(res.getModelo(), vehiculo.getModelo()),
                () -> assertEquals(res.getMatricula(), vehiculo.getMatricula()),
                () -> assertEquals(res.getImagen(), vehiculo.getImagen()),
                () -> assertEquals(res.getBitmap(), vehiculo.getBitmap())
        );

        Mockito.verify(vehiculosRepository, Mockito.times(1))
                .findById(1L);
        Mockito.verify(vehiculoMapper, Mockito.times(1))
                .toDTO(vehiculo);
    }
}