package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Servicio;
import com.taller2dam.taller.dto.ServicioDTO;
import com.taller2dam.taller.mapper.ServicioMapper;
import com.taller2dam.taller.repository.ServicioRepository;
import com.taller2dam.taller.service.ServicioService;
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
public class ServicioController {
    private final ServicioRepository servicioRepository;
    private final ServicioService servicioService;
    private final ServicioMapper servicioMapper;

    @Autowired
    public ServicioController(ServicioRepository servicioRepository, ServicioService servicioService, ServicioMapper servicioMapper) {
        this.servicioRepository = servicioRepository;
        this.servicioService = servicioService;
        this.servicioMapper = servicioMapper;
    }

    @GetMapping("/servicios")
    public ResponseEntity<List<ServicioDTO>> findAll(@RequestParam(required = false, name = "limit") Optional<String> limit,
                                                     @RequestParam(required = false, name = "nombre") Optional<String> nombre) {
        List<Servicio> servicios = null;
        try {
            if (nombre.isPresent()) {
                //servicios = servicioRepository.findByNombreContainsIgnoreCase(nombre.get());
            } else {
                servicios = servicioRepository.findAll();
            }
            if (limit.isPresent() && !servicios.isEmpty() && servicios.size() > Integer.parseInt(limit.get())) {

                return ResponseEntity.ok(servicioMapper.toDTO(
                        servicios.subList(0, Integer.parseInt(limit.get())))
                );
            } else {
                if (!servicios.isEmpty()) {
                    return ResponseEntity.ok(servicioMapper.toDTO(servicios));
                } else {
                    throw new Exception(); //Excepcion personalizada
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(); //excepcion personalizada
        }
    }

    @ApiOperation(value = "Obtener un servicio por id", notes = "Obtiene un servicio por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ServicioDTO.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/servicio/{id}")
    public ResponseEntity<ServicioDTO> findById(@PathVariable Long id) {
        Servicio servicio = servicioRepository.findById(id).orElse(null);
        if (servicio == null) {
            throw new NullPointerException(); //Excepcion personalizada
        } else {
            return ResponseEntity.ok(servicioMapper.toDTO(servicio));
        }
    }

    @ApiOperation(value = "Crear un servicio", notes = "Crea un servicio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created", response = ServicioDTO.class),
            @ApiResponse(code = 400, message = "Bad Request") //Excepcion personalizada
    })
    @PostMapping("/servicio")
    public ResponseEntity<ServicioDTO> save(@RequestBody ServicioDTO servicioDTO) {
        try {
            Servicio servicio = servicioMapper.fromDTO(servicioDTO);
            checkServicioData(servicio);
            Servicio servicioInsertado = servicioRepository.save(servicio);
            return ResponseEntity.ok(servicioMapper.toDTO(servicioInsertado));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Insertar, Error al insertar el servicio. Campos incorrectos " + e.getMessage());
        }
    }

    @ApiOperation(value = "Actualizar un servicio", notes = "Actualiza un servicio por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ServicioDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/servicio/{id}")
    public ResponseEntity<ServicioDTO> update(@PathVariable Long id, @RequestBody Servicio servicio) {
        try {
            Servicio servicioActualizado = servicioRepository.findById(id).orElse(null);
            if (servicioActualizado == null) {
                throw new NullPointerException();
            } else {
                checkServicioData(servicio);
                // Actualizamos los datos que queramos
                servicioActualizado.setId(servicio.getId());
                servicioActualizado.setPrecio(servicio.getPrecio());
                servicioActualizado.setTipo(servicio.getTipo());
                servicioActualizado.setTiempo(servicio.getTiempo());
                servicioActualizado.setFecha_inicio(servicio.getFecha_inicio());
                servicioActualizado.setFecha_fin(servicio.getFecha_fin());
                servicioActualizado = servicioRepository.save(servicioActualizado);
                return ResponseEntity.ok(servicioMapper.toDTO(servicioActualizado));
            }
        } catch (Exception e) {
            throw new NullPointerException(); //Excepcion personalizada
        }
    }



    @ApiOperation(value = "Eliminar un servicio", notes = "Elimina un servicio dado su id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ServicioDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/servicio/{id}")
    public ResponseEntity<ServicioDTO> delete(@PathVariable Long id) {
        try {
            Servicio servicio = servicioRepository.findById(id).orElse(null);
            if (servicio == null) {
                throw new NullPointerException();
            } else {
                servicioRepository.delete(servicio);
                return ResponseEntity.ok(servicioMapper.toDTO(servicio));
            }
        } catch (Exception e) {
            throw new RuntimeException("Eliminar, Error al borrar el servicio");
        }
    }
    /**
     * Método para comprobar que los datos del servicio son correctos
     *
     * @param servicio Servicio a comprobar
     *                 - precio no puede estar vacío o negativo
     *                 - tipo no puede estar vacío
     */
    private void checkServicioData(Servicio servicio) {
        if (servicio.getPrecio() == null || servicio.getPrecio()<0) {
            throw new RuntimeException("El precio es obligatorio además de positivo");
        }
        if (servicio.getTipo() == null) {
            throw new RuntimeException("El tipo es obligatorio");
        }
    }

    @ApiOperation(value = "Crea un servicio", notes = "Crea un servicio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ServicioDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @PostMapping(value = "/servicio/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> nuevoServicio(
            @RequestPart("servicio") ServicioDTO servicioDTO) {

        try {
            // Comprobamos los campos obligatorios
            Servicio servicio = servicioMapper.fromDTO(servicioDTO);
            checkServicioData(servicio);
            //TODO IMAGEN?
            Servicio servicioInsertado = servicioRepository.save(servicio);
            return ResponseEntity.ok(servicioMapper.toDTO(servicioInsertado));
        } catch (Exception ex) {
            throw new RuntimeException("Insertar, Error al insertar el servicio. Campos incorrectos");
        }
    }

    @ApiOperation(value = "Obtiene una lista de servicios", notes = "Obtiene una lista de servicios paginada, filtrada y ordenada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK: Lista de servicios", response = ServicioDTO.class),
            @ApiResponse(code = 400, message = "Bad Request: Lista no encontrada")
    })
    @GetMapping("/all/servicio")
    public ResponseEntity<?> listado(
            // Podemos buscar por los campos que quieramos... nombre, precio... así construir consultas
            @RequestParam(required = false, name = "nombre") Optional<String> nombre,
            @RequestParam(required = false, name = "salario") Optional<String> salario,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        // Consulto en base a las páginas
        Pageable paging = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<Servicio> pagedResult;
        try {
            if (nombre.isPresent() && salario.isPresent()) {
                pagedResult = servicioRepository.findAll(paging);
            } else {
                throw new RuntimeException("Hay algún campo incompleto: matricula o salario");
            }
            // De la página saco la lista de servicios
            //List<Servicio> servicios = pagedResult.getContent();
            // Mapeo al DTO. Si quieres ver toda la info de las paginas pon pageResult.


            /*ListServicioPageDTO listServicioPageDTO = ListServicioPageDTO.builder()
                    .data(servicioMapper.toDTO(pagedResult.getContent()))
                    .totalPages(pagedResult.getTotalPages())
                    .totalElements(pagedResult.getTotalElements())
                    .currentPage(pagedResult.getNumber())
                    .sort(pagedResult.getSort().toString())
                    .build();
            return ResponseEntity.ok(listServicioPageDTO);*/
            return null; //<-------------Cambiar esto por el bloque comentado
        } catch (Exception e) {
            throw new RuntimeException("Selección de Datos Parámetros de consulta incorrectos");
        }
    }
}