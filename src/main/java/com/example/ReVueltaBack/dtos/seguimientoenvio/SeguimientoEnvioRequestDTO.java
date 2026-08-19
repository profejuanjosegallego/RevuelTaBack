package com.example.ReVueltaBack.dtos.seguimientoenvio;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Envio;
import com.example.ReVueltaBack.modelos.SeguimientoEnvio;

public record SeguimientoEnvioRequestDTO(

        String estado,
        String descripcion,
        String ubicacion,
        LocalDateTime fechaHora,
        Double latitud,
        Double longitud,
        UUID idEnvio

) {

    public SeguimientoEnvio toEntity() {

        SeguimientoEnvio seguimientoEnvio = new SeguimientoEnvio();
        Envio envio = new Envio();
        envio.setId(idEnvio);
        seguimientoEnvio.setEstado(estado);
        seguimientoEnvio.setDescripcion(descripcion);
        seguimientoEnvio.setUbicacion(ubicacion);
        seguimientoEnvio.setFecha_hora(fechaHora);
        seguimientoEnvio.setLatitud(latitud);
        seguimientoEnvio.setLongitud(longitud);
        seguimientoEnvio.setEnvio(envio);
        return seguimientoEnvio;
    }
}
