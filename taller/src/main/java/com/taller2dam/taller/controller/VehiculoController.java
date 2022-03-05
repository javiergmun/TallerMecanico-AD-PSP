package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dao.Vehiculo;
import com.taller2dam.taller.dto.VehiculoDTO;
import com.taller2dam.taller.mapper.VehiculoMapper;
import com.taller2dam.taller.repository.VehiculoRepository;
import com.taller2dam.taller.service.VehiculoService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
//@RequestMapping(ApiConfig.API_PATH + "/vehiculos")    //FALLA
public class VehiculoController {
    private final VehiculoRepository vehiculoRepository;
    private final VehiculoService vehiculoService;
    private final VehiculoMapper vehiculoMapper;

    @Autowired
    public VehiculoController(VehiculoRepository vehiculoRepository, VehiculoService vehiculoService, VehiculoMapper vehiculoMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.vehiculoService = vehiculoService;
        this.vehiculoMapper = vehiculoMapper;
    }

    // @CrossOrigin(origins = "http://localhost:6969") //
    @ApiOperation(value = "test", notes = "Mensaje de bienvenida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class)
    })
    @GetMapping("/test")
    public String test() {
        return "Todo OK";
    }

    @ApiOperation(value = "Obtener todos los vehiculos", notes = "Obtiene todos los vehiculos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VehiculoDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/vehiculos")
    public ResponseEntity<List<VehiculoDTO>> findAll(@RequestParam(required = false, name = "limit") Optional<String> limit,
                                                     @RequestParam(required = false, name = "nombre") Optional<String> nombre) {
        List<Vehiculo> vehiculos = null;
        try {
            if (nombre.isPresent()) {
                //vehiculos = vehiculoRepository.findByNombreContainsIgnoreCase(nombre.get());
            } else {
                vehiculos = vehiculoRepository.findAll();
            }

            if (limit.isPresent() && !vehiculos.isEmpty() && vehiculos.size() > Integer.parseInt(limit.get())) {

                return ResponseEntity.ok(vehiculoMapper.toDTO(
                        vehiculos.subList(0, Integer.parseInt(limit.get())))
                );

            } else {
                if (!vehiculos.isEmpty()) {
                    return ResponseEntity.ok(vehiculoMapper.toDTO(vehiculos));
                } else {
                    throw new Exception(); //Excepcion personalizada
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(); //excepcion personalizada
        }
    }


    @ApiOperation(value = "Obtener un vehiculo por id", notes = "Obtiene un vehiculo por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VehiculoDTO.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/vehiculo/{id}")
    public ResponseEntity<VehiculoDTO> findById(@PathVariable Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        if (vehiculo == null) {
            throw new NullPointerException(); //Excepcion personalizada
        } else {
            return ResponseEntity.ok(vehiculoMapper.toDTO(vehiculo));
        }
    }

    @ApiOperation(value = "Crear un vehiculo", notes = "Crea un vehiculo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created", response = VehiculoDTO.class),
            @ApiResponse(code = 400, message = "Bad Request") //Excepcion personalizada
    })
    @PostMapping("/vehiculo")
    public ResponseEntity<VehiculoDTO> save(@RequestBody VehiculoDTO vehiculoDTO) {
        try {
            Vehiculo vehiculo = vehiculoMapper.fromDTO(vehiculoDTO);
            checkVehiculoData(vehiculo);
            Vehiculo vehiculoInsertado = vehiculoRepository.save(vehiculo);
            return ResponseEntity.ok(vehiculoMapper.toDTO(vehiculoInsertado));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Insertar, Error al insertar el vehiculo. Campos incorrectos " + e.getMessage());
        }
    }


    @ApiOperation(value = "Actualizar un vehiculo", notes = "Actualiza un vehiculo por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VehiculoDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/vehiculo/{id}")
    public ResponseEntity<VehiculoDTO> update(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo vehiculoActualizado = vehiculoRepository.findById(id).orElse(null);
            if (vehiculoActualizado == null) {
                throw new NullPointerException();
            } else {
                checkVehiculoData(vehiculo);
                // Actualizamos los datos que queramos
                vehiculoActualizado.setId(vehiculo.getId());
                vehiculoActualizado.setMarca(vehiculo.getMarca());
                vehiculoActualizado.setModelo(vehiculo.getModelo());
                vehiculoActualizado.setMatricula(vehiculo.getMatricula());
                vehiculoActualizado.setColor(vehiculo.getColor());
                vehiculoActualizado.setImagen(vehiculo.getImagen());
                vehiculoActualizado.setBitmap(vehiculo.getBitmap());
                //vehiculoActualizado.setPropietario(vehiculo.getPropietario());

                vehiculoActualizado = vehiculoRepository.save(vehiculoActualizado);
                return ResponseEntity.ok(vehiculoMapper.toDTO(vehiculoActualizado));
            }
        } catch (Exception e) {
            throw new NullPointerException(); //Excepcion personalizada
        }
    }

    @ApiOperation(value = "Eliminar un vehiculo", notes = "Elimina un vehiculo dado su id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VehiculoDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/vehiculo/{id}")
    public ResponseEntity<VehiculoDTO> delete(@PathVariable Long id) {
        try {
            Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
            if (vehiculo == null) {
                throw new NullPointerException();
            } else {
                vehiculoRepository.delete(vehiculo);
                return ResponseEntity.ok(vehiculoMapper.toDTO(vehiculo));
            }
        } catch (Exception e) {
            throw new RuntimeException("Eliminar, Error al borrar el vehiculo");
        }
    }

    /**
     * Método para comprobar que los datos del vehiculo son correctos
     *
     * @param vehiculo Vehiculo a comprobar
     *                 - marca no puede estar vacía
     *                 - modelo no puede estar vacío
     *                 - matricula no puede estar vacío
     *                 - color no puede estar vacío
     *                 - propiertario no puede estar vacío
     */
    private void checkVehiculoData(Vehiculo vehiculo) {
        if (vehiculo.getMarca() == null || vehiculo.getMarca().isEmpty()) {
            throw new RuntimeException("La marca es obligatoria");
        }
        if (vehiculo.getModelo() == null || vehiculo.getModelo().isEmpty()) {
            throw new RuntimeException("El modelo es obligatorio");
        }
        if (vehiculo.getMatricula() == null || vehiculo.getMatricula().isEmpty()) {
            throw new RuntimeException("La matricula es obligatorio");
        }
        if (vehiculo.getColor() == null || vehiculo.getColor().isEmpty()) {
            throw new RuntimeException("El color es obligatorio");
        }
        //if (vehiculo.getPropietario() == null || vehiculo.getPropietario().getNombre().isEmpty()) {
          //  throw new RuntimeException("El propiertario es obligatorio");
        //}

    }

    @ApiOperation(value = "Crea un vehiculo con imagen", notes = "Crea un vehiculo con imagen")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VehiculoDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @PostMapping(value = "/vehiculo/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> nuevoVehiculo(
            @RequestPart("vehiculo") VehiculoDTO vehiculoDTO,
            @RequestPart("file") MultipartFile file) {

        try {
            // Comprobamos los campos obligatorios
            Vehiculo vehiculo = vehiculoMapper.fromDTO(vehiculoDTO);
            checkVehiculoData(vehiculo);

            if (!file.isEmpty()) {
                String imagen = file.toString();
                vehiculo.setImagen(imagen);
            }
            Vehiculo vehiculoInsertado = vehiculoRepository.save(vehiculo);
            return ResponseEntity.ok(vehiculoMapper.toDTO(vehiculoInsertado));
        } catch (Exception ex) {
            throw new RuntimeException("Insertar, Error al insertar el vehiculo. Campos incorrectos");
        }

    }

    //@Operation(summary = "Obtiene la lista de vehiculos existentes", description = "Obtiene la lista de vehiculos existentes")
    @ApiOperation(value = "Obtiene una lista de vehiculos", notes = "Obtiene una lista de vehiculos paginada, filtrada y ordenada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK: Lista de vehiculos", response = VehiculoDTO.class),
            @ApiResponse(code = 400, message = "Bad Request: Lista no encontrada")
    })
    @GetMapping("/all/vehiculo")
    public ResponseEntity<?> listado(
            // Podemos buscar por los campos que quieramos... nombre, precio... así construir consultas
            @RequestParam(required = false, name = "marca") Optional<String> marca,
            @RequestParam(required = false, name = "modelo") Optional<String> modelo,
            @RequestParam(required = false, name = "matricula") Optional<String> matricula,
            @RequestParam(required = false, name = "color") Optional<String> color,
            @RequestParam(required = false, name = "propietario") Optional<Usuario> propietario,
            @RequestParam(required = false, name = "imagen") Optional<String> imagen,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        // Consulto en base a las páginas
        Pageable paging = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<Vehiculo> pagedResult;
        try {
            if (matricula.isPresent() && propietario.isPresent()) {
                pagedResult = vehiculoRepository.findAll(paging);
            } else {
                throw new RuntimeException("Hay algún campo incompleto: matricula o propietario");
            }
            // De la página saco la lista de vehiculos
            //List<Vehiculo> vehiculos = pagedResult.getContent();
            // Mapeo al DTO. Si quieres ver toda la info de las paginas pon pageResult.


            /*ListVehiculoPageDTO listVehiculoPageDTO = ListVehiculoPageDTO.builder()
                    .data(vehiculoMapper.toDTO(pagedResult.getContent()))
                    .totalPages(pagedResult.getTotalPages())
                    .totalElements(pagedResult.getTotalElements())
                    .currentPage(pagedResult.getNumber())
                    .sort(pagedResult.getSort().toString())
                    .build();
            return ResponseEntity.ok(listVehiculoPageDTO);*/
            return null; //<-------------Cambiar esto por el bloque comentado
        } catch (Exception e) {
            throw new RuntimeException("Selección de Datos Parámetros de consulta incorrectos");
        }
    }
}