package com.example.ReVueltaBack.validaciones.calificaciones;

import java.time.LocalDate;

import com.example.ReVueltaBack.modelos.Calificacion;

public interface ICalificacionesValidador {

    void validarComentarioCortoObligatorio(String comentarioCorto);

    void validarPuntajeRango(Integer puntaje);

    void validarFechaNoFutura(LocalDate fecha);

    void validar(Calificacion calificacion);
}