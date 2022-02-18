package mapper;


import com.taller2dam.taller.dao.Cita;
import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dto.CitaDTO;
import com.taller2dam.taller.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitaMapper {
    private final ModelMapper modelMapper;

    public CitaDTO toDTO(Cita cita) {
        return modelMapper.map(cita, CitaDTO.class);

    }

    public Cita fromDTO(CitaDTO citaDTO) {
        return modelMapper.map(citaDTO, Cita.class);

    }

}