package com.example.ReVueltaBack.dtos.calificaciones;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Calificacion;

public record CalificacionesResponseDTO(
    UUID id,
    Integer puntaje,
    String dimension,
    String comentarioCorto,
    LocalDate fecha,
    Boolean verificada,
    Integer peso,
    UUID reseñaID
) {

    public static CalificacionesResponseDTO fromEntity(Calificacion calificacion) {
        return new CalificacionesResponseDTO(
            calificacion.getId(),
            calificacion.getPuntaje(),
            calificacion.getDimension(),
            calificacion.getComentario_corto(),
            calificacion.getFecha(),
            calificacion.getVerificada(),
            calificacion.getPeso(),
            calificacion.getReseña().getId()
        );
    }
}