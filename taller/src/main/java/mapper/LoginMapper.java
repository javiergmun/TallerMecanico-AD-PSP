package mapper;

import com.taller2dam.taller.dao.Login;
import com.taller2dam.taller.dao.Mecanico;
import com.taller2dam.taller.dto.LoginDTO;
import com.taller2dam.taller.dto.MecanicoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoginMapper {
    private final ModelMapper modelMapper;

    //DTOS BASICOS COMPLETOS
    public LoginDTO toDTO(Login login) {
        return modelMapper.map(login, LoginDTO.class);
    }
    public Login fromDTO(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, Login.class);
    }
    public List<LoginDTO> toDTO(List<Login> login) {
        return login.stream().map(this::toDTO).collect(Collectors.toList());
    }
}