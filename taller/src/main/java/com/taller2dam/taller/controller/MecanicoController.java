package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Mecanico;
import com.taller2dam.taller.dto.MecanicoDTO;
import com.taller2dam.taller.dto.page.ListMecanicoPageDTO;
import com.taller2dam.taller.mapper.MecanicoMapper;
import com.taller2dam.taller.repository.MecanicoRepository;
import com.taller2dam.taller.service.MecanicoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class MecanicoController {
    private final MecanicoRepository mecanicoRepository;
    private final MecanicoService mecanicoService;
    private final MecanicoMapper mecanicoMapper;

    @Autowired
    public MecanicoController(MecanicoRepository mecanicoRepository, MecanicoService mecanicoService, MecanicoMapper mecanicoMapper) {
        this.mecanicoRepository = mecanicoRepository;
        this.mecanicoService = mecanicoService;
        this.mecanicoMapper = mecanicoMapper;
    }

    @GetMapping("/mecanicos")
    public ResponseEntity<List<MecanicoDTO>> findAll(@RequestParam(required = false, name = "limit") Optional<String> limit,
                                                     @RequestParam(required = false, name = "nombre") Optional<String> nombre) {
        List<Mecanico> mecanicos = null;
        try {
            if (nombre.isPresent()) {
                //mecanicos = mecanicoRepository.findByNombreContainsIgnoreCase(nombre.get());
            } else {
                mecanicos = mecanicoRepository.findAll();
            }
            if (limit.isPresent() && !mecanicos.isEmpty() && mecanicos.size() > Integer.parseInt(limit.get())) {

                return ResponseEntity.ok(mecanicoMapper.toDTO(
                        mecanicos.subList(0, Integer.parseInt(limit.get())))
                );
            } else {
                if (!mecanicos.isEmpty()) {
                    return ResponseEntity.ok(mecanicoMapper.toDTO(mecanicos));
                } else {
                    throw new Exception(); //Excepcion personalizada
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(); //excepcion personalizada
        }
    }

    @ApiOperation(value = "Obtener un mecanico por id", notes = "Obtiene un mecanico por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MecanicoDTO.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/mecanico/{id}")
    public ResponseEntity<MecanicoDTO> findById(@PathVariable Long id) {
        Mecanico mecanico = mecanicoRepository.findById(id).orElse(null);
        if (mecanico == null) {
            throw new NullPointerException(); //Excepcion personalizada
        } else {
            return ResponseEntity.ok(mecanicoMapper.toDTO(mecanico));
        }
    }

    @ApiOperation(value = "Crear un mecanico", notes = "Crea un mecanico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created", response = MecanicoDTO.class),
            @ApiResponse(code = 400, message = "Bad Request") //Excepcion personalizada
    })
    @PostMapping("/mecanico")
    public ResponseEntity<MecanicoDTO> save(@RequestBody MecanicoDTO mecanicoDTO) {
        try {
            Mecanico mecanico = mecanicoMapper.fromDTO(mecanicoDTO);
            checkMecanicoData(mecanico);
            Mecanico mecanicoInsertado = mecanicoRepository.save(mecanico);
            return ResponseEntity.ok(mecanicoMapper.toDTO(mecanicoInsertado));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Insertar, Error al insertar el mecanico. Campos incorrectos " + e.getMessage());
        }
    }

    @ApiOperation(value = "Actualizar un mecanico", notes = "Actualiza un mecanico por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MecanicoDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/mecanico/{id}")
    public ResponseEntity<MecanicoDTO> update(@PathVariable Long id, @RequestBody Mecanico mecanico) {
        try {
            Mecanico mecanicoActualizado = mecanicoRepository.findById(id).orElse(null);
            if (mecanicoActualizado == null) {
                throw new NullPointerException();
            } else {
                checkMecanicoData(mecanico);
                // Actualizamos los datos que queramos
                mecanicoActualizado.setId(mecanico.getId());
                mecanicoActualizado.setNombre(mecanico.getNombre());
                mecanicoActualizado.setSalario(mecanico.getSalario());
                mecanicoActualizado = mecanicoRepository.save(mecanicoActualizado);
                return ResponseEntity.ok(mecanicoMapper.toDTO(mecanicoActualizado));
            }
        } catch (Exception e) {
            throw new NullPointerException(); //Excepcion personalizada
        }
    }



    @ApiOperation(value = "Eliminar un mecanico", notes = "Elimina un mecanico dado su id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MecanicoDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/mecanico/{id}")
    public ResponseEntity<MecanicoDTO> delete(@PathVariable Long id) {
        try {
            Mecanico mecanico = mecanicoRepository.findById(id).orElse(null);
            if (mecanico == null) {
                throw new NullPointerException();
            } else {
                mecanicoRepository.delete(mecanico);
                return ResponseEntity.ok(mecanicoMapper.toDTO(mecanico));
            }
        } catch (Exception e) {
            throw new RuntimeException("Eliminar, Error al borrar el mecanico");
        }
    }
    /**
     * Método para comprobar que los datos del mecanico son correctos
     *
     * @param mecanico Mecanico a comprobar
     *                 - nombre no puede estar vacía
     *                 - salario no puede estar vacío
     */
    private void checkMecanicoData(Mecanico mecanico) {
        if (mecanico.getNombre() == null || mecanico.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }
        if (mecanico.getSalario() == null) {
            throw new RuntimeException("El salario es obligatorio");
        }
    }

    @ApiOperation(value = "Crea un mecanico", notes = "Crea un mecanico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MecanicoDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @PostMapping(value = "/mecanico/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> nuevoMecanico(
            @RequestPart("mecanico") MecanicoDTO mecanicoDTO) {

        try {
            // Comprobamos los campos obligatorios
            Mecanico mecanico = mecanicoMapper.fromDTO(mecanicoDTO);
            checkMecanicoData(mecanico);
            //TODO IMAGEN?
            Mecanico mecanicoInsertado = mecanicoRepository.save(mecanico);
            return ResponseEntity.ok(mecanicoMapper.toDTO(mecanicoInsertado));
        } catch (Exception ex) {
            throw new RuntimeException("Insertar, Error al insertar el mecanico. Campos incorrectos");
        }
    }
/*
    @ApiOperation(value = "Obtiene una lista de mecanicos", notes = "Obtiene una lista de mecanicos paginada, filtrada y ordenada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK: Lista de mecanicos", response = MecanicoDTO.class),
            @ApiResponse(code = 400, message = "Bad Request: Lista no encontrada")
    })

    @GetMapping("/mecanicopage")
    public ResponseEntity<?> listado(
            // Podemos buscar por los campos que quieramos... nombre, precio... así construir consultas
            @RequestParam(required = false, name = "nombre") Optional<String> nombre,
            @RequestParam(required = false, name = "salario") Optional<Double> salario,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        // Consulto en base a las páginas
        Pageable paging = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<Mecanico> pagedResult;
        try {
            if (nombre.isPresent() && salario.isPresent()) {
                pagedResult = mecanicoRepository.findByNombreAndPrecioPageable(nombre.get(), salario.get(), paging);
            } else {
                pagedResult = mecanicoRepository.findAll(paging);
                //throw new RuntimeException("Hay algún campo incompleto: matricula o salario");
            }
            // De la página saco la lista de mecanicos
            //List<Mecanico> mecanicos = pagedResult.getContent();
            // Mapeo al DTO. Si quieres ver toda la info de las paginas pon pageResult.


            ListMecanicoPageDTO listMecanicoPageDTO = ListMecanicoPageDTO.builder()
                    .data(mecanicoMapper.toDTO(pagedResult.getContent()))
                    .totalPages(pagedResult.getTotalPages())
                    .totalElements(pagedResult.getTotalElements())
                    .currentPage(pagedResult.getNumber())
                    .sort(pagedResult.getSort().toString())
                    .build();
            return ResponseEntity.ok(listMecanicoPageDTO);
            //return null; //<-------------Cambiar esto por el bloque comentado
        } catch (Exception e) {
            throw new RuntimeException("Selección de Datos Parámetros de consulta incorrectos");
        }
    }

 */
}