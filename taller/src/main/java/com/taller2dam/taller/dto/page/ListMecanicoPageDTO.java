package com.taller2dam.taller.dto.page;

import com.taller2dam.taller.dto.MecanicoDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ListMecanicoPageDTO {

        private final String consulta = LocalDateTime.now().toString();
        private final String project = "SpringDam";
        private List<MecanicoDTO> data;

        @Min(message = "El número de página debe ser mayor o igual a 0", value = 0)
        private int currentPage;

        @Min(message = "El número de elementos por página debe ser mayor o igual a 1", value = 1)
        private long totalElements;

        private int totalPages;
        private String sort;
    }