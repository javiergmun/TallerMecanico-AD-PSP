package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Cita;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CitaRepositoryTest {

    private final Cita citaTest = Cita.builder().
            id(1L).
            precio(70.0).
            build();

    @Autowired
    private CitaRepository citaRepository;

    @Test
    @Order(1)
    public void save() {
        Cita cita = citaRepository.save(citaTest);

        assertAll(
                () -> assertNotNull(cita),
                () -> assertEquals(citaTest.getId(), cita.getId()),
                () -> assertEquals(citaTest.getPrecio(), cita.getPrecio())
        );
    }

    @Test
    @Order(2)
    public void getAllCitas() {
        //Solo para el administrador
        assertTrue(citaRepository.findAll().size() > 0);
    }

    @Test
    @Order(3)
    public void getCitaById() {
        var cit = citaRepository.save(citaTest);
        var citaId = citaRepository.findById(cit.getId()).get();

        assertAll(
                () -> assertNotNull(citaId),
                () -> assertEquals(cit.getPrecio(), citaId.getPrecio())
        );
    }
    @Test
    @Order(4)
    public void updateCita() {
        var cit = citaRepository.save(citaTest);
        cit = citaRepository.findById(cit.getId()).get();
        cit.setPrecio(80.0);

        var cita = citaRepository.save(cit);
        assertAll(
                () -> assertNotNull(cita),
                () -> assertEquals(80.0, cita.getPrecio())
        );
    }
    @Test
    @Order(5)
    public void deleteCita() {
        Cita cita = citaRepository.save(citaTest);
        cita = citaRepository.findById(cita.getId()).get();
        citaRepository.delete(cita);
        assertNull(citaRepository.findById(cita.getId()).orElse(null));
    }

}