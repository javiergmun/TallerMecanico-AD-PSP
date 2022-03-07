package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Servicio;
import com.taller2dam.taller.dto.ServicioDTO;
import com.taller2dam.taller.mapper.ServicioMapper;
import com.taller2dam.taller.repository.ServicioRepository;
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
class ServicioControllerTest {

    // Mis mocks
    @MockBean
    private final ServicioRepository serviciosRepository;
    @MockBean
    private final StorageService storageService;
    @MockBean
    private final ServicioMapper servicioMapper;
    private final Servicio servicio = Servicio.builder()
            .id(1L)
            .precio(40.0)
            .tipo("Tintado de Ventanas")
            .tiempo(30.0)
            .imagen("imagenTest")
            .bitmap("bitmap1")
            .descripcion("descripcion de test")
            .build();

    @InjectMocks
    private ServicioController serviciosController;

    @Autowired
    ServicioControllerTest(ServicioRepository serviciosRepository, StorageService storageService, ServicioMapper servicioMapper) {
        this.serviciosRepository = serviciosRepository;
        this.storageService = storageService;
        this.servicioMapper = servicioMapper;
    }

    @Test
    void findAllTestMock() {
        var dto = ServicioDTO.builder()
                .precio(40.0)
                .tipo("Tintado de Ventanas")
                .tiempo(30.0)
                .imagen("imagenTest")
                .bitmap("bitmap1")
                .descripcion("descripcion de test")
                .build();

        Mockito.when(serviciosRepository.findAll())
                .thenReturn(List.of(servicio));

        Mockito.when(servicioMapper.toDTO(List.of(servicio))).thenReturn(List.of(dto));

        var response = serviciosController.findAll(
                java.util.Optional.empty(), java.util.Optional.empty()
        );
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.get(0).getPrecio(), servicio.getPrecio()),
                () -> assertEquals(res.get(0).getTipo(), servicio.getTipo()),
                () -> assertEquals(res.get(0).getTiempo(), servicio.getTiempo()),
                () -> assertEquals(res.get(0).getImagen(), servicio.getImagen()),
                () -> assertEquals(res.get(0).getBitmap(), servicio.getBitmap()),
                () -> assertEquals(res.get(0).getDescripcion(), servicio.getDescripcion())
        );
    }

    @Test
    void findByIdTestMock() {
        var dto = ServicioDTO.builder()
                .precio(40.0)
                .tipo("Tintado de Ventanas")
                .tiempo(30.0)
                .imagen("imagenTest")
                .bitmap("bitmap1")
                .descripcion("descripcion de test")
                .build();

        Mockito.when(serviciosRepository.findById(1L))
                .thenReturn(Optional.of(servicio));

        Mockito.when(servicioMapper.toDTO(servicio)).thenReturn(dto);

        var response = serviciosController.findById(1L);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getPrecio(), servicio.getPrecio()),
                () -> assertEquals(res.getTipo(), servicio.getTipo()),
                () -> assertEquals(res.getTiempo(), servicio.getTiempo()),
                () -> assertEquals(res.getImagen(), servicio.getImagen()),
                () -> assertEquals(res.getBitmap(), servicio.getBitmap()),
                () -> assertEquals(res.getDescripcion(), servicio.getDescripcion())
        );

        Mockito.verify(serviciosRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(servicioMapper, Mockito.times(1)).toDTO(servicio);
    }
    @Test
    void updateTestMock() {
        var dto = ServicioDTO.builder()
                .precio(40.0)
                .tipo("Tintado de Ventanas")
                .tiempo(30.0)
                .imagen("imagenTest")
                .bitmap("bitmap1")
                .descripcion("descripcion de test")
                .build();

        Mockito.when(serviciosRepository.findById(1L))
                .thenReturn(java.util.Optional.of(servicio));

        Mockito.when(serviciosRepository.save(servicio))
                .thenReturn(servicio);

        Mockito.when(servicioMapper.toDTO(servicio)).thenReturn(dto);

        var response = serviciosController.update(1L, servicio);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getPrecio(), servicio.getPrecio()),
                () -> assertEquals(res.getTipo(), servicio.getTipo()),
                () -> assertEquals(res.getTiempo(), servicio.getTiempo()),
                () -> assertEquals(res.getImagen(), servicio.getImagen()),
                () -> assertEquals(res.getBitmap(), servicio.getBitmap()),
                () -> assertEquals(res.getDescripcion(), servicio.getDescripcion())
        );

        Mockito.verify(serviciosRepository, Mockito.times(1))
                .findById(1L);
        Mockito.verify(serviciosRepository, Mockito.times(1))
                .save(servicio);
        Mockito.verify(servicioMapper, Mockito.times(1))
                .toDTO(servicio);
    }
    @Test
    void deleteTestMock() {
        var dto = ServicioDTO.builder()
                .precio(40.0)
                .tipo("Tintado de Ventanas")
                .tiempo(30.0)
                .imagen("imagenTest")
                .bitmap("bitmap1")
                .descripcion("descripcion de test")
                .build();

        Mockito.when(serviciosRepository.findById(1L))
                .thenReturn(java.util.Optional.of(servicio));

        Mockito.when(servicioMapper.toDTO(servicio)).thenReturn(dto);

        var response = serviciosController.delete(1L);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getPrecio(), servicio.getPrecio()),
                () -> assertEquals(res.getTipo(), servicio.getTipo()),
                () -> assertEquals(res.getTiempo(), servicio.getTiempo()),
                () -> assertEquals(res.getImagen(), servicio.getImagen()),
                () -> assertEquals(res.getBitmap(), servicio.getBitmap()),
                () -> assertEquals(res.getDescripcion(), servicio.getDescripcion())
        );

        Mockito.verify(serviciosRepository, Mockito.times(1))
                .findById(1L);
        Mockito.verify(servicioMapper, Mockito.times(1))
                .toDTO(servicio);
    }
}