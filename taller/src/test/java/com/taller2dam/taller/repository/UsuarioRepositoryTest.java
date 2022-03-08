package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Usuario;
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
public class UsuarioRepositoryTest {

    private final Usuario usuarioTest = Usuario.builder()
            .id(1)
            .dni("685933P")
            .username("Usuario Test")
            //.administrador(false)
            .telefono("43439734")
            .build();
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Order(1)
    public void save() {
        Usuario user = usuarioRepository.save(usuarioTest);

        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals(usuarioTest.getId(), user.getId()),
                () -> assertEquals(usuarioTest.getDni(), user.getDni()),
                () -> assertEquals(usuarioTest.getUsername(), user.getUsername()),
                //() -> assertEquals(usuarioTest.getAdministrador(), user.getAdministrador()),
                () -> assertEquals(usuarioTest.getTelefono(), user.getTelefono())

        );
    }
    @Test
    @Order(2)
    public void getAllUsuario() {
        assertTrue(usuarioRepository.findAll().size() > 0);
    }

    @Test
    @Order(3)
    public void getUsuarioById() {
        var user = usuarioRepository.save(usuarioTest);
        var usuarioId = usuarioRepository.findById(user.getId()).get();

        assertAll(
                () -> assertNotNull(usuarioId),
                () -> assertEquals(user.getDni(), usuarioId.getDni()),
                () -> assertEquals(user.getUsername(), usuarioId.getUsername()),
               // () -> assertEquals(user.getAdministrador(), usuarioId.getAdministrador()),
                () -> assertEquals(user.getTelefono(), usuarioId.getTelefono())
        );
    }
    @Test
    @Order(4)
    public void updateUsuario() {
        var user = usuarioRepository.save(usuarioTest);
        user = usuarioRepository.findById(user.getId()).get();
        user.setUsername("Usuario de prueba modificado");

        var res = usuarioRepository.save(user);
        assertAll(
                () -> assertNotNull(res),
                () -> assertEquals("Usuario de prueba modificado", res.getUsername()),
                () -> assertEquals(usuarioTest.getDni(), res.getDni()),
               // () -> assertEquals(usuarioTest.getAdministrador(), res.getAdministrador()),
                () -> assertEquals(usuarioTest.getTelefono(), res.getTelefono())

        );
    }
    @Test
    @Order(5)
    public void deleteUsuario() {
        Usuario user = usuarioRepository.save(usuarioTest);
        user = usuarioRepository.findById(user.getId()).get();
        //Usuario user = usuarioRepository.findById(usuarioTest.getId()).get();
        usuarioRepository.delete(user);
        assertNull(usuarioRepository.findById(user.getId()).orElse(null));

    }

}