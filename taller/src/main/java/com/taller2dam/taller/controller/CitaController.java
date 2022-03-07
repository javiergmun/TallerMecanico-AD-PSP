package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Cita;
import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dao.users.UserRole;
import com.taller2dam.taller.dto.CitaDTO;
import com.taller2dam.taller.mapper.CitaMapper;
import com.taller2dam.taller.repository.CitaRepository;
import com.taller2dam.taller.service.CitaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CitaController {
    private final CitaRepository citaRepository;
    private final CitaService citaService;
    private final CitaMapper citaMapper;

    @Autowired
    public CitaController(CitaRepository citaRepository, CitaService citaService, CitaMapper citaMapper) {
        this.citaRepository = citaRepository;
        this.citaService = citaService;
        this.citaMapper = citaMapper;
    }

    @ApiOperation(value = "Obtener todos los cita", notes = "Obtiene todos los cita")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CitaDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/citas")
    public ResponseEntity<List<CitaDTO>> findAll(@RequestParam(required = false, name = "limit") Optional<String> limit,
                                                 @RequestParam(required = false, name = "nombre") Optional<String> nombre,
                                                // @AuthenticationPrincipal Usuario user
    ) {
        List<Cita> cita = null;
        try {
          // if (user.getRoles().contains(UserRole.ADMIN)) {
          //     cita = citaRepository.findAll();
          // } else {
          //     cita = citaRepository.findAllByUsuario(user).get();
          // }


            if (nombre.isPresent()) {
                //cita = CitaRepository.findByNombreContainsIgnoreCase(nombre.get());
            } else {
                // cita = citaRepository.findAll();
            }

            if (limit.isPresent() && !cita.isEmpty() && cita.size() > Integer.parseInt(limit.get())) {

                return ResponseEntity.ok(citaMapper.toDTO(
                        cita.subList(0, Integer.parseInt(limit.get())))
                );

            } else {
                if (!cita.isEmpty()) {
                    return ResponseEntity.ok(citaMapper.toDTO(cita));
                } else {
                    throw new Exception(); //Excepcion personalizada
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(); //excepcion personalizada
        }
    }


    @ApiOperation(value = "Obtener un cita por id", notes = "Obtiene un cita por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CitaDTO.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/citas/{id}")
    public ResponseEntity<CitaDTO> findById(@PathVariable Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita == null) {
            throw new NullPointerException(); //Excepcion personalizada
        } else {
            return ResponseEntity.ok(citaMapper.toDTO(cita));
        }
    }

    @ApiOperation(value = "Crear una cita", notes = "Crea una cita")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created", response = CitaDTO.class),
            @ApiResponse(code = 400, message = "Bad Request") //Excepcion personalizada
    })
    @PostMapping("/citas")
    public ResponseEntity<CitaDTO> save(@RequestBody CitaDTO citaDTO) {
        try {
            Cita cita = citaMapper.fromDTO(citaDTO);
            checkCitaData(cita);
            Cita citaInsertado = citaRepository.save(cita);
            return ResponseEntity.ok(citaMapper.toDTO(citaInsertado));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Insertar, Error al insertar el cita. Campos incorrectos " + e.getMessage());
        }
    }


    @ApiOperation(value = "Actualizar un cita", notes = "Actualiza un cita por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CitaDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/citas/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Long id, @RequestBody Cita login) {
        try {
            Cita citaActualizado = citaRepository.findById(id).orElse(null);
            if (citaActualizado == null) {
                throw new NullPointerException();
            } else {
                checkCitaData(login);
                // Actualizamos los datos que queramos
                citaActualizado.setId(login.getId());
                citaActualizado.setFecha(login.getFecha());

                citaActualizado = citaRepository.save(citaActualizado);
                return ResponseEntity.ok(citaMapper.toDTO(citaActualizado));
            }
        } catch (Exception e) {
            throw new NullPointerException(); //Excepcion personalizada
        }
    }

    @ApiOperation(value = "Eliminar una cita", notes = "Elimina una cita dado su id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CitaDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/citas/{id}")
    public ResponseEntity<CitaDTO> delete(@PathVariable Long id) {
        try {
            Cita cita = citaRepository.findById(id).orElse(null);
            if (cita == null) {
                throw new NullPointerException();
            } else {
                citaRepository.delete(cita);
                return ResponseEntity.ok(citaMapper.toDTO(cita));
            }
        } catch (Exception e) {
            throw new RuntimeException("Eliminar, Error al borrar el cita");
        }
    }


    private void checkCitaData(Cita cita) {

        if (cita.getServicio() == null || cita.getMecanico() == null) {
            throw new RuntimeException("La marca es obligatoria y necesitas un mecanico que lo realice");
        }

    }
}