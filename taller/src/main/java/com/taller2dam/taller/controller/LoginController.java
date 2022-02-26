package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Login;
import com.taller2dam.taller.dto.LoginDTO;
import com.taller2dam.taller.dto.VehiculoDTO;
import com.taller2dam.taller.mapper.LoginMapper;
import com.taller2dam.taller.repository.LoginRepository;
import com.taller2dam.taller.service.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LoginController {
    private final LoginRepository loginRepository;
    private final LoginService loginService;
    private final LoginMapper loginMapper;

    @Autowired
    public LoginController(LoginRepository loginRepository, LoginService loginService, LoginMapper loginMapper) {
        this.loginRepository = loginRepository;
        this.loginService = loginService;
        this.loginMapper = loginMapper;
    }

    @ApiOperation(value = "Obtener todos los login", notes = "Obtiene todos los login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LoginDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/logins")
    public ResponseEntity<List<LoginDTO>> findAll(@RequestParam(required = false, name = "limit") Optional<String> limit,
                                                     @RequestParam(required = false, name = "nombre") Optional<String> nombre) {
        List<Login> login = null;
        try {
            if (nombre.isPresent()) {
                //login = LoginRepository.findByNombreContainsIgnoreCase(nombre.get());
            } else {
                login = loginRepository.findAll();
            }

            if (limit.isPresent() && !login.isEmpty() && login.size() > Integer.parseInt(limit.get())) {

                return ResponseEntity.ok(loginMapper.toDTO(
                        login.subList(0, Integer.parseInt(limit.get())))
                );

            } else {
                if (!login.isEmpty()) {
                    return ResponseEntity.ok(loginMapper.toDTO(login));
                } else {
                    throw new Exception(); //Excepcion personalizada
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(); //excepcion personalizada
        }
    }


    @ApiOperation(value = "Obtener un login por id", notes = "Obtiene un login por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LoginDTO.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/login/{id}")
    public ResponseEntity<LoginDTO> findById(@PathVariable Long id) {
        Login login = loginRepository.findById(id).orElse(null);
        if (login == null) {
            throw new NullPointerException(); //Excepcion personalizada
        } else {
            return ResponseEntity.ok(loginMapper.toDTO(login));
        }
    }

    @ApiOperation(value = "Crear un login", notes = "Crea un login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created", response = LoginDTO.class),
            @ApiResponse(code = 400, message = "Bad Request") //Excepcion personalizada
    })
    @PostMapping("/login")
    public ResponseEntity<LoginDTO> save(@RequestBody LoginDTO loginDTO) {
        try {
            Login login = loginMapper.fromDTO(loginDTO);
            checkLoginData(login);
            Login loginInsertado = loginRepository.save(login);
            return ResponseEntity.ok(loginMapper.toDTO(loginInsertado));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Insertar, Error al insertar el login. Campos incorrectos " + e.getMessage());
        }
    }


    @ApiOperation(value = "Actualizar un login", notes = "Actualiza un login por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VehiculoDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/login/{id}")
    public ResponseEntity<LoginDTO> update(@PathVariable Long id, @RequestBody Login login) {
        try {
            Login loginActualizado = loginRepository.findById(id).orElse(null);
            if (loginActualizado == null) {
                throw new NullPointerException();
            } else {
                checkLoginData(login);
                // Actualizamos los datos que queramos
                loginActualizado.setId(login.getId());
                loginActualizado.setActivo(login.getActivo());
                loginActualizado.setToken(login.getToken());

                loginActualizado = loginRepository.save(loginActualizado);
                return ResponseEntity.ok(loginMapper.toDTO(loginActualizado));
            }
        } catch (Exception e) {
            throw new NullPointerException(); //Excepcion personalizada
        }
    }

    @ApiOperation(value = "Eliminar un login", notes = "Elimina un login dado su id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LoginDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/login/{id}")
    public ResponseEntity<LoginDTO> delete(@PathVariable Long id) {
        try {
            Login login = loginRepository.findById(id).orElse(null);
            if (login == null) {
                throw new NullPointerException();
            } else {
                loginRepository.delete(login);
                return ResponseEntity.ok(loginMapper.toDTO(login));
            }
        } catch (Exception e) {
            throw new RuntimeException("Eliminar, Error al borrar el login");
        }
    }


    private void checkLoginData(Login login) {
        /*
        if (login.getMarca() == null || vehiculo.getMarca().isEmpty()) {
            throw new RuntimeException("La marca es obligatoria");
        }

         */
    }

}
