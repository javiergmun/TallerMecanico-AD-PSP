package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Vehiculo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class VehiculoRepositoryTest {

    private final Vehiculo vehiculoTestId = Vehiculo.builder()
                .id(1)
                .color("Rojo")
                .marca("Marca Test")
                .modelo("Modelo Test")
                .matricula("263562LLG")
                .build();


    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Test
    @Order(1)
    public void save() {
        Vehiculo vehiculo = vehiculoRepository.save(vehiculoTestId);

        assertAll(
                () -> assertNotNull(vehiculo),
                () -> assertEquals(vehiculoTestId.getId(), vehiculo.getId()),
                () -> assertEquals(vehiculoTestId.getColor(), vehiculo.getColor()),
                () -> assertEquals(vehiculoTestId.getMatricula(), vehiculo.getMatricula()),
                //() -> assertEquals(vehiculoTestId.getPropietario(), vehiculo.getPropietario()),
                () -> assertEquals(vehiculoTestId.getMarca(), vehiculo.getMarca()),
                () -> assertEquals(vehiculoTestId.getModelo(), vehiculo.getModelo())
        );
    }

    @Test
    @Order(2)
    public void getAllVehiculo() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        assertAll(
                () -> assertTrue(vehiculos.size() > 0),
                () -> assertEquals(vehiculos.get(0).getId(), vehiculoTestId.getId())
        );
    }

    @Test
    @Order(3)
    public void getVehiculoById() {
        var vehiculo = vehiculoRepository.save(vehiculoTestId);
        var vehiculoId = vehiculoRepository.findById(vehiculo.getId()).get();

        assertAll(
                () -> assertNotNull(vehiculoId),
                () -> assertEquals(vehiculo.getId(), vehiculoId.getId()),
                () -> assertEquals(vehiculo.getColor(), vehiculoId.getColor()),
                () -> assertEquals(vehiculo.getMatricula(), vehiculoId.getMatricula()),
                //() -> assertEquals(vehiculo.getPropietario(), vehiculoId.getPropietario()),
                () -> assertEquals(vehiculo.getMarca(), vehiculoId.getMarca()),
                () -> assertEquals(vehiculo.getModelo(), vehiculoId.getModelo())
        );
    }

    @Test
    @Order(4)
    public void updateVehiculo() {
        var vehi = vehiculoRepository.save(vehiculoTestId);
        vehi = vehiculoRepository.findById(vehi.getId()).get();
        vehi.setMatricula("12345678890");
        var res = vehiculoRepository.save(vehi);

        assertAll(
                () -> assertNotNull(res),
                () -> assertEquals(vehiculoTestId.getId(), res.getId()),
                () -> assertEquals(vehiculoTestId.getColor(), res.getColor()),
                () -> assertEquals("12345678890", res.getMatricula()),
                //() -> assertEquals(vehiculoTestId.getPropietario(), res.getPropietario()),
                () -> assertEquals(vehiculoTestId.getMarca(), res.getMarca()),
                () -> assertEquals(vehiculoTestId.getModelo(), res.getModelo())
        );
    }

    @Test
    @Order(5)
    public void deleteVehiculo() {
        Vehiculo vehiculo = vehiculoRepository.save(vehiculoTestId);
        vehiculo = vehiculoRepository.findById(vehiculo.getId()).get();
        vehiculoRepository.delete(vehiculo);
        assertNull(vehiculoRepository.findById(vehiculo.getId()).orElse(null));
    }

}