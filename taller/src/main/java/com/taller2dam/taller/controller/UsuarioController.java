package com.taller2dam.taller.controller;

import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dto.UsuarioDTO;
import com.taller2dam.taller.mapper.UsuarioMapper;
import com.taller2dam.taller.repository.UsuarioRepository;
import com.taller2dam.taller.service.UsuarioService;
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
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> findAll(@RequestParam(required = false, name = "limit") Optional<String> limit,
                                                     @RequestParam(required = false, name = "nombre") Optional<String> nombre) {
        List<Usuario> usuarios = null;
        try {
            if (nombre.isPresent()) {
                //usuarios = usuarioRepository.findByNombreContainsIgnoreCase(nombre.get());
            } else {
                usuarios = usuarioRepository.findAll();
            }
            if (limit.isPresent() && !usuarios.isEmpty() && usuarios.size() > Integer.parseInt(limit.get())) {

                return ResponseEntity.ok(usuarioMapper.toDTO(
                        usuarios.subList(0, Integer.parseInt(limit.get())))
                );
            } else {
                if (!usuarios.isEmpty()) {
                    return ResponseEntity.ok(usuarioMapper.toDTO(usuarios));
                } else {
                    throw new Exception(); //Excepcion personalizada
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(); //excepcion personalizada
        }
    }

    @ApiOperation(value = "Obtener un usuario por id", notes = "Obtiene un usuario por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UsuarioDTO.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new NullPointerException(); //Excepcion personalizada
        } else {
            return ResponseEntity.ok(usuarioMapper.toDTO(usuario));
        }
    }

    @ApiOperation(value = "Crear un usuario", notes = "Crea un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created", response = UsuarioDTO.class),
            @ApiResponse(code = 400, message = "Bad Request") //Excepcion personalizada
    })
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioMapper.fromDTO(usuarioDTO);
            checkUsuarioData(usuario);
            Usuario usuarioInsertado = usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuarioMapper.toDTO(usuarioInsertado));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Insertar, Error al insertar el usuario. Campos incorrectos " + e.getMessage());
        }
    }

    @ApiOperation(value = "Actualizar un usuario", notes = "Actualiza un usuario por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UsuarioDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioActualizado = usuarioRepository.findById(id).orElse(null);
            if (usuarioActualizado == null) {
                throw new NullPointerException();
            } else {
                checkUsuarioData(usuario);
                // Actualizamos los datos que queramos
                usuarioActualizado.setId(usuario.getId());
                usuarioActualizado.setDni(usuario.getDni());
                usuarioActualizado.setNombre(usuario.getNombre());
                usuarioActualizado.setAdministrador(usuario.getAdministrador());
                usuarioActualizado.setDireccion(usuario.getDireccion());
                usuarioActualizado.setVehiculos(usuario.getVehiculos());
                usuarioActualizado.setTelefono(usuario.getTelefono());
                usuarioActualizado.setImagen(usuario.getImagen());
                usuarioActualizado.setBitmap(usuario.getBitmap());

                usuarioActualizado = usuarioRepository.save(usuarioActualizado);
                return ResponseEntity.ok(usuarioMapper.toDTO(usuarioActualizado));
            }
        } catch (Exception e) {
            throw new NullPointerException(); //Excepcion personalizada
        }
    }

    @ApiOperation(value = "Eliminar un usuario", notes = "Elimina un usuario dado su id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UsuarioDTO.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario == null) {
                throw new NullPointerException();
            } else {
                usuarioRepository.delete(usuario);
                return ResponseEntity.ok(usuarioMapper.toDTO(usuario));
            }
        } catch (Exception e) {
            throw new RuntimeException("Eliminar, Error al borrar el usuario");
        }
    }
    /**
     * Método para comprobar que los datos del usuario son correctos
     * @param usuario Usuario a comprobar
     *                 - nombre no puede estar vacía
     *                 - salario no puede estar vacío
     */
    private void checkUsuarioData(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }
        if (usuario.getDni() == null) {
            throw new RuntimeException("El DNI es obligatorio");
        }
    }
    @ApiOperation(value = "Crea un usuario", notes = "Crea un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UsuarioDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @PostMapping(value = "/usuario/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> nuevoUsuario(
            @RequestPart("usuario") UsuarioDTO usuarioDTO) {
        try {
            // Comprobamos los campos obligatorios
            Usuario usuario = usuarioMapper.fromDTO(usuarioDTO);
            checkUsuarioData(usuario);
            //TODO IMAGEN?
            Usuario usuarioInsertado = usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuarioMapper.toDTO(usuarioInsertado));
        } catch (Exception ex) {
            throw new RuntimeException("Insertar, Error al insertar el usuario. Campos incorrectos");
        }
    }
    @ApiOperation(value = "Obtiene una lista de usuarios", notes = "Obtiene una lista de usuarios paginada, filtrada y ordenada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK: Lista de usuarios", response = UsuarioDTO.class),
            @ApiResponse(code = 400, message = "Bad Request: Lista no encontrada")
    })
    @GetMapping("/all/usuario")
    public ResponseEntity<?> listado(
            // Podemos buscar por los campos que quieramos... nombre, precio... así construir consultas
            @RequestParam(required = false, name = "nombre") Optional<String> nombre,
            @RequestParam(required = false, name = "dni") Optional<String> dni,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ){
        // Consulto en base a las páginas
        Pageable paging = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        Page<Usuario> pagedResult;
        try {
            if (nombre.isPresent() && dni.isPresent()) {
                pagedResult = usuarioRepository.findAll(paging);
            } else {
                throw new RuntimeException("Hay algún campo incompleto: matricula o salario");
            }
            // De la página saco la lista de usuarios
            //List<Usuario> usuarios = pagedResult.getContent();
            // Mapeo al DTO. Si quieres ver toda la info de las paginas pon pageResult.


            /*ListUsuarioPageDTO listUsuarioPageDTO = ListUsuarioPageDTO.builder()
                    .data(usuarioMapper.toDTO(pagedResult.getContent()))
                    .totalPages(pagedResult.getTotalPages())
                    .totalElements(pagedResult.getTotalElements())
                    .currentPage(pagedResult.getNumber())
                    .sort(pagedResult.getSort().toString())
                    .build();
            return ResponseEntity.ok(listUsuarioPageDTO);*/
            return null; //<-------------Cambiar esto por el bloque comentado
        } catch (Exception e) {
            throw new RuntimeException("Selección de Datos Parámetros de consulta incorrectos");
        }
    }
}