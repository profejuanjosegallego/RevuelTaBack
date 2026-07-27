package com.example.ReVueltaBack.validaciones.seguimientoenvio;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.SeguimientoEnvio;

@Component
public class ValidacionSeguimientoEnvioImpl implements IValidacionSeguimientoEnvio{

    //Longitud de ubicacion
    private static final int LONGITUD_MINIMA_UBICACION = 3;
    private static final int LONGITUD_MAXIMA_UBICACION = 255;
    private static final LocalDateTime FECHA_ACTUAL = LocalDateTime.now();

    @Override
    public void validarDescriptionObligarorio(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripcion es obligatoria");
        }
    }

    @Override
    public void validarUbicacionLongitud(String ubicacion) {
        if (ubicacion == null || ubicacion.length() < LONGITUD_MINIMA_UBICACION || ubicacion.length() > LONGITUD_MAXIMA_UBICACION) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la longitud de ubicacion es obligatoria y debe estar entre 3 y 255 caracteres.");
        }
    }

    @Override
    public void validarFechaHoraNoFutura(LocalDateTime fechaHora) {
        if (fechaHora == null || fechaHora.isAfter(FECHA_ACTUAL)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha y hora es obligatoria ni una fecha futura");
        }
    }

    @Override
    public void validar(SeguimientoEnvio seguimientoEnvio) {
        validarDescriptionObligarorio(seguimientoEnvio.getDescripcion());
        validarFechaHoraNoFutura(seguimientoEnvio.getFecha_hora());
        validarUbicacionLongitud(seguimientoEnvio.getUbicacion());
    }

}
