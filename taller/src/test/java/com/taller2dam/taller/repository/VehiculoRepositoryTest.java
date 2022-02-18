package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Usuario;
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
    private Vehiculo VehiculoTest;
    private Usuario usuarioTest;

    @Autowired
    private VehiculoRepository VehiculoRepository;

    @BeforeEach
    void setUp() {
        usuarioTest = Usuario.builder()
                .id(1L)
                .dni("685933P")
                .nombre("Usuario Test")
                .administrador(false)
                .telefono(43439734)
                .build();

        VehiculoTest = Vehiculo.builder()
                .id(10L)
                .color("Rojo")
                .marca("Marca Test")
                .modelo("Modelo Test")
                .matricula("263562LLG")
                .propietario(usuarioTest)
                .build();

    }

    @Test
    @Order(1)
    public void save() {
        Vehiculo vehiculo = VehiculoRepository.save(VehiculoTest);

        assertAll(
                () -> assertNotNull(vehiculo),
                () -> assertEquals(VehiculoTest.getId(), vehiculo.getId()),
                () -> assertEquals(VehiculoTest.getColor(), vehiculo.getColor()),
                () -> assertEquals(VehiculoTest.getMatricula(), vehiculo.getMatricula()),
                () -> assertEquals(VehiculoTest.getPropietario(), vehiculo.getPropietario()),
                () -> assertEquals(VehiculoTest.getMarca(), vehiculo.getMarca()),
                () -> assertEquals(VehiculoTest.getModelo(), vehiculo.getModelo())
        );
    }

    @Test
    @Order(2)
    public void getAllVehiculo() {
        List<Vehiculo> vehiculos = VehiculoRepository.findAll();
        assertAll(
                () -> assertTrue(vehiculos.size() > 0),
                () -> assertEquals(vehiculos.get(0).getId(), VehiculoTest.getId())
        );
    }

    @Test
    @Order(3)
    public void getVehiculoById() {
        var vehiculo = VehiculoRepository.save(VehiculoTest);
        var VehiculoId = VehiculoRepository.findById(vehiculo.getId()).get();

        assertAll(
                () -> assertNotNull(VehiculoId),
                () -> assertEquals(vehiculo.getDni(), VehiculoId.getDni()),
                () -> assertEquals(vehiculo.getNombre(), VehiculoId.getNombre()),
                () -> assertEquals(vehiculo.getAdministrador(), VehiculoId.getAdministrador()),
                () -> assertEquals(vehiculo.getTelefono(), VehiculoId.getTelefono())
        );
    }

    @Test
    @Order(4)
    public void updateVehiculo() {
        var vehiculo = VehiculoRepository.save(VehiculoTest);
        vehiculo = VehiculoRepository.findById(vehiculo.getId()).get();
        vehiculo.setNombre("Vehiculo de prueba modificado");

        var res = VehiculoRepository.save(vehiculo);
        assertAll(
                () -> assertNotNull(res),
                () -> assertEquals("Vehiculo de prueba modificado", res.getNombre()),
                () -> assertEquals(VehiculoTest.getDni(), res.getDni()),
                () -> assertEquals(VehiculoTest.getAdministrador(), res.getAdministrador()),
                () -> assertEquals(VehiculoTest.getTelefono(), res.getTelefono())
        );
    }

    @Test
    @Order(5)
    public void deleteVehiculo() {
        Vehiculo vehiculo = VehiculoRepository.save(VehiculoTest);
        vehiculo = VehiculoRepository.findById(vehiculo.getId()).get();
        VehiculoRepository.delete(vehiculo);
        assertNull(VehiculoRepository.findById(vehiculo.getId()).orElse(null));
    }

}
