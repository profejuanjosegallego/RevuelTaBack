package com.example.ReVueltaBack.dtos.seguimientoenvio;

import java.time.LocalDateTime;

public record SeguimientoEnvioResponseDTO(

        String estado,
        String descripcion,
        String ubicacion,
        LocalDateTime fechaHora,
        Double latitud,
        Double longitud

) {

}
