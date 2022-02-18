package com.taller2dam.taller.repository;

import com.taller2dam.taller.dao.Usuario;
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
public class UsuarioRepositoryTest {
    private Usuario usuarioTest;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioTest = Usuario.builder()
                .id(10L)
                .dni("685933P")
                .nombre("Usuario Test")
                .administrador(false)
                .telefono(43439734)
                .build();
    }

    @Test
    @Order(1)
    public void save() {
        Usuario user = usuarioRepository.save(usuarioTest);

        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals(usuarioTest.getId(), user.getId()),
                () -> assertEquals(usuarioTest.getDni(), user.getDni()),
                () -> assertEquals(usuarioTest.getNombre(), user.getNombre()),
                () -> assertEquals(usuarioTest.getAdministrador(), user.getAdministrador()),
                () -> assertEquals(usuarioTest.getTelefono(), user.getTelefono())
        );
    }

    @Test
    @Order(2)
    public void getAllUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        assertAll(
                () -> assertTrue(usuarios.size() > 0),
                () -> assertEquals(usuarios.get(0).getId(), usuarioTest.getId())
        );
    }

    @Test
    @Order(3)
    public void getUsuarioById() {

        var usuario = usuarioRepository.findById(10L).get();

        assertAll(
                () -> assertNotNull(usuario),
                () -> assertEquals(usuarioTest.getDni(), usuario.getDni()),
                () -> assertEquals(usuarioTest.getNombre(), usuario.getNombre()),
                () -> assertEquals(usuarioTest.getAdministrador(), usuario.getAdministrador()),
                () -> assertEquals(usuarioTest.getTelefono(), usuario.getTelefono())
        );
    }

    @Test
    @Order(4)
    public void updateUsuario() {
        usuarioTest.setNombre("Nombre Modificado");
        var res = usuarioRepository.save(usuarioTest);

        assertAll(
                () -> assertNotNull(res),
                () -> assertEquals("Nombre Modificado", res.getNombre()),
                () -> assertEquals(usuarioTest.getDni(), res.getDni()),
                () -> assertEquals(usuarioTest.getAdministrador(), res.getAdministrador()),
                () -> assertEquals(usuarioTest.getTelefono(), res.getTelefono())
        );
    }

    @Test
    @Order(5)
    public void deleteUsuario() {
        Usuario user = usuarioRepository.save(usuarioTest);
        user = usuarioRepository.findById(user.getId()).get();
        usuarioRepository.delete(user);
        assertNull(usuarioRepository.findById(user.getId()).orElse(null));
    }

}