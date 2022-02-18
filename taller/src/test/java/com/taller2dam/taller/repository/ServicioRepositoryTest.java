package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Servicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ServicioRepositoryTest {
    private Servicio servicioTest;

    @Autowired
    private ServicioRepository servicioRepository;

    @BeforeEach
    void setUp() {
        servicioTest = Servicio.builder().
                id(1L).
                precio(70.0).
                tipo("Chapa y pintura").
                tiempo(50.0).
                build();
    }

    @Test
    @Order(1)
    public void save() {
        Servicio servicio = servicioRepository.save(servicioTest);

        assertAll(
                () -> assertNotNull(servicio),
                () -> assertEquals(servicioTest.getId(), servicio.getId()),
                () -> assertEquals(servicioTest.getPrecio(), servicio.getPrecio()),
                () -> assertEquals(servicioTest.getTipo(), servicio.getTipo()),
                () -> assertEquals(servicioTest.getTiempo(), servicio.getTiempo())
        );
    }

    @Test
    @Order(2)
    public void getAllServicio() {
        List<Servicio> servicios = servicioRepository.findAll();
        assertAll(
                () -> assertTrue(servicios.size() > 0),
                () -> assertEquals(servicios.get(0).getId(), servicioTest.getId())
        );
    }

    @Test
    @Order(3)
    public void getServicioById() {
        var serv = servicioRepository.save(servicioTest);
        var servicioId = servicioRepository.findById(serv.getId()).get();

        assertAll(
                () -> assertNotNull(servicioId),
                () -> assertEquals(serv.getPrecio(), servicioId.getPrecio()),
                () -> assertEquals(serv.getTipo(), servicioId.getTipo()),
                () -> assertEquals(serv.getTiempo(), servicioId.getTiempo())

        );
    }

    @Test
    @Order(4)
    public void updateServicio() {
        var serv = servicioRepository.save(servicioTest);
        serv = servicioRepository.findById(serv.getId()).get();
        serv.setTipo("Cambio de ruedas");

        var res = servicioRepository.save(serv);
        assertAll(
                () -> assertNotNull(res),
                () -> assertEquals("Cambio de ruedas", res.getTipo()),
                () -> assertEquals(servicioTest.getPrecio(), res.getPrecio()),
                () -> assertEquals(servicioTest.getTiempo(), res.getTiempo())

        );
    }

    @Test
    @Order(5)
    public void deleteServicio() {
        Assertions.assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
            servicioRepository.delete(servicioTest);
        });
    }

}