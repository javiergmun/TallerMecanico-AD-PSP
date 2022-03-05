package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Mecanico;
import com.taller2dam.taller.dto.MecanicoDTO;
import com.taller2dam.taller.mapper.MecanicoMapper;
import com.taller2dam.taller.repository.MecanicoRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MecanicoControllerTest {


    /*
    @Mock
    private static MecanicoRepository mecanicoRepository;

    @InjectMocks
    private static MecanicoController mecanicoController;


    private final Mecanico mecanico = Mecanico.builder()
            .id(1L)
            .nombre("MecanicoTest1")
            .salario(2000.0)
            .build();
    @Mock
    private MecanicoMapper mecanicoMapper;


    @Test
    @Order(1)
    void findAll() {
        var dto = MecanicoDTO.builder()
                .nombre(mecanico.getNombre());

    }

    @Test
    void findById() {
    //given

    //

    //

    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void nuevoMecanico() {
    }

    @Test
    void listado() {
    }

 */
}