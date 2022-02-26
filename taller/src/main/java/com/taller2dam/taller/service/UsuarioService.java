package com.taller2dam.taller.service;

import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    UsuarioRepository usuarioRepository;


    //CONSULTAS CRUD BÁSICAS

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findUsuarioById(Long usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    //public Optional<Usuario> findByUsername (String username){
    //   return usuarioRepository.findByUsername(username);
    // }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //A falta de probar su funcionamiento
    public Optional<Usuario> updateUsuario(Usuario usuario, Usuario usuarioNuevo) {
        Optional<Usuario> usuario1 = findUsuarioById(usuario.getId());
        usuario1.ifPresent(u -> {

            u.setDni(usuarioNuevo.getDni());
            u.setId(usuarioNuevo.getId());
            u.setNombre(usuarioNuevo.getNombre());
            u.setAdministrador(usuarioNuevo.getAdministrador());
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