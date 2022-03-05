package com.taller2dam.taller.service;

import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dao.users.UserRole;
import com.taller2dam.taller.dto.CreateUserDTO;
import com.taller2dam.taller.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    //CONSULTAS CRUD BÁSICAS

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findUsuarioById(Long usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }


    //Creación de usuario con seguridad
    public Usuario saveUsuario(CreateUserDTO newUsuario) throws Exception {

        //if(newUsuario.getPassword().contentEquals(newUsuario.getPassword2())){
        Usuario usuario = Usuario.builder()
                    .username(newUsuario.getUsername())
                    .password(passwordEncoder.encode(newUsuario.getPassword()))
                    .dni(newUsuario.getDni())
                    .telefono(newUsuario.getTelefono())
                    .correo(newUsuario.getCorreo())
                    //.direccion(newUsuario.getDireccion())
                    .roles(Stream.of(UserRole.USER).collect(Collectors.toSet()))  //Con Java 9 se puede hacer como Set.of(UserRole.USER)
                    .build();

            return usuarioRepository.save(usuario);
       // }else{
       //     throw new Exception();
       //     //Lanzada en caso de que no coincidan las contraseñas. TODO: Se podrían crear errores personalizados
       // }
    }

    //A falta de probar su funcionamiento
    public Optional<Usuario> updateUsuario(Usuario usuario, Usuario usuarioNuevo) {
        Optional<Usuario> usuario1 = findUsuarioById(usuario.getId());
        usuario1.ifPresent(u -> {

            u.setDni(usuarioNuevo.getDni());
            u.setId(usuarioNuevo.getId());
            u.setUsername(usuarioNuevo.getUsername());
           // u.setAdministrador(usuarioNuevo.getAdministrador());
            u.setTelefono(usuarioNuevo.getTelefono());
            u.setDireccion(usuarioNuevo.getDireccion());
            u.setCorreo(usuarioNuevo.getCorreo());
            u.setPassword(usuarioNuevo.getPassword());
            u.setVehiculos(usuarioNuevo.getVehiculos());
            u.setLogin(usuarioNuevo.getLogin());

            usuarioRepository.save(u);

        });
        return usuario1;
    }

    public void deleteUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

}