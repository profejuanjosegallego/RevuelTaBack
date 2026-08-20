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
        // Sin el id del envio no se puede guardar el seguimiento: la relacion
        // es obligatoria (optional = false en la entidad).
        UUID idEnvio

) {

    /**
     * @param envio envio YA buscado en la base de datos por el servicio.
     */
    public SeguimientoEnvio toEntity(Envio envio) {

        SeguimientoEnvio seguimientoEnvio = new SeguimientoEnvio();
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
