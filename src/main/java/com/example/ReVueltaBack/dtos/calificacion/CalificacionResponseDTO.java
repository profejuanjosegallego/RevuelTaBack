package com.example.ReVueltaBack.dtos.calificacion;

import java.time.LocalDate;
import java.util.UUID;

import com.example.ReVueltaBack.modelos.Calificacion;

public record CalificacionResponseDTO(
    UUID id,
    Integer puntaje,
    String dimension,
    String comentarioCorto,
    LocalDate fecha,
    Boolean verificada,
    Integer peso,
    UUID idResena
) {

    public static CalificacionResponseDTO fromEntity(Calificacion calificacion) {
        return new CalificacionResponseDTO(
            calificacion.getId(),
            calificacion.getPuntaje(),
            calificacion.getDimension(),
            calificacion.getComentario_corto(),
            calificacion.getFecha(),
            calificacion.getVerificada(),
            calificacion.getPeso(),
            calificacion.getResena().getId()
        );
    }
}