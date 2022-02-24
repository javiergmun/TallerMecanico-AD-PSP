package com.taller2dam.taller.service;

import com.taller2dam.taller.dao.Login;
import com.taller2dam.taller.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    LoginRepository loginRepository;

    public List<Login> findAllLogin() {return loginRepository.findAll();}
    public Optional<Login> findLoginById(Long loginId) {
        return loginRepository.findById(loginId);
    }
    public Login saveLogin(Login login) {
        return loginRepository.save(login);
    }

    public Optional<Login> updateLogin(Login login, Login loginNuevo) {
        Optional<Login> login1 = findLoginById(login.getId());
        login1.ifPresent(l -> {

            l.setId(loginNuevo.getId());
            l.setActivo(loginNuevo.getActivo());
            l.setToken(loginNuevo.getToken());
            loginRepository.save(l);

        });
        return login1;

    }
    public void deleteLogin(Login login) {
        loginRepository.delete(login);
    }
}