package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dto.UsuarioDTO;
import com.taller2dam.taller.errores.UsuarioNotFoundException;
import com.taller2dam.taller.mapper.UsuarioMapper;
import com.taller2dam.taller.repository.UsuarioRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UsuarioControllerTest {
    // Mis mocks
    @MockBean
    private final UsuarioRepository usuariosRepository;
    @MockBean
    private final StorageService storageService;
    @MockBean
    private final UsuarioMapper usuarioMapper;
    private final Usuario usuario = Usuario.builder()
            .id(1L)
            .correo("javi@test")
            .dni("29837390J")
            .username("javi")
            .password("1223")
            .imagen("imagenTest")
            .telefono("615462360")
            .direccion("Calle Monegros,9,28914,Leganes")
            .bitmap("bitmapTest")
            .build();

    @InjectMocks
    private UsuarioController usuariosController;

    @Autowired
    UsuarioControllerTest(UsuarioRepository usuariosRepository, StorageService storageService, UsuarioMapper usuarioMapper) {
        this.usuariosRepository = usuariosRepository;
        this.storageService = storageService;
        this.usuarioMapper = usuarioMapper;
    }

    @Test
    void findByIdException() {
        Mockito.when(usuariosRepository.findById(1L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(UsuarioNotFoundException.class, () -> {
            usuariosController.findById(1L);
        });

        assertTrue(ex.getMessage().contains("No se encuentra ningún usuario con la ID:"));

        Mockito.verify(usuariosRepository, Mockito.times(1))
                .findById(1L);
    }

    @Test
    void findAllTestMock() {
        var dto = UsuarioDTO.builder()
                .correo("javi@test")
                .dni("29837390J")
                .username("javi")
                .password("1223")
                .imagen("imagenTest")
                .telefono("615462360")
                .direccion("Calle Monegros,9,28914,Leganes")
                .bitmap("bitmapTest")
                .build();

        Mockito.when(usuariosRepository.findAll())
                .thenReturn(List.of(usuario));

        Mockito.when(usuarioMapper.toDTO(List.of(usuario))).thenReturn(List.of(dto));

        var response = usuariosController.findAll(
                Optional.empty(), Optional.empty()
        );
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.get(0).getCorreo(), usuario.getCorreo()),
                () -> assertEquals(res.get(0).getDni(), usuario.getDni()),
                () -> assertEquals(res.get(0).getUsername(), usuario.getUsername()),
                () -> assertEquals(res.get(0).getPassword(), usuario.getPassword()),
                () -> assertEquals(res.get(0).getImagen(), usuario.getImagen()),
                () -> assertEquals(res.get(0).getTelefono(), usuario.getTelefono()),
                () -> assertEquals(res.get(0).getDireccion(), usuario.getDireccion()),
                () -> assertEquals(res.get(0).getBitmap(), usuario.getBitmap())
        );
    }
    @Test
    void deleteTestMock() {
        var dto = UsuarioDTO.builder()
                .correo("javi@test")
                .dni("29837390J")
                .username("javi")
                .password("1223")
                .imagen("imagenTest")
                .telefono("615462360")
                .direccion("Calle Monegros,9,28914,Leganes")
                .bitmap("bitmapTest")
                .build();

        Mockito.when(usuariosRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        Mockito.when(usuarioMapper.toDTO(usuario)).thenReturn(dto);

        var response = usuariosController.delete(1L);
        var res = response.getBody();

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode().value()),
                () -> assertEquals(res.getCorreo(), usuario.getCorreo()),
                () -> assertEquals(res.getDni(), usuario.getDni()),
                () -> assertEquals(res.getUsername(), usuario.getUsername()),
                () -> assertEquals(res.getPassword(), usuario.getPassword()),
                () -> assertEquals(res.getImagen(), usuario.getImagen()),
                () -> assertEquals(res.getTelefono(), usuario.getTelefono()),
                () -> assertEquals(res.getDireccion(), usuario.getDireccion()),
                () -> assertEquals(res.getBitmap(), usuario.getBitmap())
        );

        Mockito.verify(usuariosRepository, Mockito.times(1))
                .findById(1L);
        Mockito.verify(usuarioMapper, Mockito.times(1))
                .toDTO(usuario);
    }
}