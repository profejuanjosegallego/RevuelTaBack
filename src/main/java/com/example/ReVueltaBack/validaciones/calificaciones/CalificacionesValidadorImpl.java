package com.example.ReVueltaBack.validaciones.calificaciones;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.ReVueltaBack.modelos.Calificacion;

@Component
public class CalificacionesValidadorImpl implements ICalificacionesValidador {

    @Override
    public void validarComentarioCortoObligatorio(String comentarioCorto) {

        if (comentarioCorto == null || comentarioCorto.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El comentario corto es obligatorio.");
        }
    }

    @Override
    public void validarPuntajeRango(Integer puntaje) {

        if (puntaje == null || puntaje < 1 || puntaje > 5) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El puntaje debe estar entre 1 y 5.");
        }
    }

    @Override
    public void validarFechaNoFutura(LocalDate fecha) {

        if (fecha == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha es obligatoria.");
        }

        if (fecha.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha no puede ser futura.");
        }
    }

    @Override
    public void validar(Calificacion calificacion) {

        if (calificacion == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La calificación es obligatoria.");
        }

        validarComentarioCortoObligatorio(calificacion.getComentario_corto());
        validarPuntajeRango(calificacion.getPuntaje());
        validarFechaNoFutura(calificacion.getFecha());
    }
}