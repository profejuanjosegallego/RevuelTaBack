package com.example.ReVueltaBack.dtos.seguimientoenvio;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.SeguimientoEnvio;

public record SeguimientoEnvioResponseDTO(

        UUID id,
        String estado,
        String descripcion,
        String ubicacion,
        LocalDateTime fechaHora,
        Double latitud,
        Double longitud,
        UUID idEnvio

) {

    public static SeguimientoEnvioResponseDTO fromEntity(SeguimientoEnvio seguimientoEnvio){
        
        return new SeguimientoEnvioResponseDTO(
            seguimientoEnvio.getId(),
            seguimientoEnvio.getEstado(),
            seguimientoEnvio.getDescripcion(),
            seguimientoEnvio.getUbicacion(),
            seguimientoEnvio.getFecha_hora(),
            seguimientoEnvio.getLatitud(),
            seguimientoEnvio.getLongitud(),
            seguimientoEnvio.getEnvio().getId()
        );

    }

}
