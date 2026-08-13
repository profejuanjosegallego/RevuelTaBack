package com.example.ReVueltaBack.dtos.seguimientoenvio;

import java.time.LocalDateTime;

import com.example.ReVueltaBack.modelos.SeguimientoEnvio;

public record SeguimientoEnvioRequestDTO(

        String estado,
        String descripcion,
        String ubicacion,
        LocalDateTime fechaHora,
        Double latitud,
        Double longitud

) {

    public SeguimientoEnvio toEntity() {

        SeguimientoEnvio seguimientoEnvio = new SeguimientoEnvio();
        seguimientoEnvio.setEstado(estado);
        seguimientoEnvio.setDescripcion(descripcion);
        seguimientoEnvio.setUbicacion(ubicacion);
        seguimientoEnvio.setFecha_hora(fechaHora);
        seguimientoEnvio.setLatitud(latitud);
        seguimientoEnvio.setLongitud(longitud);
        return seguimientoEnvio;

    }
}
