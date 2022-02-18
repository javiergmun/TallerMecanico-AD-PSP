package mapper;


import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dao.Vehiculo;
import com.taller2dam.taller.dto.UsuarioDTO;
import com.taller2dam.taller.dto.VehiculoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {
    private final ModelMapper modelMapper;

    public UsuarioDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);

    }

    public Usuario fromDTO(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);

    }

}