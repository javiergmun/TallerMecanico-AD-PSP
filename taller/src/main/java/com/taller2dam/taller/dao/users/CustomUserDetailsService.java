package com.taller2dam.taller.dao.users;

import com.taller2dam.taller.errores.UsuarioNotFoundException;
import com.taller2dam.taller.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " no encontrado"));
    }

    public UserDetails loadUSerById(Long id) throws  UsernameNotFoundException{
        return usuarioService.findUsuarioById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }
}
