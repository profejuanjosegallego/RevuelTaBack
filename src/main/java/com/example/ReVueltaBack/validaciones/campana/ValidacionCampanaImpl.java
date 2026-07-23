package com.example.ReVueltaBack.validaciones.campana;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Campana;

@Component
public class ValidacionCampanaImpl implements IValidacionCampana {

    private static final int MAX_LONGITUD_NOMBRE = 50;
    private static final int MAX_LONGITUD_DESCRIPCION = 50;

    @Override
    public void validarNombreObligatorio(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de campaña es obligatorio");
        }
        if (nombre.length() > MAX_LONGITUD_NOMBRE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de campaña no puede superar " + MAX_LONGITUD_NOMBRE + " caracteres");
        }
    }

    @Override
    public void validarDescripcionObligatoria(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción de campaña es obligatoria");
        }
        if (descripcion.length() > MAX_LONGITUD_DESCRIPCION) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción de campaña no puede superar " + MAX_LONGITUD_DESCRIPCION + " caracteres");
        }
    }

    @Override
    public void validarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        if (fechaInicio == null || fechaFinal == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas de inicio y final de la campaña son obligatorias");
        }
        if (fechaFinal.isBefore(fechaInicio) || fechaFinal.isEqual(fechaInicio)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha final debe ser posterior a la fecha de inicio");
        }
    }

    @Override
    public void validarDescuento(Double descuentoPct) {
        if (descuentoPct == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El porcentaje de descuento es obligatorio");
        }
        if (descuentoPct < 0 || descuentoPct > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El descuento debe estar entre 0 y 100");
        }
    }

    @Override
    public void validarActiva(Boolean activa) {
        if (activa == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo activa debe ser verdadero o falso");
        }
    }

    @Override
    public void validarCampana(Campana campana) {
        if (campana == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La campaña no puede ser nula");
        }
        validarNombreObligatorio(campana.getNombre_campana());
        validarDescripcionObligatoria(campana.getDescripcion_campana());
        validarFechas(campana.getFecha_inicio(), campana.getFecha_final());
        validarDescuento(campana.getDescuento_pct());
        validarActiva(campana.getActiva());
    }
}
